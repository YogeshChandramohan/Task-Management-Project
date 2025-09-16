package com.example.demo.mailRemainder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.EmailService;

@Component
public class ReminderScheduler {
	 @Autowired
	    private TaskRepository taskRepo;

	    @Autowired
	    private ProjectRepository projectRepo;

	    @Autowired
	    private EmailService emailService;

	    // Runs every day at 9 AM
	    @Scheduled(cron = "0 0 9 * * ?")
	    public void sendReminders() {
	        LocalDate today = LocalDate.now();
	        LocalDate tomorrow = today.plusDays(1);

	        // ✅ Employee Task Reminders
	        List<Task> tasksDueTomorrow = taskRepo.findByDueDate(tomorrow);
	        for (Task task : tasksDueTomorrow) {
	            if (task.getEmployee() != null && task.getEmployee().getEmail() != null) {
	                String subject = "Task Reminder: " + task.getTitle();
	                String body = "Hi " + task.getEmployee().getName() + ",\n\n" +
	                        "This is a reminder that your task '" + task.getTitle() +
	                        "' is due on " + task.getDueDate() + ".\n\nPlease make sure to complete it on time.\n\nRegards,\nTask Manager System";

	                emailService.sendEmail(task.getEmployee().getEmail(), subject, body);
	            }
	        }

	        // ✅ Manager Project Reminders
	        List<Project> projectsDueTomorrow = projectRepo.findByEdate(tomorrow);
	        for (Project project : projectsDueTomorrow) {
	            if (project.getManager() != null && project.getManager().getEmail() != null) {
	                String subject = "Project Reminder: " + project.getName();
	                String body = "Hi " + project.getManager().getName() + ",\n\n" +
	                        "This is a reminder that the project '" + project.getName() +
	                        "' is due on " + project.getEdate() + ".\n\nPlease review pending tasks and ensure completion.\n\nRegards,\nProject Tracker System";

	                emailService.sendEmail(project.getManager().getEmail(), subject, body);
	            }
	        }
	    }

}
