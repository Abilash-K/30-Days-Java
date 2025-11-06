# Day 29: Design Patterns

Welcome to Day 29! Today we'll learn about common design patterns in Java.

## Table of Contents
- [What are Design Patterns?](#what-are-design-patterns)
- [Singleton Pattern](#singleton-pattern)
- [Factory Pattern](#factory-pattern)
- [Builder Pattern](#builder-pattern)
- [Observer Pattern](#observer-pattern)
- [Strategy Pattern](#strategy-pattern)
- [Exercises](#exercises)

## What are Design Patterns?

Design patterns are reusable solutions to common software design problems.

### Categories:
- **Creational**: Object creation
- **Structural**: Object composition
- **Behavioral**: Object interaction

## Singleton Pattern

Ensure only one instance of a class exists.

```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {
        // Private constructor
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// Thread-safe version
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

// Enum Singleton (best practice)
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Singleton method");
    }
}
```

## Factory Pattern

Create objects without specifying exact class.

```java
// Product interface
interface Shape {
    void draw();
}

// Concrete products
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}

// Factory
class ShapeFactory {
    public static Shape createShape(String type) {
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            case "triangle":
                return new Triangle();
            default:
                throw new IllegalArgumentException("Unknown shape");
        }
    }
}

// Usage
public class FactoryDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        circle.draw();
        
        Shape rectangle = ShapeFactory.createShape("rectangle");
        rectangle.draw();
    }
}
```

## Builder Pattern

Construct complex objects step by step.

```java
class Person {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String address;
    
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    
    public static class Builder {
        private String name;
        private int age;
        private String email;
        private String phone;
        private String address;
        
        public Builder(String name) {
            this.name = name;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Person build() {
            return new Person(this);
        }
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + 
               ", email='" + email + "', phone='" + phone + 
               "', address='" + address + "'}";
    }
}

// Usage
public class BuilderDemo {
    public static void main(String[] args) {
        Person person = new Person.Builder("Alice")
            .age(25)
            .email("alice@example.com")
            .phone("123-456-7890")
            .address("123 Main St")
            .build();
        
        System.out.println(person);
    }
}
```

## Observer Pattern

Objects notify observers of state changes.

```java
import java.util.*;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject
class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Concrete observer
class EmailNotifier implements Observer {
    private String name;
    
    public EmailNotifier(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println(name + " received email: " + message);
    }
}

class SMSNotifier implements Observer {
    private String name;
    
    public SMSNotifier(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println(name + " received SMS: " + message);
    }
}

// Usage
public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        
        Observer email = new EmailNotifier("Alice");
        Observer sms = new SMSNotifier("Bob");
        
        subject.attach(email);
        subject.attach(sms);
        
        subject.notifyObservers("New update available!");
    }
}
```

## Strategy Pattern

Define family of algorithms, make them interchangeable.

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " + 
                         cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account " + email);
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}

// Usage
public class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Pay with credit card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(100.0);
        
        // Pay with PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(50.0);
    }
}
```

## Exercises

### Exercise 1: Singleton Database Connection
Implement singleton for database connection

### Exercise 2: Factory for Notifications
Create factory for different notification types

### Exercise 3: Builder for Configuration
Use builder pattern for configuration object

### Exercise 4: Observer for Events
Implement event system with observers

### Exercise 5: Strategy for Sorting
Implement different sorting strategies

## Summary

Today you learned:
- ✅ What design patterns are
- ✅ Singleton pattern
- ✅ Factory pattern
- ✅ Builder pattern
- ✅ Observer pattern
- ✅ Strategy pattern
- ✅ When to use each pattern

Tomorrow, we'll complete the **Final Project**!

---

**Next**: [Day 30 - Final Project](../Day30-Final-Project/README.md)

**Previous**: [Day 28 - Java 11 Additional Features](../Day28-Java11-More/README.md)
