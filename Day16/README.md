# Day 16: Functional Interfaces

Welcome to Day 16! Today we'll learn about Functional Interfaces - the foundation for Lambda expressions.

## Table of Contents
- [What are Functional Interfaces?](#what-are-functional-interfaces)
- [@FunctionalInterface Annotation](#functionalinterface-annotation)
- [Built-in Functional Interfaces](#built-in-functional-interfaces)
- [Predicate](#predicate)
- [Consumer](#consumer)
- [Function](#function)
- [Supplier](#supplier)
- [Custom Functional Interfaces](#custom-functional-interfaces)
- [Exercises](#exercises)

## What are Functional Interfaces?

A functional interface is an interface with exactly one abstract method (SAM - Single Abstract Method).

### Characteristics:
- Contains exactly one abstract method
- Can have multiple default or static methods
- Used as lambda expression targets
- Foundation for functional programming in Java

### Example:
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // Using lambda expression
        Greeting greeting = (name) -> System.out.println("Hello, " + name);
        greeting.sayHello("Alice");
        
        // Using anonymous class
        Greeting greeting2 = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hi, " + name);
            }
        };
        greeting2.sayHello("Bob");
    }
}
```

## @FunctionalInterface Annotation

Optional annotation that ensures interface has exactly one abstract method.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    
    // Can have default methods
    default int add(int a, int b) {
        return a + b;
    }
    
    // Can have static methods
    static int multiply(int a, int b) {
        return a * b;
    }
}

public class CalculatorDemo {
    public static void main(String[] args) {
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        
        System.out.println("Addition: " + addition.calculate(10, 5));
        System.out.println("Subtraction: " + subtraction.calculate(10, 5));
        System.out.println("Default add: " + addition.add(10, 5));
        System.out.println("Static multiply: " + Calculator.multiply(10, 5));
    }
}
```

## Built-in Functional Interfaces

Java 8 provides several built-in functional interfaces in `java.util.function` package.

### Common Functional Interfaces:
- **Predicate\<T>**: Takes one argument, returns boolean
- **Consumer\<T>**: Takes one argument, returns nothing
- **Function\<T,R>**: Takes one argument, returns result
- **Supplier\<T>**: Takes no argument, returns result
- **UnaryOperator\<T>**: Takes one argument, returns same type
- **BinaryOperator\<T>**: Takes two arguments, returns same type
- **BiPredicate\<T,U>**: Takes two arguments, returns boolean
- **BiConsumer\<T,U>**: Takes two arguments, returns nothing
- **BiFunction\<T,U,R>**: Takes two arguments, returns result

## Predicate

Tests a condition and returns boolean.

### Interface:
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

### Examples:
```java
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.List;

public class PredicateDemo {
    public static void main(String[] args) {
        // Check if number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));
        
        // Check if string is empty
        Predicate<String> isEmpty = str -> str.isEmpty();
        System.out.println("Is '' empty? " + isEmpty.test(""));
        System.out.println("Is 'Hello' empty? " + isEmpty.test("Hello"));
        
        // Check if number is positive
        Predicate<Integer> isPositive = num -> num > 0;
        
        // Combining predicates
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("Is 10 even and positive? " + isEvenAndPositive.test(10));
        System.out.println("Is -10 even and positive? " + isEvenAndPositive.test(-10));
        
        // Filter list using predicate
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("\nEven numbers:");
        numbers.stream()
               .filter(isEven)
               .forEach(System.out::println);
    }
}
```

### Predicate Methods:
```java
// Combining predicates
Predicate<String> startsWithA = s -> s.startsWith("A");
Predicate<String> endsWithE = s -> s.endsWith("e");

// AND
Predicate<String> startsWithAandEndsWithE = startsWithA.and(endsWithE);

// OR
Predicate<String> startsWithAorEndsWithE = startsWithA.or(endsWithE);

// NEGATE
Predicate<String> notStartsWithA = startsWithA.negate();
```

## Consumer

Accepts input and performs operation without returning anything.

### Interface:
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

### Examples:
```java
import java.util.function.Consumer;
import java.util.Arrays;
import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) {
        // Print consumer
        Consumer<String> print = str -> System.out.println(str);
        print.accept("Hello, World!");
        
        // Uppercase consumer
        Consumer<String> printUppercase = str -> 
            System.out.println(str.toUpperCase());
        printUppercase.accept("hello");
        
        // List consumer
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<List<String>> printList = list -> {
            for (String name : list) {
                System.out.println(name);
            }
        };
        printList.accept(names);
        
        // Chaining consumers
        Consumer<String> c1 = str -> System.out.print(str.toUpperCase());
        Consumer<String> c2 = str -> System.out.println(" - Length: " + str.length());
        Consumer<String> combined = c1.andThen(c2);
        
        combined.accept("hello");  // HELLO - Length: 5
        
        // Using forEach
        names.forEach(name -> System.out.println("Hello, " + name));
    }
}
```

## Function

Transforms input to output.

### Interface:
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

### Examples:
```java
import java.util.function.Function;
import java.util.Arrays;
import java.util.List;

public class FunctionDemo {
    public static void main(String[] args) {
        // String length function
        Function<String, Integer> length = str -> str.length();
        System.out.println("Length of 'Hello': " + length.apply("Hello"));
        
        // Square function
        Function<Integer, Integer> square = num -> num * num;
        System.out.println("Square of 5: " + square.apply(5));
        
        // String to integer
        Function<String, Integer> parseIntFunction = str -> Integer.parseInt(str);
        System.out.println("Parse '123': " + parseIntFunction.apply("123"));
        
        // Chaining functions
        Function<Integer, Integer> multiplyBy2 = num -> num * 2;
        Function<Integer, Integer> add10 = num -> num + 10;
        
        Function<Integer, Integer> multiplyThenAdd = multiplyBy2.andThen(add10);
        System.out.println("5 * 2 + 10 = " + multiplyThenAdd.apply(5));  // 20
        
        Function<Integer, Integer> addThenMultiply = multiplyBy2.compose(add10);
        System.out.println("(5 + 10) * 2 = " + addThenMultiply.apply(5));  // 30
        
        // Transform list
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        System.out.println("\nWord lengths:");
        words.stream()
             .map(length)
             .forEach(System.out::println);
    }
}
```

## Supplier

Supplies a value without taking input.

### Interface:
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

### Examples:
```java
import java.util.function.Supplier;
import java.util.Random;
import java.time.LocalDateTime;

public class SupplierDemo {
    public static void main(String[] args) {
        // Constant supplier
        Supplier<String> messageSupplier = () -> "Hello, World!";
        System.out.println(messageSupplier.get());
        
        // Random number supplier
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(100);
        System.out.println("Random: " + randomSupplier.get());
        System.out.println("Random: " + randomSupplier.get());
        
        // Current time supplier
        Supplier<LocalDateTime> timeSupplier = () -> LocalDateTime.now();
        System.out.println("Current time: " + timeSupplier.get());
        
        // Object creation supplier
        Supplier<List<String>> listSupplier = () -> new ArrayList<>();
        List<String> list = listSupplier.get();
        list.add("Item");
        System.out.println("List: " + list);
        
        // Lazy evaluation
        Supplier<String> expensiveOperation = () -> {
            System.out.println("Performing expensive operation...");
            return "Result";
        };
        
        // Only called when needed
        System.out.println("Before get");
        String result = expensiveOperation.get();
        System.out.println("Result: " + result);
    }
}
```

## Other Functional Interfaces

### UnaryOperator
```java
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {
    public static void main(String[] args) {
        UnaryOperator<Integer> square = num -> num * num;
        UnaryOperator<String> uppercase = str -> str.toUpperCase();
        
        System.out.println("Square: " + square.apply(5));
        System.out.println("Uppercase: " + uppercase.apply("hello"));
    }
}
```

### BinaryOperator
```java
import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = (a, b) -> a + b;
        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;
        
        System.out.println("Add: " + add.apply(10, 20));
        System.out.println("Concat: " + concat.apply("Hello", " World"));
        
        // Max/Min
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compareTo);
        
        System.out.println("Max: " + max.apply(10, 20));
        System.out.println("Min: " + min.apply(10, 20));
    }
}
```

### BiPredicate, BiConsumer, BiFunction
```java
import java.util.function.*;

public class BiFunctionalInterfacesDemo {
    public static void main(String[] args) {
        // BiPredicate
        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
        System.out.println("Length check: " + checkLength.test("Hello", 5));
        
        // BiConsumer
        BiConsumer<String, Integer> printWithNumber = (str, num) -> 
            System.out.println(num + ": " + str);
        printWithNumber.accept("Apple", 1);
        printWithNumber.accept("Banana", 2);
        
        // BiFunction
        BiFunction<Integer, Integer, String> compare = (a, b) -> {
            if (a > b) return "First is greater";
            else if (a < b) return "Second is greater";
            else return "Equal";
        };
        System.out.println(compare.apply(10, 20));
    }
}
```

## Custom Functional Interfaces

Create your own functional interfaces for specific needs.

```java
@FunctionalInterface
interface StringOperation {
    String operate(String str);
}

@FunctionalInterface
interface MathOperation {
    double calculate(double a, double b);
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class CustomFunctionalDemo {
    public static void main(String[] args) {
        // String operation
        StringOperation reverse = str -> new StringBuilder(str).reverse().toString();
        StringOperation removeSpaces = str -> str.replaceAll(" ", "");
        
        System.out.println("Reverse: " + reverse.operate("Hello"));
        System.out.println("Remove spaces: " + removeSpaces.operate("Hello World"));
        
        // Math operation
        MathOperation power = (a, b) -> Math.pow(a, b);
        System.out.println("2^3 = " + power.calculate(2, 3));
        
        // Tri function
        TriFunction<Integer, Integer, Integer, Integer> addThree = 
            (a, b, c) -> a + b + c;
        System.out.println("Sum: " + addThree.apply(1, 2, 3));
    }
}
```

## Complete Example

```java
import java.util.*;
import java.util.function.*;

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
        return name + ", " + age + ", " + city;
    }
}

public class FunctionalInterfaceComplete {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "New York"),
            new Person("Bob", 30, "London"),
            new Person("Charlie", 20, "Paris"),
            new Person("David", 35, "New York")
        );
        
        // Predicate - filter people over 25
        Predicate<Person> isAdult = person -> person.getAge() >= 25;
        System.out.println("Adults:");
        people.stream()
              .filter(isAdult)
              .forEach(System.out::println);
        
        // Consumer - print person
        Consumer<Person> printPerson = person -> 
            System.out.println(person.getName() + " is " + person.getAge());
        
        System.out.println("\nAll people:");
        people.forEach(printPerson);
        
        // Function - extract name
        Function<Person, String> getName = Person::getName;
        System.out.println("\nNames:");
        people.stream()
              .map(getName)
              .forEach(System.out::println);
        
        // Supplier - create person
        Supplier<Person> personSupplier = () -> new Person("New Person", 25, "City");
        Person newPerson = personSupplier.get();
        System.out.println("\nNew person: " + newPerson);
        
        // BiPredicate - same city
        BiPredicate<Person, String> inCity = 
            (person, city) -> person.getCity().equals(city);
        
        System.out.println("\nPeople in New York:");
        people.stream()
              .filter(p -> inCity.test(p, "New York"))
              .forEach(System.out::println);
    }
}
```

## Exercises

### Exercise 1: Number Filters
Create predicates to filter:
- Even numbers
- Numbers > 10
- Numbers divisible by 3

### Exercise 2: String Transformers
Create functions to:
- Convert to uppercase
- Reverse string
- Get first character

### Exercise 3: List Operations
Use consumers to:
- Print list elements
- Multiply each number by 2
- Add prefix to strings

### Exercise 4: Value Generators
Create suppliers for:
- Random numbers
- Current timestamp
- UUID generation

### Exercise 5: Custom Validator
Create a custom functional interface for validation with error message

## Summary

Today you learned:
- ✅ What functional interfaces are
- ✅ @FunctionalInterface annotation
- ✅ Built-in functional interfaces (Predicate, Consumer, Function, Supplier)
- ✅ Combining functional interfaces
- ✅ Creating custom functional interfaces
- ✅ Practical use cases

Tomorrow, we'll explore **Stream API - Part 1**!

---

**Next**: [Day 17 - Stream API - Part 1](../Day17/README.md)

**Previous**: [Day 15 - Lambda Expressions](../Day15/README.md)
