package com.project.taskmanager.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Digits;
// import jakarta.validation.constraints.FutureOrPresent;
// import jakarta.validation.constraints.Max;
// import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
// import jakarta.validation.constraints.Digits;

@Document(collection = "tasks")
public class Task {
    @Id

@NotNull(message = "id is required")
@Positive(message = "id must be a positive integer")

    private Integer id;
    
    
//Here's what @NotBlank does:
// Checks if the annotated string is not null.
// Checks if the trimmed length of the annotated string is greater than zero.
    @NotBlank(message = "Title is required")
    private String title;


        @NotBlank(message = "description is required")
private String description;

        @NotBlank(message = "duedate is required")
        @Pattern(regexp = "^202[4-9]-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "Due date must be in yyyy-MM-dd format")

    private String dueDate;

    
        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        @NotBlank(message = "status is required")

    private String status;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    
    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

  
}
