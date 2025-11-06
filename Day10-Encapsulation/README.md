# Day 10: Encapsulation and Access Modifiers

Welcome to Day 10! Today we'll learn about encapsulation - one of the four pillars of OOP.

## Table of Contents
- [What is Encapsulation?](#what-is-encapsulation)
- [Access Modifiers](#access-modifiers)
- [Getters and Setters](#getters-and-setters)
- [Data Hiding](#data-hiding)
- [Benefits of Encapsulation](#benefits-of-encapsulation)
- [Packages](#packages)
- [Exercises](#exercises)

## What is Encapsulation?

Encapsulation is the mechanism of wrapping data (variables) and code (methods) together as a single unit. It hides the internal state of an object from the outside world and only exposes necessary operations.

### Real-world Example:
- A capsule that contains medicine - you don't see what's inside
- An ATM machine - you interact through an interface, not directly with cash

### Key Concepts:
- **Data Hiding**: Hide internal data from outside access
- **Controlled Access**: Provide public methods to access and modify private data
- **Protection**: Protect object's integrity by preventing invalid operations

## Access Modifiers

Java provides four access modifiers to control visibility:

### 1. Private
Accessible only within the same class.

```java
public class BankAccount {
    private double balance;  // Private field
    
    private void validateAmount(double amount) {  // Private method
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
}
```

### 2. Default (Package-Private)
Accessible within the same package (no modifier keyword).

```java
class Helper {
    int count;  // Default access
    
    void increment() {  // Default access
        count++;
    }
}
```

### 3. Protected
Accessible within the same package and by subclasses.

```java
public class Animal {
    protected String species;  // Protected field
    
    protected void makeSound() {  // Protected method
        System.out.println("Some sound");
    }
}
```

### 4. Public
Accessible from anywhere.

```java
public class Calculator {
    public int add(int a, int b) {  // Public method
        return a + b;
    }
}
```

### Access Modifier Table:

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| private | ✓ | ✗ | ✗ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| protected | ✓ | ✓ | ✓ | ✗ |
| public | ✓ | ✓ | ✓ | ✓ |

### Example:
```java
package banking;

public class Account {
    private String accountNumber;      // Only in this class
    double balance;                    // Same package
    protected String accountType;      // Same package + subclasses
    public String holderName;          // Everywhere
    
    private void internalAudit() {
        // Private method
    }
    
    void packageMethod() {
        // Default access
    }
    
    protected void restrictedOperation() {
        // Protected method
    }
    
    public void publicOperation() {
        // Public method
    }
}
```

## Getters and Setters

Getter and setter methods provide controlled access to private fields.

### Basic Example:
```java
public class Student {
    private String name;
    private int age;
    private double gpa;
    
    // Getter for name
    public String getName() {
        return name;
    }
    
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter for age
    public int getAge() {
        return age;
    }
    
    // Setter for age with validation
    public void setAge(int age) {
        if (age > 0 && age < 100) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Invalid age");
        }
    }
    
    // Getter for GPA
    public double getGpa() {
        return gpa;
    }
    
    // Setter for GPA with validation
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            throw new IllegalArgumentException("GPA must be between 0 and 4");
        }
    }
}

// Usage
Student student = new Student();
student.setName("Alice");
student.setAge(20);
student.setGpa(3.8);

System.out.println("Name: " + student.getName());
System.out.println("Age: " + student.getAge());
System.out.println("GPA: " + student.getGpa());
```

### Read-Only Properties:
```java
public class Employee {
    private final String employeeId;  // Can't be changed after initialization
    private String name;
    
    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }
    
    // Only getter for employeeId (read-only)
    public String getEmployeeId() {
        return employeeId;
    }
    
    // Getter and setter for name
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
```

### Write-Only Properties:
```java
public class User {
    private String password;
    
    // Only setter (write-only)
    public void setPassword(String password) {
        // Hash the password before storing
        this.password = hashPassword(password);
    }
    
    // Method to verify password (not a getter)
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(hashPassword(inputPassword));
    }
    
    private String hashPassword(String password) {
        // Simple example - use proper hashing in production
        return Integer.toString(password.hashCode());
    }
}
```

## Data Hiding

Protecting internal data from unauthorized access and modification.

### Example: Bank Account
```java
public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String pin;
    
    public BankAccount(String accountNumber, String holder, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolder = holder;
        this.pin = pin;
        this.balance = 0.0;
    }
    
    // Public methods with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount, String inputPin) {
        if (!validatePin(inputPin)) {
            System.out.println("Invalid PIN");
            return false;
        }
        
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount");
            return false;
        }
    }
    
    public double getBalance(String inputPin) {
        if (validatePin(inputPin)) {
            return balance;
        } else {
            System.out.println("Invalid PIN");
            return -1;
        }
    }
    
    private boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    // No direct access to PIN
    public void changePin(String oldPin, String newPin) {
        if (validatePin(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN changed successfully");
        } else {
            System.out.println("Invalid old PIN");
        }
    }
}
```

## Benefits of Encapsulation

### 1. Data Protection
```java
public class Temperature {
    private double celsius;
    
    public void setCelsius(double celsius) {
        if (celsius < -273.15) {  // Absolute zero
            throw new IllegalArgumentException("Temperature below absolute zero");
        }
        this.celsius = celsius;
    }
    
    public double getCelsius() {
        return celsius;
    }
    
    public double getFahrenheit() {
        return (celsius * 9/5) + 32;
    }
}
```

### 2. Flexibility
```java
public class Product {
    private double price;
    private double taxRate = 0.10;  // 10% tax
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }
    
    // Can change calculation without affecting external code
    public double getFinalPrice() {
        return price + (price * taxRate);
    }
    
    public void setTaxRate(double taxRate) {
        if (taxRate >= 0 && taxRate <= 1) {
            this.taxRate = taxRate;
        }
    }
}
```

### 3. Maintainability
```java
public class User {
    private String email;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email.toLowerCase();  // Normalize
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
```

## Packages

Packages are used to group related classes and control access.

### Creating a Package:
```java
package com.example.banking;

public class Account {
    private String accountNumber;
    protected double balance;  // Accessible in package and subclasses
    
    // Package-private method
    void internalOperation() {
        // Only accessible within com.example.banking package
    }
}
```

### Using Classes from Another Package:
```java
package com.example.app;

import com.example.banking.Account;

public class BankingApp {
    public static void main(String[] args) {
        Account account = new Account();
        // Can only access public members
    }
}
```

## Complete Example

```java
package library;

public class Book {
    // Private fields (encapsulated data)
    private String isbn;
    private String title;
    private String author;
    private double price;
    private int availableCopies;
    private int totalCopies;
    
    // Constructor
    public Book(String isbn, String title, String author, double price, int copies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        setPrice(price);  // Use setter for validation
        this.totalCopies = copies;
        this.availableCopies = copies;
    }
    
    // Getters
    public String getIsbn() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    // Setters with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }
    
    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
    }
    
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
    
    // Business logic methods
    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Book borrowed successfully");
            return true;
        } else {
            System.out.println("No copies available");
            return false;
        }
    }
    
    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            System.out.println("Book returned successfully");
        } else {
            System.out.println("All copies are already in library");
        }
    }
    
    public void displayInfo() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Available: " + availableCopies + "/" + totalCopies);
    }
    
    // Private helper method
    private void updateInventory() {
        // Internal bookkeeping
    }
}

// Usage
public class LibrarySystem {
    public static void main(String[] args) {
        Book book = new Book("978-0134685991", "Effective Java", 
                            "Joshua Bloch", 45.99, 5);
        
        book.displayInfo();
        
        book.borrowBook();
        book.borrowBook();
        
        System.out.println("Available copies: " + book.getAvailableCopies());
        
        book.returnBook();
        
        // Cannot access private fields directly
        // book.availableCopies = 100;  // Compilation error
        
        // Must use public methods
        book.setPrice(39.99);
    }
}
```

## Exercises

### Exercise 1: Person Class
Create an encapsulated Person class with:
- Private fields: name, age, email, phoneNumber
- Proper getters and setters with validation
- Method to display person info

### Exercise 2: Rectangle Class
Create Rectangle class with:
- Private fields: length, width
- Validation in setters (no negative values)
- Methods: area(), perimeter(), isSquare()

### Exercise 3: Employee Class
Create Employee class with:
- Private fields: id, name, salary
- Read-only employeeId
- Salary with validation (minimum wage)
- Method to give raise

### Exercise 4: Shopping Cart
Create ShoppingCart class with:
- Private list of items
- Methods: addItem(), removeItem(), getTotal()
- Proper encapsulation of internal list

### Exercise 5: Date Class
Create Date class with:
- Private fields: day, month, year
- Validation for valid dates
- Methods: isLeapYear(), getDayOfWeek()

## Summary

Today you learned:
- ✅ What encapsulation is and why it's important
- ✅ Four access modifiers (private, default, protected, public)
- ✅ Getters and setters for controlled access
- ✅ Data hiding and protection
- ✅ Benefits of encapsulation
- ✅ Packages and access control

Tomorrow, we'll explore **Exception Handling**!

---

**Next**: [Day 11 - Exception Handling](../Day11-Exception-Handling/README.md)

**Previous**: [Day 09 - Abstraction and Interfaces](../Day09-Abstraction-Interfaces/README.md)
