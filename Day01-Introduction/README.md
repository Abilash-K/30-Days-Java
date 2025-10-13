# Day 01: Introduction to Java

Welcome to Day 1 of the 30 Days of Java Challenge! Today, we'll set up our development environment and write our first Java programs.

## Table of Contents
- [What is Java?](#what-is-java)
- [Java Features](#java-features)
- [Java Versions](#java-versions)
- [Setting Up Development Environment](#setting-up-development-environment)
- [Your First Java Program](#your-first-java-program)
- [Basic Java Syntax](#basic-java-syntax)
- [Java Program Structure](#java-program-structure)
- [Compilation and Execution](#compilation-and-execution)
- [Exercises](#exercises)

## What is Java?

Java is a high-level, class-based, object-oriented programming language designed to have as few implementation dependencies as possible. It was developed by James Gosling at Sun Microsystems (now owned by Oracle) and released in 1995.

### Key Characteristics:
- **Platform Independent**: "Write Once, Run Anywhere" (WORA)
- **Object-Oriented**: Everything is an object in Java
- **Simple and Secure**: Automatic memory management and strong type checking
- **Robust**: Exception handling and garbage collection
- **Multithreaded**: Built-in support for concurrent programming
- **High Performance**: JIT (Just-In-Time) compilation

## Java Features

### 1. Platform Independence
Java code is compiled into bytecode that runs on the Java Virtual Machine (JVM). This makes Java programs platform-independent.

```
Source Code (.java) → Compiler → Bytecode (.class) → JVM → Machine Code
```

### 2. Object-Oriented
Java follows OOP principles:
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

### 3. Automatic Memory Management
Java has a garbage collector that automatically manages memory allocation and deallocation.

### 4. Rich Standard Library
Java comes with extensive APIs for networking, I/O, utilities, and more.

## Java Versions

### Important Java Versions:
- **Java 1.0** (1996): First public release
- **Java 5** (2004): Generics, enums, annotations
- **Java 7** (2011): Try-with-resources, diamond operator
- **Java 8** (2014): Lambda expressions, Stream API, Optional
- **Java 9** (2017): Module system
- **Java 11** (2018): LTS version with String methods, HTTP Client
- **Java 17** (2021): Latest LTS version

This course focuses on **Java 8 and Java 11** features.

## Setting Up Development Environment

### Step 1: Install JDK

#### Windows:
1. Download JDK 11 from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. Run the installer
3. Set JAVA_HOME environment variable:
   ```
   JAVA_HOME = C:\Program Files\Java\jdk-11
   ```
4. Add to PATH: `%JAVA_HOME%\bin`

#### macOS:
```bash
# Using Homebrew
brew install openjdk@11

# Set JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-11-jdk

# Verify installation
java -version
javac -version
```

### Step 2: Verify Installation
```bash
java -version
# Output should show: java version "11.x.x"

javac -version
# Output should show: javac 11.x.x
```

### Step 3: Choose an IDE

#### IntelliJ IDEA (Recommended)
- Download from [JetBrains](https://www.jetbrains.com/idea/)
- Community Edition is free
- Excellent Java support

#### Eclipse
- Download from [Eclipse.org](https://www.eclipse.org/)
- Free and open-source
- Popular in enterprise environments

#### VS Code
- Download from [code.visualstudio.com](https://code.visualstudio.com/)
- Install Java Extension Pack
- Lightweight and versatile

## Your First Java Program

### Hello World Program

Create a file named `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Compile and Run:
```bash
# Compile
javac HelloWorld.java

# Run
java HelloWorld
```

**Output:**
```
Hello, World!
```

## Basic Java Syntax

### 1. Class Declaration
```java
public class ClassName {
    // Class body
}
```
- Every Java program must have at least one class
- Class name should match the filename
- Use PascalCase for class names

### 2. Main Method
```java
public static void main(String[] args) {
    // Program entry point
}
```
- Entry point of every Java application
- Must be public, static, and void
- Takes String array as parameter

### 3. Statements
```java
System.out.println("Hello");  // Print with newline
System.out.print("Hello");    // Print without newline
```
- Every statement ends with a semicolon (;)
- Case-sensitive language

### 4. Comments
```java
// Single-line comment

/*
 * Multi-line comment
 * Can span multiple lines
 */

/**
 * Documentation comment (Javadoc)
 * Used to generate API documentation
 * @author Your Name
 */
```

### 5. Identifiers
Rules for naming:
- Must start with a letter, $, or _
- Cannot be a Java keyword
- Case-sensitive
- No spaces allowed

**Valid identifiers:**
```java
myVariable
_value
$price
userName123
```

**Invalid identifiers:**
```java
123abc      // Cannot start with digit
my-var      // Hyphen not allowed
class       // Java keyword
```

### 6. Keywords
Java reserved words:
```
abstract    continue    for          new          switch
assert      default     goto         package      synchronized
boolean     do          if           private      this
break       double      implements   protected    throw
byte        else        import       public       throws
case        enum        instanceof   return       transient
catch       extends     int          short        try
char        final       interface    static       void
class       finally     long         strictfp     volatile
const       float       native       super        while
```

## Java Program Structure

### Complete Example with Comments:

```java
// 1. Package declaration (optional)
package com.example.day01;

// 2. Import statements
import java.util.Scanner;

// 3. Class declaration
public class BasicProgram {
    
    // 4. Class variables (fields)
    private String message;
    
    // 5. Constructor
    public BasicProgram(String message) {
        this.message = message;
    }
    
    // 6. Methods
    public void displayMessage() {
        System.out.println(message);
    }
    
    // 7. Main method (entry point)
    public static void main(String[] args) {
        BasicProgram program = new BasicProgram("Welcome to Java!");
        program.displayMessage();
    }
}
```

## Compilation and Execution

### Java Compilation Process:

1. **Write Source Code**: Create `.java` file
2. **Compile**: `javac` compiler converts to bytecode (`.class`)
3. **Execute**: JVM interprets bytecode and runs the program

### Example:
```bash
# Create and navigate to directory
mkdir Day01Examples
cd Day01Examples

# Create Java file
# Write your code in HelloWorld.java

# Compile
javac HelloWorld.java

# This creates HelloWorld.class

# Run
java HelloWorld
```

### Common Compilation Errors:

1. **File name doesn't match class name**
   ```
   Error: class HelloWorld is public, should be declared in a file named HelloWorld.java
   ```

2. **Missing semicolon**
   ```
   Error: ';' expected
   ```

3. **Mismatched braces**
   ```
   Error: reached end of file while parsing
   ```

## Exercises

### Exercise 1: Basic Hello World Variations
Create a program that:
1. Prints "Hello, Java!" on one line
2. Prints your name on the next line
3. Prints "Welcome to 30 Days of Java Challenge!" on the third line

### Exercise 2: Multiple Print Statements
Write a program that displays the following pattern:
```
*
**
***
****
*****
```

### Exercise 3: Personal Information
Create a program that displays:
- Your name
- Your age
- Your favorite programming language
- Why you're learning Java

Format the output nicely with labels.

### Exercise 4: Comments Practice
Write a well-documented Java program that:
1. Uses single-line comments
2. Uses multi-line comments
3. Uses Javadoc comments
4. Prints "Learning Java is fun!"

### Exercise 5: Explore System.out
Research and use these methods:
- `System.out.println()`
- `System.out.print()`
- `System.out.printf()`

Create examples demonstrating each method.

### Exercise 6: ASCII Art
Create a program that displays ASCII art of your choice. For example:
```
  /\_/\
 ( o.o )
  > ^ <
```

## Solutions

Solutions to these exercises can be found in the [exercises](./exercises) folder.

## Additional Resources

- [Official Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorial by Oracle](https://docs.oracle.com/javase/tutorial/)
- [Java Language Specification](https://docs.oracle.com/javase/specs/)

## Summary

Today you learned:
- ✅ What Java is and its key features
- ✅ How to set up your development environment
- ✅ Basic Java syntax and structure
- ✅ How to write, compile, and run Java programs
- ✅ Java naming conventions and keywords

Tomorrow, we'll dive into **Variables, Data Types, and Operators**!

---

**Next**: [Day 02 - Variables, Data Types, and Operators](../Day02-Variables-DataTypes/README.md)

**Previous**: [Main README](../README.md)
