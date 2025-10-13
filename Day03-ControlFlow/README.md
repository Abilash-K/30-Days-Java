# Day 03: Control Flow Statements

Welcome to Day 3! Today we'll learn about control flow statements that allow us to make decisions in our programs.

## Table of Contents
- [Introduction](#introduction)
- [if Statement](#if-statement)
- [if-else Statement](#if-else-statement)
- [if-else-if Ladder](#if-else-if-ladder)
- [Nested if Statements](#nested-if-statements)
- [switch Statement](#switch-statement)
- [Exercises](#exercises)

## Introduction

Control flow statements determine the order in which statements are executed. They allow programs to make decisions based on conditions.

## if Statement

The `if` statement executes a block of code only if a specified condition is true.

### Syntax:
```java
if (condition) {
    // Code to execute if condition is true
}
```

### Example:
```java
int age = 20;
if (age >= 18) {
    System.out.println("You are an adult");
}
```

### Single Statement (no braces needed):
```java
if (age >= 18)
    System.out.println("You are an adult");
```

**Best Practice:** Always use braces even for single statements to avoid errors.

## if-else Statement

Executes one block if the condition is true, another if it's false.

### Syntax:
```java
if (condition) {
    // Code if condition is true
} else {
    // Code if condition is false
}
```

### Example:
```java
int number = -5;
if (number >= 0) {
    System.out.println("Positive number");
} else {
    System.out.println("Negative number");
}
```

## if-else-if Ladder

Used when you need to check multiple conditions.

### Syntax:
```java
if (condition1) {
    // Code for condition1
} else if (condition2) {
    // Code for condition2
} else if (condition3) {
    // Code for condition3
} else {
    // Code if none of the above conditions are true
}
```

### Example:
```java
int marks = 85;

if (marks >= 90) {
    System.out.println("Grade: A+");
} else if (marks >= 80) {
    System.out.println("Grade: A");
} else if (marks >= 70) {
    System.out.println("Grade: B");
} else if (marks >= 60) {
    System.out.println("Grade: C");
} else {
    System.out.println("Grade: F");
}
```

## Nested if Statements

An `if` statement inside another `if` statement.

### Example:
```java
int age = 25;
boolean hasLicense = true;

if (age >= 18) {
    if (hasLicense) {
        System.out.println("You can drive");
    } else {
        System.out.println("You need a license");
    }
} else {
    System.out.println("You are too young to drive");
}
```

## switch Statement

Evaluates an expression and executes code based on matching cases.

### Syntax:
```java
switch (expression) {
    case value1:
        // Code for value1
        break;
    case value2:
        // Code for value2
        break;
    default:
        // Code if no case matches
}
```

### Example:
```java
int day = 3;
String dayName;

switch (day) {
    case 1:
        dayName = "Monday";
        break;
    case 2:
        dayName = "Tuesday";
        break;
    case 3:
        dayName = "Wednesday";
        break;
    case 4:
        dayName = "Thursday";
        break;
    case 5:
        dayName = "Friday";
        break;
    case 6:
        dayName = "Saturday";
        break;
    case 7:
        dayName = "Sunday";
        break;
    default:
        dayName = "Invalid day";
}

System.out.println(dayName);
```

### Switch with char:
```java
char grade = 'B';

switch (grade) {
    case 'A':
        System.out.println("Excellent!");
        break;
    case 'B':
        System.out.println("Good!");
        break;
    case 'C':
        System.out.println("Average");
        break;
    default:
        System.out.println("Invalid grade");
}
```

### Switch with String (Java 7+):
```java
String month = "January";

switch (month) {
    case "January":
    case "March":
    case "May":
        System.out.println("31 days");
        break;
    case "February":
        System.out.println("28 or 29 days");
        break;
    case "April":
    case "June":
        System.out.println("30 days");
        break;
    default:
        System.out.println("Invalid month");
}
```

### Fall-through Behavior:
```java
int number = 2;

switch (number) {
    case 1:
        System.out.println("One");
    case 2:
        System.out.println("Two");  // Executes
    case 3:
        System.out.println("Three"); // Also executes (fall-through)
        break;
    default:
        System.out.println("Other");
}
// Output: Two, Three
```

## Exercises

### Exercise 1: Positive, Negative, or Zero
Write a program that checks if a number is positive, negative, or zero.

### Exercise 2: Even or Odd
Create a program to determine if a number is even or odd.

### Exercise 3: Largest of Three Numbers
Write a program that finds the largest of three numbers.

### Exercise 4: Grade Calculator
Create a program that assigns grades based on marks:
- 90-100: A+
- 80-89: A
- 70-79: B
- 60-69: C
- Below 60: F

### Exercise 5: Leap Year Checker
Write a program to check if a year is a leap year.
Rules:
- Divisible by 4 AND not divisible by 100, OR
- Divisible by 400

### Exercise 6: Calculator
Create a simple calculator using switch statement that performs +, -, *, / operations.

### Exercise 7: Month Days
Write a program using switch that displays the number of days in a month.

### Exercise 8: Vowel or Consonant
Check if a given character is a vowel or consonant.

## Summary

Today you learned:
- ✅ if statements for basic decisions
- ✅ if-else for binary choices
- ✅ if-else-if ladder for multiple conditions
- ✅ Nested if statements
- ✅ switch statements for multiple cases
- ✅ Fall-through behavior in switch
- ✅ Best practices for control flow

Tomorrow, we'll explore **Loops and Iterations**!

---

**Next**: [Day 04 - Loops](../Day04-Loops/README.md)

**Previous**: [Day 02 - Variables, Data Types, and Operators](../Day02-Variables-DataTypes/README.md)
