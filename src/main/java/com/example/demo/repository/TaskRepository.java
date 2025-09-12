package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findByProjectIdAndEmployeeId(Long projectId, Long employeeId);
	
	 @Query("SELECT t FROM Task t WHERE t.project.id = :projectId AND t.employee.id = :employeeId")
	    List<Task> findByProjectAndEmployee(@Param("projectId") Long projectId,
	                                        @Param("employeeId") Long employeeId);
	 
	 List<Task> findByProject_IdAndEmployee_Id(Long projectId, Long employeeId);
}
