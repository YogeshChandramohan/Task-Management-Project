package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/projects/{id}")
    public String viewEmployeeProjects(@PathVariable("id") Long employeeId,
                                       HttpSession session,
                                       Model model) {
        User employee = (User) session.getAttribute("loggedInUser");
        if (employee == null) {
            return "redirect:/login";
        }

        Set<Project> projects = employeeService.getEmployeeProjects(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("projects", projects);

        return "employee/employee-projects"; // this is JSP view
    }
	
	@GetMapping("/employeeDashboard")
	public String moveToDashboard() {
		return "employee/dashboard";
	}
	
	
	//task status
	@PostMapping("/employee/task/{taskId}/updateStatus")
	public String updateTaskStatus(@PathVariable Long taskId,
	                               @RequestParam("status") String status,
	                               HttpSession session) {
	    User employee = (User) session.getAttribute("loggedInUser");
	    if (employee == null) {
	        return "redirect:/login";
	    }

	    employeeService.updateTaskStatus(taskId, status);

	    // Redirect back to tasks page
	    return "redirect:/employee/project/" + 
	           (Long) session.getAttribute("currentProjectId") + "/tasks";
	}

	

}
