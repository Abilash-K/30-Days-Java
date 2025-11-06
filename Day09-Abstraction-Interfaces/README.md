# Day 09: Abstraction and Interfaces

Welcome to Day 9! Today we'll learn about abstraction and interfaces - key concepts in object-oriented programming.

## Table of Contents
- [What is Abstraction?](#what-is-abstraction)
- [Abstract Classes](#abstract-classes)
- [Abstract Methods](#abstract-methods)
- [Interfaces](#interfaces)
- [Interface Implementation](#interface-implementation)
- [Multiple Inheritance with Interfaces](#multiple-inheritance-with-interfaces)
- [Abstract Classes vs Interfaces](#abstract-classes-vs-interfaces)
- [Exercises](#exercises)

## What is Abstraction?

Abstraction is the process of hiding implementation details and showing only functionality to the user. It focuses on what an object does rather than how it does it.

### Real-world Example:
- When you drive a car, you use the steering wheel, pedals, and gears without knowing how the engine works internally
- When you use a phone, you tap icons without knowing the underlying code

### Benefits of Abstraction:
- **Reduced Complexity**: Hide complex implementation details
- **Increased Security**: Expose only necessary information
- **Easy Maintenance**: Changes to implementation don't affect users
- **Code Reusability**: Abstract concepts can be reused

### Abstraction in Java:
Java provides two ways to achieve abstraction:
1. **Abstract Classes** (0-100% abstraction)
2. **Interfaces** (100% abstraction)

## Abstract Classes

An abstract class is a class that cannot be instantiated and may contain abstract methods.

### Syntax:
```java
abstract class ClassName {
    // Abstract method (no body)
    abstract returnType methodName();
    
    // Concrete method (has body)
    void concreteMethod() {
        // Implementation
    }
}
```

### Key Points:
- Declared using `abstract` keyword
- Cannot create objects of abstract class
- Can have both abstract and concrete methods
- Can have constructors, static methods, and final methods
- Can have instance variables

### Example:
```java
abstract class Animal {
    String name;
    
    // Constructor
    public Animal(String name) {
        this.name = name;
    }
    
    // Abstract method (must be implemented by subclass)
    abstract void makeSound();
    
    // Concrete method
    void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    void eat() {
        System.out.println(name + " is eating");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    // Implementing abstract method
    @Override
    void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        // Animal animal = new Animal("Generic"); // Error: Cannot instantiate
        
        Animal dog = new Dog("Buddy");
        dog.makeSound();  // Woof! Woof!
        dog.sleep();      // Buddy is sleeping
        
        Animal cat = new Cat("Whiskers");
        cat.makeSound();  // Meow!
        cat.eat();        // Whiskers is eating
    }
}
```

## Abstract Methods

Abstract methods are methods declared without implementation.

### Rules:
1. Must be declared with `abstract` keyword
2. Cannot have a body
3. Must be in an abstract class
4. Subclass must override all abstract methods (unless subclass is also abstract)

### Example:
```java
abstract class Shape {
    String color;
    
    // Abstract methods
    abstract double calculateArea();
    abstract double calculatePerimeter();
    
    // Concrete method
    void displayColor() {
        System.out.println("Color: " + color);
    }
}

class Circle extends Shape {
    double radius;
    
    public Circle(String color, double radius) {
        this.color = color;
        this.radius = radius;
    }
    
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    double length;
    double width;
    
    public Rectangle(String color, double length, double width) {
        this.color = color;
        this.length = length;
        this.width = width;
    }
    
    @Override
    double calculateArea() {
        return length * width;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}
```

## Interfaces

An interface is a completely abstract class that contains only abstract methods (until Java 8).

### Syntax:
```java
interface InterfaceName {
    // Abstract methods (public and abstract by default)
    returnType methodName();
    
    // Constants (public, static, and final by default)
    int CONSTANT_NAME = value;
}
```

### Key Points:
- All methods are public and abstract by default (before Java 8)
- All variables are public, static, and final by default
- Cannot have constructors
- Cannot be instantiated
- Used to achieve 100% abstraction
- Supports multiple inheritance

### Example:
```java
interface Drawable {
    void draw();  // public abstract by default
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// Usage
Drawable shape1 = new Circle();
shape1.draw();  // Drawing Circle

Drawable shape2 = new Rectangle();
shape2.draw();  // Drawing Rectangle
```

## Interface Implementation

A class can implement one or more interfaces.

### Single Interface Implementation:
```java
interface Printable {
    void print();
}

class Document implements Printable {
    @Override
    public void print() {
        System.out.println("Printing document...");
    }
}
```

### Constants in Interfaces:
```java
interface MathConstants {
    double PI = 3.14159;        // public static final
    double E = 2.71828;         // public static final
    int MAX_VALUE = 100;        // public static final
}

class Calculator implements MathConstants {
    double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }
}
```

## Multiple Inheritance with Interfaces

Java doesn't support multiple inheritance with classes, but supports it with interfaces.

### Example:
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface Walkable {
    void walk();
}

// Class implementing multiple interfaces
class Duck implements Flyable, Swimmable, Walkable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
    
    @Override
    public void walk() {
        System.out.println("Duck is walking");
    }
}

class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Fish is swimming");
    }
}

class Bird implements Flyable, Walkable {
    @Override
    public void fly() {
        System.out.println("Bird is flying");
    }
    
    @Override
    public void walk() {
        System.out.println("Bird is walking");
    }
}
```

### Interface Inheritance:
Interfaces can extend other interfaces.

```java
interface Animal {
    void eat();
}

interface Mammal extends Animal {
    void breathe();
}

class Human implements Mammal {
    @Override
    public void eat() {
        System.out.println("Human is eating");
    }
    
    @Override
    public void breathe() {
        System.out.println("Human is breathing");
    }
}
```

## Abstract Classes vs Interfaces

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Can have both abstract and concrete methods | All methods are abstract (before Java 8) |
| Variables | Can have any type of variables | Only constants (public static final) |
| Constructor | Can have constructors | Cannot have constructors |
| Access Modifiers | Can have any access modifier | All methods are public by default |
| Multiple Inheritance | Not supported | Supported |
| Implementation | Uses `extends` keyword | Uses `implements` keyword |
| When to Use | When classes share common behavior | When unrelated classes implement same behavior |

### When to Use Abstract Classes:
```java
abstract class Vehicle {
    String brand;
    int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    // Common concrete method
    void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
    
    // Abstract method
    abstract void start();
}

class Car extends Vehicle {
    public Car(String brand, int year) {
        super(brand, year);
    }
    
    @Override
    void start() {
        System.out.println("Car is starting with key");
    }
}
```

### When to Use Interfaces:
```java
interface Payment {
    void processPayment(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

class BitcoinPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Bitcoin payment: $" + amount);
    }
}
```

## Complete Example

```java
// Interface for notification
interface Notifiable {
    void sendNotification(String message);
}

// Abstract class for Employee
abstract class Employee {
    protected String name;
    protected String id;
    protected double salary;
    
    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
    // Abstract method
    abstract double calculateBonus();
    
    // Concrete methods
    void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
    }
    
    double getTotalCompensation() {
        return salary + calculateBonus();
    }
}

// Manager class
class Manager extends Employee implements Notifiable {
    private int teamSize;
    
    public Manager(String name, String id, double salary, int teamSize) {
        super(name, id, salary);
        this.teamSize = teamSize;
    }
    
    @Override
    double calculateBonus() {
        return salary * 0.20 + (teamSize * 1000);
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("Manager " + name + " received: " + message);
    }
}

// Developer class
class Developer extends Employee implements Notifiable {
    private String programmingLanguage;
    
    public Developer(String name, String id, double salary, String language) {
        super(name, id, salary);
        this.programmingLanguage = language;
    }
    
    @Override
    double calculateBonus() {
        return salary * 0.15;
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("Developer " + name + " received: " + message);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Employee manager = new Manager("Alice", "M001", 80000, 5);
        manager.displayInfo();
        System.out.println("Bonus: $" + manager.calculateBonus());
        System.out.println("Total: $" + manager.getTotalCompensation());
        ((Notifiable) manager).sendNotification("Team meeting at 3 PM");
        
        System.out.println();
        
        Employee developer = new Developer("Bob", "D001", 70000, "Java");
        developer.displayInfo();
        System.out.println("Bonus: $" + developer.calculateBonus());
        System.out.println("Total: $" + developer.getTotalCompensation());
        ((Notifiable) developer).sendNotification("Code review scheduled");
    }
}
```

## Exercises

### Exercise 1: Bank Account System
Create an abstract class `BankAccount` with:
- Abstract methods: `calculateInterest()`, `withdraw()`
- Concrete methods: `deposit()`, `getBalance()`
- Implement `SavingsAccount` and `CurrentAccount` classes

### Exercise 2: Shape Hierarchy
Create interface `Shape` with methods `area()` and `perimeter()`.
Implement for: Circle, Rectangle, Triangle

### Exercise 3: Payment System
Create interface `Payable` with method `processPayment()`.
Implement for: CreditCard, DebitCard, DigitalWallet

### Exercise 4: Vehicle System
Create abstract class `Vehicle` and interfaces `Drivable`, `Refuelable`.
Implement for: Car, Motorcycle, ElectricCar

### Exercise 5: Media Player
Create interfaces: `Playable`, `Pausable`, `Stoppable`.
Create abstract class `MediaPlayer`.
Implement: AudioPlayer, VideoPlayer

## Summary

Today you learned:
- ✅ What abstraction is and why it's important
- ✅ Abstract classes and abstract methods
- ✅ Interfaces and their implementation
- ✅ Multiple inheritance with interfaces
- ✅ Difference between abstract classes and interfaces
- ✅ When to use abstract classes vs interfaces

Tomorrow, we'll explore **Encapsulation and Access Modifiers**!

---

**Next**: [Day 10 - Encapsulation](../Day10-Encapsulation/README.md)

**Previous**: [Day 08 - Inheritance and Polymorphism](../Day08-Inheritance-Polymorphism/README.md)
