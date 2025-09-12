package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  private String title;
	    private String description;
	    private LocalDate dueDate;
	    private String status;

	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private User employee;   // Assigned employee

	    @ManyToOne
	    @JoinColumn(name = "project_id")
	    private Project project; // Belongs to project

		@Override
		public String toString() {
			return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
					+ ", status=" + status + ", employee=" + employee + ", project=" + project + "]";
		}
	    
	    
}
