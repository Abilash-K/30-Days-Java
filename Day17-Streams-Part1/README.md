# Day 17: Stream API - Part 1

Welcome to Day 17! Today we'll learn about Stream API - a powerful feature for processing collections.

## Table of Contents
- [What are Streams?](#what-are-streams)
- [Creating Streams](#creating-streams)
- [Intermediate Operations](#intermediate-operations)
- [Terminal Operations](#terminal-operations)
- [filter()](#filter)
- [map()](#map)
- [sorted()](#sorted)
- [distinct()](#distinct)
- [limit() and skip()](#limit-and-skip)
- [Exercises](#exercises)

## What are Streams?

Stream API provides a declarative way to process collections of data.

### Characteristics:
- **Not a data structure**: Just a view of data
- **Functional in nature**: Operations don't modify source
- **Lazy evaluation**: Computed only when needed
- **Can be consumed only once**: Need new stream for reuse
- **Supports parallel processing**: Easy parallelization

### Benefits:
- Concise and readable code
- Better performance with parallel streams
- Functional programming style
- No explicit loops needed

## Creating Streams

Multiple ways to create streams:

```java
import java.util.*;
import java.util.stream.*;

public class CreateStreamDemo {
    public static void main(String[] args) {
        // From collection
        List<String> list = Arrays.asList("A", "B", "C");
        Stream<String> stream1 = list.stream();
        
        // From array
        String[] array = {"X", "Y", "Z"};
        Stream<String> stream2 = Arrays.stream(array);
        
        // Using Stream.of()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        
        // Empty stream
        Stream<String> emptyStream = Stream.empty();
        
        // Infinite stream with limit
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2)
                                              .limit(10);
        
        // Generate stream
        Stream<Double> randomStream = Stream.generate(Math::random)
                                           .limit(5);
        
        // Range streams (IntStream)
        IntStream range = IntStream.range(1, 5);        // 1,2,3,4
        IntStream rangeClosed = IntStream.rangeClosed(1, 5);  // 1,2,3,4,5
        
        System.out.println("Collection stream:");
        stream1.forEach(System.out::println);
        
        System.out.println("\nArray stream:");
        stream2.forEach(System.out::println);
    }
}
```

## Intermediate Operations

Operations that return a new stream (lazy evaluation).

### Common Intermediate Operations:
- `filter()` - Filter elements
- `map()` - Transform elements
- `flatMap()` - Flatten nested structures
- `distinct()` - Remove duplicates
- `sorted()` - Sort elements
- `limit()` - Limit to n elements
- `skip()` - Skip first n elements
- `peek()` - Debug/side effects

## Terminal Operations

Operations that produce a result and close the stream.

### Common Terminal Operations:
- `forEach()` - Iterate elements
- `collect()` - Collect to collection
- `reduce()` - Reduce to single value
- `count()` - Count elements
- `min()` / `max()` - Find min/max
- `anyMatch()` / `allMatch()` / `noneMatch()` - Test conditions
- `findFirst()` / `findAny()` - Find elements

## filter()

Filter elements based on a predicate.

```java
import java.util.*;
import java.util.stream.*;

public class FilterDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers
        System.out.println("Even numbers:");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);
        
        // Filter numbers > 5
        System.out.println("\nNumbers > 5:");
        numbers.stream()
               .filter(n -> n > 5)
               .forEach(System.out::println);
        
        // Multiple filters (chaining)
        System.out.println("\nEven numbers > 5:");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .filter(n -> n > 5)
               .forEach(System.out::println);
        
        // Filter strings
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
        System.out.println("\nNames starting with 'A':");
        names.stream()
             .filter(name -> name.startsWith("A"))
             .forEach(System.out::println);
        
        // Filter with length
        System.out.println("\nNames with length > 3:");
        names.stream()
             .filter(name -> name.length() > 3)
             .forEach(System.out::println);
    }
}
```

## map()

Transform elements from one form to another.

```java
import java.util.*;
import java.util.stream.*;

public class MapDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        
        // Convert to uppercase
        System.out.println("Uppercase names:");
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);
        
        // Get string lengths
        System.out.println("\nName lengths:");
        names.stream()
             .map(String::length)
             .forEach(System.out::println);
        
        // Square numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("\nSquared numbers:");
        numbers.stream()
               .map(n -> n * n)
               .forEach(System.out::println);
        
        // Transform objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35)
        );
        
        System.out.println("\nPerson names:");
        people.stream()
              .map(Person::getName)
              .forEach(System.out::println);
        
        System.out.println("\nPerson ages:");
        people.stream()
              .map(Person::getAge)
              .forEach(System.out::println);
    }
}

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
}
```

## sorted()

Sort elements in natural or custom order.

```java
import java.util.*;
import java.util.stream.*;

public class SortedDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        
        // Natural order
        System.out.println("Sorted (ascending):");
        numbers.stream()
               .sorted()
               .forEach(System.out::println);
        
        // Reverse order
        System.out.println("\nSorted (descending):");
        numbers.stream()
               .sorted(Comparator.reverseOrder())
               .forEach(System.out::println);
        
        // Sort strings
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        System.out.println("\nSorted names:");
        names.stream()
             .sorted()
             .forEach(System.out::println);
        
        // Sort by length
        System.out.println("\nSorted by length:");
        names.stream()
             .sorted(Comparator.comparing(String::length))
             .forEach(System.out::println);
        
        // Sort custom objects
        List<Person> people = Arrays.asList(
            new Person("Charlie", 25),
            new Person("Alice", 30),
            new Person("Bob", 20)
        );
        
        System.out.println("\nSorted by name:");
        people.stream()
              .sorted(Comparator.comparing(Person::getName))
              .forEach(p -> System.out.println(p.getName()));
        
        System.out.println("\nSorted by age:");
        people.stream()
              .sorted(Comparator.comparing(Person::getAge))
              .forEach(p -> System.out.println(p.getName() + ": " + p.getAge()));
    }
}
```

## distinct()

Remove duplicate elements.

```java
import java.util.*;
import java.util.stream.*;

public class DistinctDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        
        System.out.println("Distinct numbers:");
        numbers.stream()
               .distinct()
               .forEach(System.out::println);
        
        // Distinct strings
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        System.out.println("\nDistinct words:");
        words.stream()
             .distinct()
             .forEach(System.out::println);
        
        // Distinct with sorted
        System.out.println("\nDistinct and sorted:");
        numbers.stream()
               .distinct()
               .sorted()
               .forEach(System.out::println);
    }
}
```

## limit() and skip()

Limit or skip elements in stream.

```java
import java.util.*;
import java.util.stream.*;

public class LimitSkipDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // First 5 elements
        System.out.println("First 5:");
        numbers.stream()
               .limit(5)
               .forEach(System.out::println);
        
        // Skip first 5
        System.out.println("\nSkip first 5:");
        numbers.stream()
               .skip(5)
               .forEach(System.out::println);
        
        // Skip 3, then take 5
        System.out.println("\nSkip 3, take 5:");
        numbers.stream()
               .skip(3)
               .limit(5)
               .forEach(System.out::println);
        
        // Pagination example
        int page = 2;
        int pageSize = 3;
        System.out.println("\nPage " + page + ":");
        numbers.stream()
               .skip((page - 1) * pageSize)
               .limit(pageSize)
               .forEach(System.out::println);
    }
}
```

## Combining Operations

Chain multiple operations together.

```java
import java.util.*;
import java.util.stream.*;

public class CombinedOperationsDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10);
        
        // Complex chain
        System.out.println("Even numbers, squared, distinct, sorted, first 5:");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .map(n -> n * n)
               .distinct()
               .sorted()
               .limit(5)
               .forEach(System.out::println);
        
        // String processing
        List<String> words = Arrays.asList("apple", "banana", "CHERRY", "date", "ELDERBERRY");
        System.out.println("\nProcessed words:");
        words.stream()
             .map(String::toLowerCase)
             .filter(w -> w.length() > 4)
             .sorted()
             .forEach(System.out::println);
        
        // Person filtering and transformation
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 20),
            new Person("David", 35),
            new Person("Eve", 28)
        );
        
        System.out.println("\nPeople over 25, sorted by name:");
        people.stream()
              .filter(p -> p.getAge() > 25)
              .sorted(Comparator.comparing(Person::getName))
              .map(Person::getName)
              .forEach(System.out::println);
    }
}
```

## Complete Example

```java
import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;
    
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return name + " (" + category + "): $" + price;
    }
}

public class StreamExample {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 999.99),
            new Product("Phone", "Electronics", 699.99),
            new Product("Shirt", "Clothing", 29.99),
            new Product("Jeans", "Clothing", 49.99),
            new Product("Watch", "Accessories", 199.99),
            new Product("Headphones", "Electronics", 149.99),
            new Product("Shoes", "Clothing", 79.99)
        );
        
        // Find electronics under $200
        System.out.println("Electronics under $200:");
        products.stream()
                .filter(p -> p.getCategory().equals("Electronics"))
                .filter(p -> p.getPrice() < 200)
                .forEach(System.out::println);
        
        // Top 3 expensive products
        System.out.println("\nTop 3 expensive products:");
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(3)
                .forEach(System.out::println);
        
        // Get unique categories
        System.out.println("\nCategories:");
        products.stream()
                .map(Product::getCategory)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        
        // Product names in uppercase
        System.out.println("\nProduct names (uppercase):");
        products.stream()
                .map(Product::getName)
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
```

## Exercises

### Exercise 1: Number Processing
Given a list of numbers, find:
- Even numbers greater than 10
- Squares of odd numbers
- First 3 numbers divisible by 3

### Exercise 2: String Operations
Given a list of strings:
- Filter strings starting with vowels
- Convert to uppercase and sort
- Get unique first characters

### Exercise 3: Employee Management
Create Employee class and:
- Filter employees with salary > 50000
- Sort by name
- Get list of departments

### Exercise 4: Word Analysis
Given a text:
- Split into words
- Filter words longer than 4 characters
- Convert to lowercase and get unique words

### Exercise 5: Product Catalog
Create Product class and:
- Filter by category and price range
- Sort by price descending
- Get product names only

## Summary

Today you learned:
- ✅ What streams are and their benefits
- ✅ Creating streams from various sources
- ✅ Intermediate operations (filter, map, sorted, distinct, limit, skip)
- ✅ Terminal operations
- ✅ Chaining operations
- ✅ Practical stream examples

Tomorrow, we'll explore **Stream API - Part 2**!

---

**Next**: [Day 18 - Stream API - Part 2](../Day18-Streams-Part2/README.md)

**Previous**: [Day 16 - Functional Interfaces](../Day16-Functional-Interfaces/README.md)
