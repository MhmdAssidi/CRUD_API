package com.project.taskmanager.Controller;

import org.springframework.web.bind.annotation.RestController;
// import com.project.taskmanager.Repository.*;

import com.project.taskmanager.Model.Task;
import com.project.taskmanager.Repository.TaskRepository;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.PropertyMapper.Source;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//Controller classes (e.g., TaskController) that handle incoming HTTP requests and map them to appropriate methods for CRUD operations on tasks.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
@RestController
public class TaskController {
 
    @Autowired
   TaskRepository taskRepo;  //since extends mongorepository,and we need to use the function of mongo






   @PostMapping("/addTask")  //When a POST request is made to "/addTask", this method is invoked.
   public ResponseEntity<String> addTask(@Valid @RequestBody Task task, BindingResult bindingResult) {  //This method returns a ResponseEntity object, which represents the entire HTTP response, including headers, status code, and body. The response body is of type String
       // Check if there are validation errors
       //BindingResult bindingResult: This parameter captures any validation errors that occur during the validation process.
       if (bindingResult.hasErrors()) {
           // If there are validation errors, construct an error message and return a bad request response
           StringBuilder errorMessage = new StringBuilder();
           bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
           return ResponseEntity.badRequest().body(errorMessage.toString());  
       }

       // If there are no validation errors, proceed to save the task
       taskRepo.save(task);
       return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully!");
   }


   
    
    


    @PutMapping("/updateTask")
    public ResponseEntity<String> updateTask(@Valid @RequestBody Task task) {
        Task existingTask = taskRepo.findById(task.getId()).orElse(null); 
        if(existingTask != null){
            // Update task properties with the values from the request body
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setStatus(task.getStatus());
            
            // Save the updated task
            taskRepo.save(existingTask);
            return ResponseEntity.status(HttpStatus.OK).body("the task updated");
        }
        else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found with this id");
        }
    }
    


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/retrieveTask")
    public List<Task> retrieveTask() {
        return taskRepo.findAll();
    }
    
    
    @GetMapping("/retrieveTaskById/{id}")
    public ResponseEntity<String> getTaskById(@PathVariable Integer id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task != null) {
            String taskAsString = "ID: " + task.getId() + ", Title: " + task.getTitle() + ", Description: " + task.getDescription()+  ", duedate: " + task.getDueDate()+", Status: " + task.getStatus();
            return ResponseEntity.status(HttpStatus.OK).body(taskAsString);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found with this id");
        }
    }




    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id){
        // Check if the task exists before deleting it
        if(taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return ResponseEntity.ok("Task deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
        }
    }


}
