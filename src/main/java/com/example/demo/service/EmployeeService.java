package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class EmployeeService {
	@Autowired
	private UserRepository userRepository;
	  @Autowired
	    private TaskRepository taskRepository;

	    public List<Task> getTasksForEmployeeInProject(Long projectId, Long employeeId) {
	        return taskRepository.findByProjectAndEmployee(projectId, employeeId);
	    }
	public Set<Project> getEmployeeProjects(Long employeeId) {
	    return userRepository.findByIdWithProjects(employeeId)
	                         .map(User::getProjects)
	                         .orElse(Collections.emptySet());
	}

	public void updateTaskStatus(Long taskId, String status) {
	    Task task = taskRepository.findById(taskId).orElse(null);
	    if (task != null) {
	        task.setStatus(status);
	        taskRepository.save(task);
	    }
	}

}
