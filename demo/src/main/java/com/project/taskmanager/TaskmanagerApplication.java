package com.project.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskmanagerApplication {

	//PROJECT IDEA:
//MongoDB stores the data for your task manager application, your Spring Boot application serves as the backend that interacts with MongoDB to perform CRUD operations on tasks, and Postman allows you to test and validate your API endpoints by sending HTTP requests and inspecting the responses. 
	public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
