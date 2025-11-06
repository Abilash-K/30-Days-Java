# Day 15: Lambda Expressions

Welcome to Day 15! Today we dive into one of the most important Java 8 features - Lambda Expressions.

## Table of Contents
- [Introduction to Lambda Expressions](#introduction-to-lambda-expressions)
- [Syntax](#syntax)
- [Functional Interfaces](#functional-interfaces)
- [Lambda Examples](#lambda-examples)
- [Method References](#method-references)
- [Exercises](#exercises)

## Introduction to Lambda Expressions

Lambda expressions, introduced in Java 8, enable functional programming in Java. They provide a clear and concise way to represent one method interface using an expression.

### Benefits:
- **Concise Code**: Reduces boilerplate code
- **Functional Programming**: Enables functional-style operations
- **Better Collections**: Works seamlessly with Stream API
- **Parallel Processing**: Easier parallel operations

### Before Java 8 (Anonymous Class):
```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello from thread");
    }
};
```

### With Lambda (Java 8+):
```java
Runnable runnable = () -> System.out.println("Hello from thread");
```

## Syntax

### Basic Syntax:
```java
(parameters) -> expression
```

or

```java
(parameters) -> { statements; }
```

### Examples:

```java
// No parameters
() -> System.out.println("Hello")

// One parameter (parentheses optional)
x -> x * x
(x) -> x * x

// Multiple parameters
(x, y) -> x + y

// Multiple statements
(x, y) -> {
    int sum = x + y;
    return sum;
}

// With type declarations
(int x, int y) -> x + y
```

## Functional Interfaces

A functional interface has exactly one abstract method. Lambda expressions implement these interfaces.

### Common Functional Interfaces:

```java
// Predicate<T> - Takes an argument, returns boolean
Predicate<Integer> isEven = n -> n % 2 == 0;

// Function<T, R> - Takes T, returns R
Function<String, Integer> length = s -> s.length();

// Consumer<T> - Takes an argument, returns nothing
Consumer<String> printer = s -> System.out.println(s);

// Supplier<T> - Takes nothing, returns T
Supplier<Double> random = () -> Math.random();

// BiFunction<T, U, R> - Takes T and U, returns R
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
```

## Lambda Examples

### Example 1: Simple Lambda
```java
// Traditional approach
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}

// Lambda approach
names.forEach(name -> System.out.println(name));

// Method reference (even shorter)
names.forEach(System.out::println);
```

### Example 2: Filtering with Predicate
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Filter even numbers
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

### Example 3: Transforming with Function
```java
List<String> names = Arrays.asList("alice", "bob", "charlie");

// Convert to uppercase
List<String> upperNames = names.stream()
    .map(name -> name.toUpperCase())
    .collect(Collectors.toList());
```

### Example 4: Custom Functional Interface
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        
        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 - 5 = " + subtract.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));
    }
}
```

### Example 5: Comparator with Lambda
```java
// Before Java 8
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
});

// With Lambda
Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

// Even simpler with method reference
Collections.sort(names, String::compareTo);

// Or using Comparator methods
names.sort(Comparator.naturalOrder());
```

## Method References

Method references are a shorthand notation of a lambda expression to call a method.

### Types of Method References:

1. **Reference to a static method**
```java
// Lambda
Function<String, Integer> parser = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> parser = Integer::parseInt;
```

2. **Reference to an instance method of a particular object**
```java
String str = "Hello";

// Lambda
Supplier<String> upper = () -> str.toUpperCase();

// Method reference
Supplier<String> upper = str::toUpperCase;
```

3. **Reference to an instance method of an arbitrary object**
```java
// Lambda
Function<String, String> upper = s -> s.toUpperCase();

// Method reference
Function<String, String> upper = String::toUpperCase;
```

4. **Reference to a constructor**
```java
// Lambda
Supplier<List<String>> listSupplier = () -> new ArrayList<>();

// Method reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## Best Practices

1. **Keep lambdas short and readable**
```java
// Good
list.forEach(item -> System.out.println(item));

// Avoid - too complex
list.forEach(item -> {
    // Many lines of complex logic
    // Better to extract to a separate method
});
```

2. **Use method references when possible**
```java
// Less preferred
list.forEach(item -> System.out.println(item));

// Preferred
list.forEach(System.out::println);
```

3. **Avoid side effects in lambdas**
```java
// Avoid
int count = 0;
list.forEach(item -> count++); // count must be effectively final

// Better - use appropriate Stream operations
long count = list.stream().count();
```

## Exercises

### Exercise 1: Basic Lambda
Create lambda expressions for:
- A function that squares a number
- A function that checks if a string is empty
- A function that concatenates two strings

### Exercise 2: List Operations
Given a list of integers:
- Filter numbers greater than 50
- Double each number
- Find the sum of all numbers

### Exercise 3: String Manipulation
Create a program that uses lambdas to:
- Convert strings to uppercase
- Filter strings starting with a specific letter
- Sort strings by length

### Exercise 4: Custom Functional Interface
Create a functional interface `StringOperation` and implement:
- Reverse a string
- Remove spaces from a string
- Count vowels in a string

### Exercise 5: Comparator
Create different comparators using lambdas to sort:
- List of strings by length
- List of persons by age
- List of products by price

## Summary

Today you learned:
- ✅ What lambda expressions are and their syntax
- ✅ How to use functional interfaces
- ✅ Common functional interfaces in Java
- ✅ Method references as shorthand
- ✅ Best practices for using lambdas

Tomorrow, we'll explore **Functional Interfaces** in more detail!

---

**Next**: [Day 16 - Functional Interfaces](../Day16-Functional-Interfaces/README.md)

**Previous**: [Day 14 - Generics](../Day14-Generics/README.md)
