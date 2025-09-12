package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String mobile;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	
	 // ✅ Projects where this user is a team member
    @ManyToMany
    @JoinTable(
        name = "user_projects",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    // ✅ Projects where this user is the manager
    @OneToMany(mappedBy = "manager")
    private Set<Project> managedProjects = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Task> tasks = new HashSet<>();

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", password="
				+ password + ", role=" + role + ", projects=" + projects + ", managedProjects=" + managedProjects
				+ ", tasks=" + tasks + "]";
	}
    
	

}
