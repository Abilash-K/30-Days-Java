# Day 30: Final Project - Task Management System

Welcome to Day 30! Congratulations on reaching the final day! Today we'll build a complete Task Management System that demonstrates everything you've learned.

## Table of Contents
- [Project Overview](#project-overview)
- [Requirements](#requirements)
- [Project Structure](#project-structure)
- [Implementation Guide](#implementation-guide)
- [Features to Implement](#features-to-implement)
- [Testing](#testing)
- [Next Steps](#next-steps)

## Project Overview

Build a comprehensive Task Management System that allows users to:
- Create, read, update, and delete tasks
- Organize tasks by category and priority
- Set due dates and reminders
- Mark tasks as complete
- Filter and search tasks
- Export/import tasks

### Technologies Used:
- Java 11
- Stream API
- Optional
- Date/Time API
- File I/O
- Collections Framework
- Lambda Expressions

## Requirements

### Functional Requirements:
1. **Task CRUD Operations**
   - Create new tasks with title, description, due date, priority
   - View all tasks or filtered tasks
   - Update existing tasks
   - Delete tasks
   - Mark tasks as complete/incomplete

2. **Organization**
   - Categories (Work, Personal, Shopping, etc.)
   - Priority levels (High, Medium, Low)
   - Status (Pending, In Progress, Completed)

3. **Search and Filter**
   - Search by title or description
   - Filter by category, priority, status
   - Filter by date range
   - Sort by various criteria

4. **Data Persistence**
   - Save tasks to file
   - Load tasks from file
   - Export to CSV/JSON

5. **User Interface**
   - Console-based menu system
   - Clear and intuitive navigation
   - Input validation

### Non-Functional Requirements:
- Clean, well-organized code
- Proper exception handling
- Use of Java 8/11 features
- Comprehensive comments
- Follow OOP principles

## Project Structure

```
TaskManagementSystem/
├── src/
│   ├── model/
│   │   ├── Task.java
│   │   ├── Category.java
│   │   ├── Priority.java
│   │   └── Status.java
│   ├── service/
│   │   ├── TaskService.java
│   │   └── FileService.java
│   ├── repository/
│   │   └── TaskRepository.java
│   ├── ui/
│   │   └── ConsoleUI.java
│   └── Main.java
├── data/
│   └── tasks.json
└── README.md
```

## Implementation Guide

### Step 1: Create Model Classes

#### Task.java
```java
package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;
    
    public Task(String title, String description, Category category, 
                Priority priority, LocalDateTime dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = Status.PENDING;
        this.dueDate = dueDate;
        this.createdDate = LocalDateTime.now();
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    // ... other getters and setters
    
    public void markAsComplete() {
        this.status = Status.COMPLETED;
        this.completedDate = LocalDateTime.now();
    }
    
    public boolean isOverdue() {
        return dueDate != null && 
               LocalDateTime.now().isAfter(dueDate) && 
               status != Status.COMPLETED;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Due: %s) [%s]",
            priority, title, status, dueDate.toLocalDate(), category);
    }
}
```

#### Enums
```java
package model;

public enum Category {
    WORK, PERSONAL, SHOPPING, HEALTH, EDUCATION, OTHER
}

public enum Priority {
    HIGH, MEDIUM, LOW
}

public enum Status {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}
```

### Step 2: Create Repository

```java
package repository;

import model.Task;
import java.util.*;
import java.util.stream.Collectors;

public class TaskRepository {
    private List<Task> tasks;
    
    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public Optional<Task> findById(String id) {
        return tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst();
    }
    
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }
    
    public List<Task> findByCategory(Category category) {
        return tasks.stream()
            .filter(task -> task.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    public List<Task> findByStatus(Status status) {
        return tasks.stream()
            .filter(task -> task.getStatus() == status)
            .collect(Collectors.toList());
    }
    
    public void deleteTask(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
    
    public List<Task> searchByTitle(String keyword) {
        return tasks.stream()
            .filter(task -> task.getTitle().toLowerCase()
                .contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
}
```

### Step 3: Create Service Layer

```java
package service;

import model.*;
import repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private TaskRepository repository;
    
    public TaskService() {
        this.repository = new TaskRepository();
    }
    
    public Task createTask(String title, String description, 
                          Category category, Priority priority, 
                          LocalDateTime dueDate) {
        Task task = new Task(title, description, category, priority, dueDate);
        repository.addTask(task);
        return task;
    }
    
    public Optional<Task> getTask(String id) {
        return repository.findById(id);
    }
    
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    
    public void updateTask(String id, String title, String description, 
                          Category category, Priority priority, 
                          LocalDateTime dueDate) {
        repository.findById(id).ifPresent(task -> {
            task.setTitle(title);
            task.setDescription(description);
            task.setCategory(category);
            task.setPriority(priority);
            task.setDueDate(dueDate);
        });
    }
    
    public void deleteTask(String id) {
        repository.deleteTask(id);
    }
    
    public void completeTask(String id) {
        repository.findById(id).ifPresent(Task::markAsComplete);
    }
    
    public List<Task> getTasksByPriority(Priority priority) {
        return getAllTasks().stream()
            .filter(task -> task.getPriority() == priority)
            .collect(Collectors.toList());
    }
    
    public List<Task> getOverdueTasks() {
        return getAllTasks().stream()
            .filter(Task::isOverdue)
            .collect(Collectors.toList());
    }
    
    public Map<Category, Long> getTaskCountByCategory() {
        return getAllTasks().stream()
            .collect(Collectors.groupingBy(
                Task::getCategory,
                Collectors.counting()
            ));
    }
    
    public List<Task> sortedByDueDate() {
        return getAllTasks().stream()
            .sorted(Comparator.comparing(Task::getDueDate))
            .collect(Collectors.toList());
    }
}
```

### Step 4: Create Console UI

```java
package ui;

import model.*;
import service.TaskService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleUI {
    private TaskService taskService;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;
    
    public ConsoleUI() {
        this.taskService = new TaskService();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
    
    public void start() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1: createTask(); break;
                case 2: viewAllTasks(); break;
                case 3: viewTasksByCategory(); break;
                case 4: searchTasks(); break;
                case 5: completeTask(); break;
                case 6: deleteTask(); break;
                case 7: viewStatistics(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }
        
        System.out.println("Goodbye!");
    }
    
    private void displayMenu() {
        System.out.println("\n=== Task Management System ===");
        System.out.println("1. Create Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. View Tasks by Category");
        System.out.println("4. Search Tasks");
        System.out.println("5. Complete Task");
        System.out.println("6. Delete Task");
        System.out.println("7. View Statistics");
        System.out.println("0. Exit");
    }
    
    private void createTask() {
        System.out.println("\n--- Create New Task ---");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        
        Category category = selectCategory();
        Priority priority = selectPriority();
        LocalDateTime dueDate = getDateInput("Due date (yyyy-MM-dd HH:mm): ");
        
        Task task = taskService.createTask(title, description, category, 
                                          priority, dueDate);
        System.out.println("Task created: " + task.getId());
    }
    
    // ... other methods
}
```

## Features to Implement

### Must-Have Features:
1. ✅ Create tasks with all attributes
2. ✅ View all tasks
3. ✅ Update tasks
4. ✅ Delete tasks
5. ✅ Mark tasks complete
6. ✅ Search and filter
7. ✅ Save/load from file

### Nice-to-Have Features:
- Priority-based sorting
- Due date reminders
- Recurring tasks
- Task tags
- Task dependencies
- Export to multiple formats
- Statistics dashboard
- Color-coded console output

## Testing

### Test Cases:
1. Create multiple tasks
2. View tasks in different views
3. Search with various keywords
4. Complete tasks
5. Delete tasks
6. Save and load data
7. Handle invalid inputs
8. Test edge cases

## Next Steps

### After Completing the Project:
1. **Refactor**: Review and improve code
2. **Add Tests**: Write unit tests
3. **Documentation**: Add JavaDoc comments
4. **Extend**: Add GUI using JavaFX
5. **Database**: Replace file storage with database
6. **API**: Create REST API
7. **Web**: Build web interface

## Congratulations! 🎉

You've completed the 30 Days of Java Challenge!

### What You've Learned:
- ✅ Java fundamentals
- ✅ Object-Oriented Programming
- ✅ Collections and Generics
- ✅ Lambda expressions and Streams
- ✅ Modern Java features (8 & 11)
- ✅ File I/O
- ✅ Exception handling
- ✅ Building complete applications

### Continue Your Journey:
- Build more projects
- Contribute to open source
- Learn frameworks (Spring, Hibernate)
- Explore advanced topics
- Join Java communities

**Keep coding and never stop learning!**

---

**Previous**: [Day 29 - Design Patterns](../Day29/README.md)

**Back to**: [Main README](../README.md)

