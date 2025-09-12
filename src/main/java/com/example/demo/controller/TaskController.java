package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private EmployeeService employeeService;
	// Show Assign Task Page
	@GetMapping("/projects/{projectId}/assignTask/{employeeId}")
	public String showAssignTaskPage(@PathVariable Long projectId,
	                                 @PathVariable Long employeeId,
	                                 Model model) {
	    Project project = projectService.getProjectById(projectId);
	    User employee = userService.getUserById(employeeId);
	    List<Task> tasks = taskService.getTasksByProjectAndEmployee(projectId, employeeId);

	    model.addAttribute("project", project);
	    model.addAttribute("employee", employee);
	    model.addAttribute("tasks", tasks);

	    return "manager/assign-task"; // JSP page
	}
	
	
	// Handle Task Submission
	@PostMapping("/projects/{projectId}/assignTask/{employeeId}")
	public String assignTask(@PathVariable Long projectId,
	                         @PathVariable Long employeeId,
	                         @ModelAttribute Task task) {
	    taskService.assignTaskToEmployee(projectId, employeeId, task);
	    return "redirect:/projects/" + projectId + "/assignTask/" + employeeId;
	}
	
	@GetMapping("/employee/project/{projectId}/tasks")
	public String viewEmployeeProjectTasks(@PathVariable Long projectId,
	                                       HttpSession session,
	                                       Model model) {
	    User employee = (User) session.getAttribute("loggedInUser");
	    if (employee == null) {
	        return "redirect:/login";
	    }
	    session.setAttribute("currentProjectId", projectId);
	    List<Task> tasks = employeeService.getTasksForEmployeeInProject(projectId, employee.getId());

	    model.addAttribute("employee", employee);
	    model.addAttribute("projectId", projectId);
	    model.addAttribute("tasks", tasks);

	    return "employee/employee-tasks"; // New JSP page
	}

	 
}
