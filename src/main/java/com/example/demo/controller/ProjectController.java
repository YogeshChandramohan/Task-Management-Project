package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	//Get All Projects
		 @GetMapping("/projects")
		    public String listProjects(Model model) {          
		        return projectService.getProjects(model); 
		    }
		
		 @GetMapping("/project/AddProject")
		    public String addProjectForm(Model model) {
			model.addAttribute("managers", projectService.getManager(model)) ;
			 model.addAttribute("project", new Project());
			return "project/AddProject";
		    }
@Autowired
private UserRepository repo;
		    @PostMapping("/project/addProject")
		    public String saveProject(@ModelAttribute Project project,@RequestParam("managerId") Long managerId) {
		    	 // fetch manager from DB
		        User manager = repo.findById(managerId).orElse(null);

		        // assign manager to project
		        project.setManager(manager);
		    	projectService.saveProject(project);
		        return "redirect:/projects";
		    }
		    
			//Edit
		 // Show Edit Form
		    @GetMapping("/projects/edit/{id}")
		    public String editProjectForm(@PathVariable Long id, Model model) {
		        Project project = projectService.getProjectById(id);
		        List<User> managers = repo.findByRole("MANAGER"); // fetch all managers

		        model.addAttribute("project", project);
		        model.addAttribute("managers", managers);

		        return "project/EditProject"; // JSP page
		    }

		    // Save Edited Project
		    @PostMapping("/projects/update/{id}")
		    public String updateProject(@PathVariable Long id,
		                                @ModelAttribute Project project,
		                                @RequestParam("managerId") Long managerId) {
		        // fetch manager from DB
		        User manager = repo.findById(managerId).orElse(null);

		        // assign manager
		        project.setManager(manager);

		        // keep the same id for update
		        project.setId(id);

		        projectService.saveProject(project);

		        return "redirect:/projects";
		    }
		    
		    //Delete
		    @GetMapping("/projects/delete/{id}")
		    public String deleteProject(@PathVariable Long id) {
		    	 return projectService.deleteProject(id);
		    }
		    
		    
		    //Manager
		    @Autowired
		    private ProjectRepository projectRepository;
		    @Autowired
		    private UserRepository userRepository;
		    @GetMapping("/projects/{projectId}/assignEmployees")
		    public String showAssignEmployeesPage(@PathVariable Long projectId, Model model) {
		        Project project = projectRepository.findById(projectId).orElseThrow();

		        // ✅ Get employees only
		        List<User> employees = userRepository.findByRole("EMPLOYEE");

		        model.addAttribute("project", project);
		        model.addAttribute("employees", employees);

		        return "manager/AssignEmployees"; // JSP page
		    }

		    @PostMapping("/projects/{projectId}/assignEmployees")
		    public String assignEmployeesToProject(
		            @PathVariable Long projectId,
		            @RequestParam("employeeIds") List<Long> employeeIds) {

		        Project project = projectRepository.findById(projectId).orElseThrow();
		        List<User> employees = userRepository.findAllById(employeeIds);
//		        List<User> employees = userService.getEmpByResource();
//		        System.out.println(employees);
		        for (User emp : employees) {
		            project.getUsers().add(emp);
		            emp.getProjects().add(project);
		        }

		        projectRepository.save(project);
		        userRepository.saveAll(employees);

		        return "redirect:/projects/ManagerProjectView/" + projectId;
		    }
		    //update project status
		    @PostMapping("/projects/{projectId}/updateStage")
		    public String updateStage(@PathVariable Long projectId,
		                              @RequestParam("stage") String stage) {
		        Project project = projectRepository.findById(projectId).orElse(null);
		        if (project != null) {
		            project.setStage(stage);
		            projectRepository.save(project);
		        }
		        return "redirect:/projects/ManagerProjectView/" + projectId;
		    }
		    
		    // view Project stage by Admin 
		    @GetMapping("/projects/view/{projectId}")
		    public String viewProject(@PathVariable Long projectId, Model model) {
		        Project project = projectRepository.findById(projectId).orElse(null);

		        if (project == null) {
		            return "redirect:/projects"; // redirect if not found
		        }

		        model.addAttribute("project", project);
		        model.addAttribute("manager", project.getManager());
		        model.addAttribute("employees", project.getUsers()); // assuming Project has Set<User> users
		        model.addAttribute("stage", project.getStage());

		        return "admin/ViewProject"; // JSP file
		    }


}
