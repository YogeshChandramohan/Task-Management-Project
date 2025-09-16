package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;

@Service
public class ManagerService {
	@Autowired
	private ProjectRepository projectRepository;

	public String getAllProjects(Long id,Model model) {
		List<Project> projects = projectRepository.findByManager_Id(id);
		   model.addAttribute("projects", projects);  
	        return "manager/ProjectList";
	}
	
}
