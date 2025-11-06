# Day 28: Java 11 - Additional Features

Welcome to Day 28! Today we'll explore additional Java 11 features.

## Table of Contents
- [String Methods](#string-methods)
- [Collection toArray()](#collection-toarray)
- [Files Methods](#files-methods)
- [Optional isEmpty()](#optional-isempty)
- [Local Variable Syntax](#local-variable-syntax)
- [Exercises](#exercises)

## String Methods

New string methods in Java 11.

```java
public class StringMethodsDemo {
    public static void main(String[] args) {
        String text = "  Hello World  ";
        
        // isBlank() - checks if string is empty or whitespace
        System.out.println("Is blank: " + text.isBlank());
        System.out.println("Empty is blank: " + "".isBlank());
        System.out.println("Spaces is blank: " + "   ".isBlank());
        
        // strip(), stripLeading(), stripTrailing()
        System.out.println("Strip: '" + text.strip() + "'");
        System.out.println("Strip leading: '" + text.stripLeading() + "'");
        System.out.println("Strip trailing: '" + text.stripTrailing() + "'");
        
        // lines() - split by line terminators
        String multiline = "Line 1\nLine 2\nLine 3";
        multiline.lines().forEach(System.out::println);
        
        // repeat() - repeat string n times
        System.out.println("Ha".repeat(3));  // HaHaHa
        System.out.println("-".repeat(20));  // --------------------
    }
}
```

## Collection toArray()

Simplified toArray() method.

```java
import java.util.*;

public class CollectionToArrayDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        
        // Java 11 - simplified
        String[] array = list.toArray(String[]::new);
        System.out.println(Arrays.toString(array));
        
        // Java 10 and before
        String[] oldWay = list.toArray(new String[0]);
    }
}
```

## Files Methods

New file methods.

```java
import java.nio.file.*;
import java.io.IOException;

public class FilesMethodsDemo {
    public static void main(String[] args) {
        try {
            // writeString() - write string to file
            Path path = Paths.get("test.txt");
            Files.writeString(path, "Hello, Java 11!");
            
            // readString() - read entire file as string
            String content = Files.readString(path);
            System.out.println("Content: " + content);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Optional isEmpty()

Check if Optional is empty.

```java
import java.util.Optional;

public class OptionalIsEmptyDemo {
    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();
        Optional<String> value = Optional.of("Hello");
        
        // isEmpty() - Java 11
        System.out.println("Empty: " + empty.isEmpty());
        System.out.println("Has value: " + value.isEmpty());
        
        // Before Java 11
        System.out.println("Not present: " + !empty.isPresent());
    }
}
```

## Local Variable Syntax

`var` in lambda parameters (Java 11).

```java
import java.util.*;
import java.util.function.*;

public class LambdaVarDemo {
    public static void main(String[] args) {
        // var in lambda (Java 11)
        BiFunction<Integer, Integer, Integer> add = (var a, var b) -> a + b;
        System.out.println("Sum: " + add.apply(5, 3));
        
        // With annotations
        BiFunction<String, String, String> concat = 
            (@NonNull var a, @NonNull var b) -> a + b;
        
        List<String> list = Arrays.asList("A", "B", "C");
        list.forEach((var item) -> System.out.println(item));
    }
}

@interface NonNull {}
```

## HTTP Client (Recap)

Quick HTTP client example.

```java
import java.net.http.*;
import java.net.*;

public class HttpClientQuick {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com"))
            .build();
        
        HttpResponse<String> response = 
            client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }
}
```

## Exercises

### Exercise 1: String Processing
Use new string methods for text processing

### Exercise 2: File Operations
Read/write files using new Files methods

### Exercise 3: Optional Handling
Use isEmpty() in Optional chains

### Exercise 4: Lambda Variables
Use var in lambda expressions

### Exercise 5: HTTP Client
Make GET and POST requests

## Summary

Today you learned:
- ✅ New String methods
- ✅ Collection toArray() improvements
- ✅ Files methods
- ✅ Optional isEmpty()
- ✅ var in lambdas
- ✅ HTTP Client overview

Tomorrow, we'll explore **Design Patterns**!

---

**Next**: [Day 29 - Design Patterns](../Day29-Design-Patterns/README.md)

**Previous**: [Day 27 - HTTP Client API](../Day27-HTTP-Client/README.md)
