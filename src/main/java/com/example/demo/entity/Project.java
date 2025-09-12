package com.example.demo.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String description;
	private LocalDate sdate;
	private LocalDate edate;
	private String stage;

	
	  // ✅ One manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    // ✅ Team members
    @ManyToMany(mappedBy = "projects")
    private Set<User> users = new HashSet<>();
    
    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", sdate=" + sdate + ", edate="
				+ edate + ", stage=" + stage + ", manager=" + manager + ", users=" + users + ", tasks=" + tasks + "]";
	}

	

	

	}

