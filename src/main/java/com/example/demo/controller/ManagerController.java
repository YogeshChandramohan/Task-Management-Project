package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
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

	    model.addAttribute("project", project);
	    model.addAttribute("employees", project.getUsers()); // users assigned to project

	    return "/manager/ViewProject"; // JSP name
	}
}
