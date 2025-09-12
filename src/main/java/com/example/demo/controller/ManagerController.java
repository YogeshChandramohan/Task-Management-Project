package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.ManagerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@GetMapping("/managerProjects")
	public String getAllProjects(HttpSession session,@RequestParam("managerId") Long ManagerId,Model model) {
		
		return managerService.getAllProjects(ManagerId, model);	
	}
	@GetMapping("/managerDashboard")
	public String dashboard() {
		return "/manager/dashboard";
	}
	
	// Manager view Project
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TaskRepository taskRepository;
	@GetMapping("/projects/ManagerProjectView/{projectId}")
	public String viewManagerProject(@PathVariable Long projectId, HttpSession session, Model model) {

	    // Get logged-in manager
	    User manager = (User) session.getAttribute("loggedInUser");
	    if (manager == null) {
	        return "redirect:/login";
	    }

	    // Fetch project
	    Project project = projectRepository.findById(projectId).orElse(null);
	    if (project == null || !project.getManager().getId().equals(manager.getId())) {
	        return "redirect:/projects/manager"; // redirect if not found or not manager's project
	    }
	    //employee task status 
	    Map<Long, Integer> employeeProgress = new HashMap<>();

	    for (User employee : project.getUsers()) {
	        List<Task> tasks = taskRepository.findByProject_IdAndEmployee_Id(projectId, employee.getId());

	        if (!tasks.isEmpty()) {
	            long completed = tasks.stream()
	                                  .filter(t -> "COMPLETED".equalsIgnoreCase(t.getStatus()))
	                                  .count();
	            int percentage = (int) ((completed * 100.0) / tasks.size());
	            employeeProgress.put(employee.getId(), percentage);
	        } else {
	            employeeProgress.put(employee.getId(), 0);
	        }
	    }
	    model.addAttribute("project", project);
	    model.addAttribute("employees", project.getUsers()); // users assigned to project
	    model.addAttribute("employeeProgress", employeeProgress);

	    return "/manager/ViewProject"; // JSP name
	}
}
