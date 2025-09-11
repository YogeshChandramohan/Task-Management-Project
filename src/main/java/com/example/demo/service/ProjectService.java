package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;

	public String getProjects(Model model) {
		model.addAttribute("projects", projectRepository.findAll());
		return "project/projectList";
	}
	
	public void saveProject(Project project) {
		projectRepository.save(project);
	}

	public List<User> getManager(Model model) {
		return userRepository.findByRole("MANAGER");	
	}

	public Project getProjectById(Long id) {
	    return projectRepository.findById(id).orElse(null);
	}

	public String deleteProject(Long id) {
		projectRepository.deleteById(id);
	        return "redirect:/projects";
	}

	 // Get employees not yet in this project
    public List<User> getAvailableEmployees(Project project) {
        List<User> allEmployees = userRepository.findByRole("EMPLOYEE"); // all employees
        Set<User> assigned = project.getUsers(); // already in project
        allEmployees.removeAll(assigned); // remove assigned employees
        return allEmployees;
    }

    public void addEmployeesToProject(Project project, List<Long> employeeIds) {
        Set<User> users = project.getUsers();
        for(Long id : employeeIds) {
            User user = userRepository.findById(id).orElse(null);
            if(user != null) {
                users.add(user);
                user.getProjects().add(project);
            }
        }
        projectRepository.save(project);
    }
}
