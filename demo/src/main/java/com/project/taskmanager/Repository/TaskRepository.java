package com.project.taskmanager.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.taskmanager.Model.Task;

//repository interfaces (e.g., TaskRepository) that define methods for interacting with MongoDB, such as saving, querying, updating, and deleting tasks.




//Extend the MongoRepository interface provided by Spring Data MongoDB.
//Pass your data model class (Task) and the type of its primary key (String) as type arguments to MongoRepository.
//This interface will inherit basic CRUD operations (such as save, findById, findAll, deleteById, etc.) from MongoRepository.

public interface TaskRepository extends MongoRepository<Task, Integer> {

}