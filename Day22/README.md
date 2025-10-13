# Day 22: Default and Static Methods in Interfaces

Welcome to Day 22! Today we'll learn about default and static methods in interfaces introduced in Java 8.

## Table of Contents
- [Default Methods](#default-methods)
- [Static Methods](#static-methods)
- [Multiple Inheritance](#multiple-inheritance)
- [Practical Examples](#practical-examples)
- [Exercises](#exercises)

## Default Methods

Default methods allow interfaces to have method implementations.

### Why Default Methods?
- Add new methods without breaking existing implementations
- Provide default behavior
- Enable interface evolution

### Syntax:
```java
interface MyInterface {
    default void defaultMethod() {
        System.out.println("Default implementation");
    }
}
```

### Example:
```java
interface Vehicle {
    // Abstract method
    void start();
    
    // Default method
    default void stop() {
        System.out.println("Vehicle stopped");
    }
    
    default void honk() {
        System.out.println("Beep! Beep!");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    // Can override default method
    @Override
    public void stop() {
        System.out.println("Car stopped with brakes");
    }
    
    // Uses default honk()
}

class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike started");
    }
    
    // Uses default stop() and honk()
}

public class DefaultMethodDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.start();
        car.stop();    // Overridden version
        car.honk();    // Default version
        
        Vehicle bike = new Bike();
        bike.start();
        bike.stop();   // Default version
        bike.honk();   // Default version
    }
}
```

## Static Methods

Static methods in interfaces belong to the interface itself.

### Syntax:
```java
interface MyInterface {
    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}
```

### Example:
```java
interface MathOperations {
    static int add(int a, int b) {
        return a + b;
    }
    
    static int multiply(int a, int b) {
        return a * b;
    }
    
    static double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }
}

public class StaticMethodDemo {
    public static void main(String[] args) {
        // Call static methods directly on interface
        int sum = MathOperations.add(10, 20);
        int product = MathOperations.multiply(5, 6);
        double result = MathOperations.power(2, 3);
        
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
        System.out.println("Power: " + result);
    }
}
```

## Multiple Inheritance

Handling conflicts when multiple interfaces have default methods with the same signature.

### Diamond Problem Resolution:
```java
interface A {
    default void display() {
        System.out.println("Display from A");
    }
}

interface B {
    default void display() {
        System.out.println("Display from B");
    }
}

class MyClass implements A, B {
    // Must override to resolve conflict
    @Override
    public void display() {
        // Can call specific interface method
        A.super.display();
        B.super.display();
        // Or provide own implementation
        System.out.println("Display from MyClass");
    }
}

public class MultipleInheritanceDemo {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.display();
    }
}
```

## Practical Examples

### Example 1: Collection Interface Evolution
```java
import java.util.*;

interface MyList<E> {
    void add(E element);
    E get(int index);
    int size();
    
    // Default method added later without breaking existing implementations
    default void addAll(Collection<? extends E> elements) {
        for (E element : elements) {
            add(element);
        }
    }
    
    default void forEach(Consumer<? super E> action) {
        for (int i = 0; i < size(); i++) {
            action.accept(get(i));
        }
    }
    
    // Static utility method
    static <E> MyList<E> empty() {
        return new MyList<E>() {
            private List<E> list = new ArrayList<>();
            
            public void add(E element) { list.add(element); }
            public E get(int index) { return list.get(index); }
            public int size() { return list.size(); }
        };
    }
}
```

### Example 2: Logging Interface
```java
import java.time.LocalDateTime;

interface Logger {
    void log(String message);
    
    default void info(String message) {
        log("[INFO] " + message);
    }
    
    default void error(String message) {
        log("[ERROR] " + message);
    }
    
    default void warning(String message) {
        log("[WARNING] " + message);
    }
    
    static String formatMessage(String level, String message) {
        return LocalDateTime.now() + " " + level + " " + message;
    }
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        logger.info("Application started");
        logger.warning("Low memory");
        logger.error("File not found");
        
        String formatted = Logger.formatMessage("DEBUG", "Test message");
        System.out.println(formatted);
    }
}
```

### Example 3: Comparator Enhancements
```java
import java.util.*;

class Person {
    private String name;
    private int age;
    private String city;
    
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }
    
    @Override
    public String toString() {
        return name + " (" + age + ", " + city + ")";
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "NYC"),
            new Person("Bob", 25, "LA"),
            new Person("Charlie", 30, "NYC"),
            new Person("David", 25, "LA")
        );
        
        // Using default methods in Comparator
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Comparator<Person> byName = Comparator.comparing(Person::getName);
        
        // Chain comparators using default methods
        Comparator<Person> byAgeAndName = byAge.thenComparing(byName);
        
        people.sort(byAgeAndName);
        System.out.println("Sorted by age then name:");
        people.forEach(System.out::println);
        
        // Reverse order using default method
        people.sort(byAge.reversed());
        System.out.println("\nSorted by age (descending):");
        people.forEach(System.out::println);
    }
}
```

### Example 4: Collection Operations
```java
import java.util.*;
import java.util.function.*;

interface DataProcessor<T> {
    List<T> process(List<T> data);
    
    default DataProcessor<T> andThen(DataProcessor<T> next) {
        return data -> next.process(this.process(data));
    }
    
    default List<T> processAndFilter(List<T> data, Predicate<T> filter) {
        List<T> processed = process(data);
        List<T> result = new ArrayList<>();
        for (T item : processed) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    static <T> DataProcessor<T> identity() {
        return data -> data;
    }
}

class UpperCaseProcessor implements DataProcessor<String> {
    @Override
    public List<String> process(List<String> data) {
        List<String> result = new ArrayList<>();
        for (String s : data) {
            result.add(s.toUpperCase());
        }
        return result;
    }
}

class TrimProcessor implements DataProcessor<String> {
    @Override
    public List<String> process(List<String> data) {
        List<String> result = new ArrayList<>();
        for (String s : data) {
            result.add(s.trim());
        }
        return result;
    }
}

public class ProcessorDemo {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("  hello  ", "  world  ", "  java  ");
        
        DataProcessor<String> trimmer = new TrimProcessor();
        DataProcessor<String> upperCaser = new UpperCaseProcessor();
        
        // Chain processors using default method
        DataProcessor<String> combined = trimmer.andThen(upperCaser);
        
        List<String> result = combined.process(data);
        System.out.println("Result: " + result);
        
        // Use processAndFilter
        List<String> filtered = trimmer.processAndFilter(data, s -> s.length() > 4);
        System.out.println("Filtered: " + filtered);
        
        // Use static method
        DataProcessor<String> identity = DataProcessor.identity();
        List<String> unchanged = identity.process(data);
        System.out.println("Unchanged: " + unchanged);
    }
}
```

## Exercises

### Exercise 1: Payment Interface
Create a Payment interface with:
- Abstract method: processPayment()
- Default method: validateAmount()
- Static method: generateTransactionId()

### Exercise 2: Shape Calculator
Create Shape interface with:
- Abstract methods: area(), perimeter()
- Default method: displayInfo()
- Static method: compareAreas()

### Exercise 3: Message Formatter
Create interface with default formatting methods and static utilities

### Exercise 4: Data Validator
Create validation interface with default validation rules

### Exercise 5: Calculator Chain
Create calculator interface with chainable operations using default methods

## Summary

Today you learned:
- ✅ Default methods in interfaces
- ✅ Static methods in interfaces
- ✅ Resolving multiple inheritance conflicts
- ✅ Practical uses of interface methods
- ✅ Interface evolution without breaking changes

Tomorrow, we'll explore **File I/O and NIO**!

---

**Next**: [Day 23 - File I/O](../Day23/README.md)

**Previous**: [Day 21 - Method References](../Day21/README.md)
