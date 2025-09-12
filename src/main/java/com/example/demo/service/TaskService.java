package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	 @Autowired
	    private ProjectRepository projectRepository;
	    @Autowired
	    private UserRepository userRepository;
	
	    public List<Task> getTasksByProjectAndEmployee(Long projectId, Long employeeId) {
	    return taskRepository.findByProjectIdAndEmployeeId(projectId, employeeId);
	}
	
	    // ✅ Assign task to employee
	    public Task assignTaskToEmployee(Long projectId, Long employeeId, Task task) {
	        // Get project
	        Project project = projectRepository.findById(projectId)
	                .orElseThrow(() -> new RuntimeException("Project not found"));

	        // Get employee
	        User employee = userRepository.findById(employeeId)
	                .orElseThrow(() -> new RuntimeException("Employee not found"));

	        // Set relations
	        task.setProject(project);
	        task.setEmployee(employee);

	        // Save task
	        return taskRepository.save(task);
	    }
}
