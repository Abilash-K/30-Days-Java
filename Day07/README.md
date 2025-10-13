# Day 07: Classes and Objects

Welcome to Day 7! Today we begin our journey into Object-Oriented Programming (OOP) with classes and objects.

## Table of Contents
- [Introduction to OOP](#introduction-to-oop)
- [Classes](#classes)
- [Objects](#objects)
- [Constructors](#constructors)
- [Instance Variables and Methods](#instance-variables-and-methods)
- [this Keyword](#this-keyword)
- [Exercises](#exercises)

## Introduction to OOP

Object-Oriented Programming is a programming paradigm based on the concept of "objects" which contain data and code.

### Four Pillars of OOP:
1. **Encapsulation** - Bundling data and methods
2. **Inheritance** - Acquiring properties from parent class
3. **Polymorphism** - Many forms of the same entity
4. **Abstraction** - Hiding implementation details

## Classes

A class is a blueprint or template for creating objects.

### Class Declaration

```java
public class Person {
    // Instance variables (attributes)
    String name;
    int age;
    
    // Methods (behaviors)
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
```

### Class Components

```java
public class Student {
    // 1. Instance variables (fields)
    private String name;
    private int rollNumber;
    private double gpa;
    
    // 2. Constructor
    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }
    
    // 3. Methods
    public void study() {
        System.out.println(name + " is studying");
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + rollNumber);
        System.out.println("GPA: " + gpa);
    }
    
    // 4. Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
```

## Objects

An object is an instance of a class.

### Creating Objects

```java
// Syntax: ClassName objectName = new ClassName();
Person person1 = new Person();
Person person2 = new Person();

// Each object has its own copy of instance variables
person1.name = "Alice";
person1.age = 25;

person2.name = "Bob";
person2.age = 30;
```

### Using Objects

```java
public class Main {
    public static void main(String[] args) {
        // Create object
        Person person = new Person();
        
        // Set values
        person.name = "Alice";
        person.age = 25;
        
        // Call method
        person.displayInfo();
    }
}
```

## Constructors

Special methods used to initialize objects.

### Default Constructor

```java
public class Car {
    String brand;
    String model;
    
    // Default constructor (no parameters)
    public Car() {
        brand = "Unknown";
        model = "Unknown";
    }
}
```

### Parameterized Constructor

```java
public class Car {
    String brand;
    String model;
    int year;
    
    // Parameterized constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}

// Usage
Car car1 = new Car("Toyota", "Camry", 2023);
```

### Constructor Overloading

```java
public class Book {
    String title;
    String author;
    double price;
    
    // Constructor 1
    public Book() {
        title = "Unknown";
        author = "Unknown";
        price = 0.0;
    }
    
    // Constructor 2
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.price = 0.0;
    }
    
    // Constructor 3
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
```

## Instance Variables and Methods

### Instance Variables

Variables declared inside a class but outside methods.

```java
public class Rectangle {
    // Instance variables
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    // Instance method
    public double calculateArea() {
        return length * width;
    }
}
```

### Static Variables and Methods

Belong to the class rather than instances.

```java
public class Counter {
    // Static variable (shared by all instances)
    static int count = 0;
    
    // Instance variable
    int id;
    
    public Counter() {
        count++;
        id = count;
    }
    
    // Static method
    public static int getCount() {
        return count;
    }
}

// Usage
Counter c1 = new Counter();  // count = 1
Counter c2 = new Counter();  // count = 2
System.out.println(Counter.getCount());  // 2
```

## this Keyword

Refers to the current object instance.

### Uses of this

```java
public class Person {
    private String name;
    private int age;
    
    // 1. Distinguish between instance and parameter variables
    public Person(String name, int age) {
        this.name = name;  // this.name refers to instance variable
        this.age = age;
    }
    
    // 2. Call another constructor
    public Person() {
        this("Unknown", 0);  // Calls parameterized constructor
    }
    
    // 3. Pass current object as parameter
    public void display() {
        printPerson(this);
    }
    
    private void printPerson(Person p) {
        System.out.println(p.name);
    }
    
    // 4. Return current object
    public Person updateAge(int age) {
        this.age = age;
        return this;  // Method chaining
    }
}
```

## Complete Example

```java
public class BankAccount {
    // Instance variables
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    // Static variable
    private static int totalAccounts = 0;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        totalAccounts++;
    }
    
    // Methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    public void displayBalance() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
    
    // Static method
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("ACC001", "Alice", 1000.0);
        BankAccount account2 = new BankAccount("ACC002", "Bob", 500.0);
        
        account1.deposit(500);
        account1.withdraw(200);
        account1.displayBalance();
        
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
    }
}
```

## Exercises

### Exercise 1: Create a Student Class
Create a Student class with:
- name, rollNumber, grade
- Constructor to initialize
- Method to display info
- Create 3 student objects

### Exercise 2: Book Class
Create a Book class with:
- title, author, price, pages
- Multiple constructors
- Method to apply discount
- Display method

### Exercise 3: Circle Class
Create a Circle class with:
- radius
- Methods to calculate area and circumference
- Static variable for PI

### Exercise 4: Employee Class
Create an Employee class with:
- id, name, salary
- Method to give raise
- Static variable to count employees
- Static method to get employee count

### Exercise 5: Rectangle Class
Create a Rectangle class with:
- length, width
- Methods for area, perimeter
- Method to check if it's a square

## Summary

Today you learned:
- ✅ What classes and objects are
- ✅ How to create and use classes
- ✅ Constructors and constructor overloading
- ✅ Instance variables and methods
- ✅ Static members
- ✅ this keyword
- ✅ Creating real-world objects

Tomorrow, we'll explore **Inheritance and Polymorphism**!

---

**Next**: [Day 08 - Inheritance and Polymorphism](../Day08/README.md)

**Previous**: [Day 06 - Methods](../Day06/README.md)
