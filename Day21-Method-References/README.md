# Day 21: Method References

Welcome to Day 21! Today we'll learn about Method References - a shorthand notation for lambda expressions.

## Table of Contents
- [What are Method References?](#what-are-method-references)
- [Types of Method References](#types-of-method-references)
- [Static Method Reference](#static-method-reference)
- [Instance Method Reference](#instance-method-reference)
- [Constructor Reference](#constructor-reference)
- [Arbitrary Object Method Reference](#arbitrary-object-method-reference)
- [Exercises](#exercises)

## What are Method References?

Method references are compact lambda expressions that call a single method.

### Syntax:
```
ClassName::methodName
```

### Lambda vs Method Reference:
```java
// Lambda
Function<String, Integer> lambda = str -> Integer.parseInt(str);

// Method reference
Function<String, Integer> methodRef = Integer::parseInt;
```

## Types of Method References

1. **Static method**: `ClassName::staticMethod`
2. **Instance method of particular object**: `instance::instanceMethod`
3. **Instance method of arbitrary object**: `ClassName::instanceMethod`
4. **Constructor**: `ClassName::new`

## Static Method Reference

Reference to a static method.

```java
import java.util.*;
import java.util.function.*;

public class StaticMethodRefDemo {
    public static void main(String[] args) {
        // Lambda
        Function<String, Integer> lambdaParse = str -> Integer.parseInt(str);
        
        // Method reference
        Function<String, Integer> methodRefParse = Integer::parseInt;
        
        System.out.println(methodRefParse.apply("123"));
        
        // Math methods
        BiFunction<Double, Double, Double> max = Math::max;
        System.out.println("Max: " + max.apply(10.5, 20.3));
        
        // Stream example
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> ints = numbers.stream()
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
        System.out.println("Parsed: " + ints);
        
        // Custom static method
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(StaticMethodRefDemo::printUpperCase);
    }
    
    public static void printUpperCase(String str) {
        System.out.println(str.toUpperCase());
    }
}
```

## Instance Method Reference

Reference to an instance method of a particular object.

```java
import java.util.*;

class Printer {
    public void print(String message) {
        System.out.println("Printing: " + message);
    }
    
    public void printWithPrefix(String message) {
        System.out.println(">> " + message);
    }
}

public class InstanceMethodRefDemo {
    public static void main(String[] args) {
        Printer printer = new Printer();
        
        // Lambda
        Consumer<String> lambdaPrint = msg -> printer.print(msg);
        
        // Method reference
        Consumer<String> methodRefPrint = printer::print;
        
        methodRefPrint.accept("Hello");
        
        // Stream example
        List<String> messages = Arrays.asList("Message 1", "Message 2", "Message 3");
        messages.forEach(printer::printWithPrefix);
        
        // System.out.println
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.forEach(System.out::println);
    }
}
```

## Constructor Reference

Reference to a constructor.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Person {
    private String name;
    private int age;
    
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }
    
    public Person(String name) {
        this.name = name;
        this.age = 0;
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class ConstructorRefDemo {
    public static void main(String[] args) {
        // Supplier with no-arg constructor
        Supplier<Person> personSupplier = Person::new;
        Person p1 = personSupplier.get();
        System.out.println(p1);
        
        // Function with single-arg constructor
        Function<String, Person> personFunction = Person::new;
        Person p2 = personFunction.apply("Alice");
        System.out.println(p2);
        
        // BiFunction with two-arg constructor
        BiFunction<String, Integer, Person> personBiFunction = Person::new;
        Person p3 = personBiFunction.apply("Bob", 30);
        System.out.println(p3);
        
        // Stream example
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Person> people = names.stream()
                                  .map(Person::new)
                                  .collect(Collectors.toList());
        System.out.println("\nPeople: " + people);
        
        // Array constructor
        IntFunction<int[]> arrayCreator = int[]::new;
        int[] array = arrayCreator.apply(5);
        System.out.println("Array length: " + array.length);
        
        // Create list of arrays
        Function<Integer, List<String>> listCreator = ArrayList::new;
        List<String> list = listCreator.apply(10);
    }
}
```

## Arbitrary Object Method Reference

Reference to an instance method of an arbitrary object of a particular type.

```java
import java.util.*;
import java.util.function.*;

public class ArbitraryObjectMethodRefDemo {
    public static void main(String[] args) {
        // String::length - instance method of String class
        List<String> words = Arrays.asList("Java", "Python", "JavaScript");
        
        // Lambda
        words.stream()
             .map(str -> str.length())
             .forEach(System.out::println);
        
        // Method reference
        words.stream()
             .map(String::length)
             .forEach(System.out::println);
        
        // String::toUpperCase
        List<String> uppercase = words.stream()
                                      .map(String::toUpperCase)
                                      .collect(Collectors.toList());
        System.out.println("Uppercase: " + uppercase);
        
        // String::compareTo for sorting
        words.sort(String::compareTo);
        System.out.println("Sorted: " + words);
        
        // String::startsWith
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("Java starts with 'J': " + startsWith.test("Java", "J"));
        
        // Custom class example
        class Employee {
            private String name;
            private double salary;
            
            public Employee(String name, double salary) {
                this.name = name;
                this.salary = salary;
            }
            
            public String getName() { return name; }
            public double getSalary() { return salary; }
            
            @Override
            public String toString() {
                return name + ": $" + salary;
            }
        }
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 70000),
            new Employee("Bob", 60000),
            new Employee("Charlie", 80000)
        );
        
        // Get names
        List<String> names = employees.stream()
                                     .map(Employee::getName)
                                     .collect(Collectors.toList());
        System.out.println("\nEmployee names: " + names);
        
        // Sort by salary
        employees.sort(Comparator.comparing(Employee::getSalary));
        System.out.println("Sorted by salary: " + employees);
    }
}
```

## Comparing Lambda and Method Reference

```java
import java.util.*;
import java.util.function.*;

public class ComparisonDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        
        // Consumer
        list.forEach(s -> System.out.println(s));      // Lambda
        list.forEach(System.out::println);             // Method reference
        
        // Function
        Function<String, Integer> f1 = s -> s.length();  // Lambda
        Function<String, Integer> f2 = String::length;   // Method reference
        
        // Predicate
        Predicate<String> p1 = s -> s.isEmpty();       // Lambda
        Predicate<String> p2 = String::isEmpty;        // Method reference
        
        // Supplier
        Supplier<ArrayList<String>> s1 = () -> new ArrayList<>();  // Lambda
        Supplier<ArrayList<String>> s2 = ArrayList::new;           // Method reference
        
        // BiFunction
        BiFunction<String, String, Integer> bf1 = (s, t) -> s.compareTo(t);  // Lambda
        BiFunction<String, String, Integer> bf2 = String::compareTo;         // Method reference
    }
}
```

## Complete Example

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Product {
    private String name;
    private double price;
    private String category;
    
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
    
    public static Product create(String name, double price, String category) {
        return new Product(name, price, category);
    }
    
    public void displayInfo() {
        System.out.println(name + " - $" + price + " [" + category + "]");
    }
}

public class MethodReferenceExample {
    public static void main(String[] args) {
        // Using constructor reference
        List<String> names = Arrays.asList("Laptop", "Phone", "Tablet");
        
        // Static method reference
        Product product1 = Product.create("Laptop", 999.99, "Electronics");
        
        // Instance method references
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, "Electronics"),
            new Product("Phone", 699.99, "Electronics"),
            new Product("Shirt", 29.99, "Clothing"),
            new Product("Shoes", 79.99, "Clothing")
        );
        
        // Print all products
        System.out.println("All products:");
        products.forEach(Product::displayInfo);
        
        // Get product names
        List<String> productNames = products.stream()
                                           .map(Product::getName)
                                           .collect(Collectors.toList());
        System.out.println("\nProduct names: " + productNames);
        
        // Get prices
        List<Double> prices = products.stream()
                                     .map(Product::getPrice)
                                     .collect(Collectors.toList());
        System.out.println("Prices: " + prices);
        
        // Sort by price
        products.sort(Comparator.comparing(Product::getPrice));
        System.out.println("\nSorted by price:");
        products.forEach(System.out::println);
        
        // Group by category
        Map<String, List<Product>> byCategory = products.stream()
                                                       .collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("\nGrouped by category:");
        byCategory.forEach((cat, prods) -> {
            System.out.println(cat + ": " + prods.size() + " products");
        });
        
        // Filter and collect
        List<Product> electronics = products.stream()
                                           .filter(p -> "Electronics".equals(p.getCategory()))
                                           .collect(Collectors.toList());
        System.out.println("\nElectronics:");
        electronics.forEach(Product::displayInfo);
    }
}
```

## Exercises

### Exercise 1: String Operations
Use method references for:
- Converting to uppercase
- Getting string lengths
- Checking if empty

### Exercise 2: Number Processing
Use method references for:
- Parsing strings to integers
- Finding max/min
- Converting to double

### Exercise 3: Collection Operations
Use method references for:
- Printing elements
- Sorting
- Filtering

### Exercise 4: Object Creation
Create objects using constructor references

### Exercise 5: Custom Methods
Create custom methods and use them via method references

## Summary

Today you learned:
- ✅ What method references are
- ✅ Types of method references
- ✅ Static method references
- ✅ Instance method references
- ✅ Constructor references
- ✅ Arbitrary object method references
- ✅ When to use method references vs lambdas

Tomorrow, we'll explore **Default and Static Methods in Interfaces**!

---

**Next**: [Day 22 - Interface Methods](../Day22-Interface-Methods/README.md)

**Previous**: [Day 20 - Date and Time API](../Day20-DateTime-API/README.md)
