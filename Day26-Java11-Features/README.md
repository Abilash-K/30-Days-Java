# Day 26: Java 11 - New Features

Welcome to Day 26! Today we'll explore the new features introduced in Java 11, the latest LTS (Long Term Support) version.

## Table of Contents
- [Introduction to Java 11](#introduction-to-java-11)
- [Local Variable Type Inference (var)](#local-variable-type-inference-var)
- [New String Methods](#new-string-methods)
- [New File Methods](#new-file-methods)
- [Collection to Array](#collection-to-array)
- [Predicate.not()](#predicatenot)
- [Pattern Recognition](#pattern-recognition)
- [Exercises](#exercises)

## Introduction to Java 11

Java 11 is a Long Term Support (LTS) release that includes several enhancements and new features. It was released in September 2018.

### Key Features:
- Local Variable Type Inference for Lambda Parameters
- New String methods
- New File methods
- HTTP Client API (standardized)
- Running Java files directly
- Removed and deprecated features

## Local Variable Type Inference (var)

Java 11 extends the use of `var` (introduced in Java 10) to lambda parameters.

### Basic var Usage:
```java
// Instead of
String message = "Hello, Java 11";
List<String> names = new ArrayList<>();

// You can write
var message = "Hello, Java 11";
var names = new ArrayList<String>();
```

### var in Lambda (Java 11):
```java
// Before Java 11
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach((String name) -> System.out.println(name));

// Java 11 - var in lambda
names.forEach((var name) -> System.out.println(name));

// With annotations
names.forEach((@NonNull var name) -> System.out.println(name));
```

### var Rules:
- Cannot be used for fields
- Cannot be used for method parameters (except lambdas)
- Must have an initializer
- Cannot be initialized with null

## New String Methods

Java 11 adds several useful methods to the String class.

### 1. isBlank()
Checks if a string is empty or contains only whitespace.

```java
String str1 = "";
String str2 = "  ";
String str3 = "Hello";

System.out.println(str1.isBlank());  // true
System.out.println(str2.isBlank());  // true
System.out.println(str3.isBlank());  // false
```

### 2. lines()
Returns a stream of lines from a multi-line string.

```java
String multiline = "Line 1\nLine 2\nLine 3";
multiline.lines()
    .forEach(System.out::println);

// Count non-empty lines
long count = multiline.lines()
    .filter(line -> !line.isBlank())
    .count();
```

### 3. strip(), stripLeading(), stripTrailing()
Removes whitespace (Unicode-aware, unlike trim()).

```java
String str = "  Hello World  ";

System.out.println(str.strip());          // "Hello World"
System.out.println(str.stripLeading());   // "Hello World  "
System.out.println(str.stripTrailing());  // "  Hello World"

// Difference from trim() - handles Unicode whitespace
String unicode = "\u2000Hello\u2000";
System.out.println(unicode.trim().length());   // Still has whitespace
System.out.println(unicode.strip().length());  // Properly removed
```

### 4. repeat()
Repeats a string n times.

```java
String star = "*";
System.out.println(star.repeat(5));  // "*****"

String hello = "Hello ";
System.out.println(hello.repeat(3));  // "Hello Hello Hello "
```

## New File Methods

### 1. Files.readString() and Files.writeString()

```java
// Write string to file
Path path = Path.of("test.txt");
Files.writeString(path, "Hello, Java 11!");

// Read string from file
String content = Files.readString(path);
System.out.println(content);  // "Hello, Java 11!"

// With specific encoding
Files.writeString(path, "Hello", StandardCharsets.UTF_8);
String content2 = Files.readString(path, StandardCharsets.UTF_8);
```

### 2. Files.isSameFile()
```java
Path path1 = Path.of("file1.txt");
Path path2 = Path.of("file2.txt");
boolean same = Files.isSameFile(path1, path2);
```

## Collection to Array

Java 11 adds a new method to convert collections to arrays more easily.

```java
// Before Java 11
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
String[] array1 = names.toArray(new String[0]);

// Java 11 - IntFunction
String[] array2 = names.toArray(String[]::new);

// More examples
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Integer[] numArray = numbers.toArray(Integer[]::new);
```

## Predicate.not()

A convenient method to negate predicates.

```java
List<String> names = Arrays.asList("Alice", "", "Bob", "  ", "Charlie");

// Before Java 11
List<String> nonBlank1 = names.stream()
    .filter(s -> !s.isBlank())
    .collect(Collectors.toList());

// Java 11 - using Predicate.not()
List<String> nonBlank2 = names.stream()
    .filter(Predicate.not(String::isBlank))
    .collect(Collectors.toList());
```

## Pattern Recognition

Java 11 adds `asMatchPredicate()` to Pattern class.

```java
Pattern pattern = Pattern.compile("[a-z]+");

List<String> words = Arrays.asList("hello", "WORLD", "java", "123");

// Filter using pattern
List<String> lowercase = words.stream()
    .filter(pattern.asMatchPredicate())
    .collect(Collectors.toList());
// Result: ["hello", "java"]
```

## Running Java Files Directly

Java 11 allows running single-file programs without explicit compilation.

```bash
# Before Java 11
javac HelloWorld.java
java HelloWorld

# Java 11
java HelloWorld.java
```

This is called "Launch Single-File Source-Code Programs" (JEP 330).

## Optional Enhancements

### isEmpty()
```java
Optional<String> opt1 = Optional.of("Hello");
Optional<String> opt2 = Optional.empty();

System.out.println(opt1.isEmpty());  // false
System.out.println(opt2.isEmpty());  // true
```

## Removed and Deprecated Features

### Removed:
- Java EE and CORBA modules
- JavaFX (moved to separate project)
- Nashorn JavaScript Engine (deprecated)

### Deprecated:
- Pack200 tools and API
- Applet API

## Practical Examples

### Example 1: String Processing
```java
String data = "  Line 1  \n  Line 2  \n  Line 3  ";

List<String> cleanLines = data.lines()
    .map(String::strip)
    .filter(Predicate.not(String::isBlank))
    .collect(Collectors.toList());

cleanLines.forEach(System.out::println);
```

### Example 2: File Operations
```java
Path tempFile = Files.createTempFile("test", ".txt");

// Write
Files.writeString(tempFile, "Hello\nWorld\n");

// Read and process
String content = Files.readString(tempFile);
long lineCount = content.lines().count();

System.out.println("Lines: " + lineCount);

// Cleanup
Files.deleteIfExists(tempFile);
```

### Example 3: Collection Filtering
```java
List<String> items = Arrays.asList(
    "apple", "banana", "", "cherry", "  ", "date"
);

String[] nonEmptyArray = items.stream()
    .filter(Predicate.not(String::isBlank))
    .toArray(String[]::new);

System.out.println(Arrays.toString(nonEmptyArray));
```

## Exercises

### Exercise 1: String Methods
Create a program that uses all new String methods:
- isBlank()
- lines()
- strip()
- repeat()

### Exercise 2: File Processing
Read a text file and:
- Count non-blank lines
- Remove leading/trailing whitespace from each line
- Write cleaned content to a new file

### Exercise 3: Lambda with var
Rewrite existing lambdas to use var for parameters.

### Exercise 4: Collection Processing
Given a list of strings:
- Filter out blank entries
- Convert to uppercase
- Convert to array using new syntax

### Exercise 5: Pattern Matching
Use Pattern.asMatchPredicate() to filter:
- Email addresses
- Phone numbers
- URLs

## Summary

Today you learned:
- ✅ Local variable type inference with var in lambdas
- ✅ New String methods (isBlank, lines, strip, repeat)
- ✅ New File methods for easier I/O
- ✅ Simplified collection to array conversion
- ✅ Predicate.not() for cleaner code
- ✅ Running Java files directly
- ✅ What was removed and deprecated

Tomorrow, we'll explore **Java 11 HTTP Client API**!

---

**Next**: [Day 27 - Java 11 HTTP Client API](../Day27-HTTP-Client/README.md)

**Previous**: [Day 25 - Multithreading Advanced](../Day25-Multithreading-Advanced/README.md)
