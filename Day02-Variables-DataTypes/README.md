# Day 02: Variables, Data Types, and Operators

Welcome to Day 2! Today we'll explore variables, data types, and operators in Java - the fundamental building blocks of any program.

## Table of Contents
- [Variables](#variables)
- [Data Types](#data-types)
- [Type Conversion](#type-conversion)
- [Operators](#operators)
- [Constants](#constants)
- [Naming Conventions](#naming-conventions)
- [Exercises](#exercises)

## Variables

A variable is a container that holds data that can be changed during program execution.

### Variable Declaration
```java
dataType variableName;
```

### Variable Initialization
```java
dataType variableName = value;
```

### Example:
```java
int age;              // Declaration
age = 25;             // Initialization

int count = 0;        // Declaration and initialization together

String name = "John"; // String variable
double price = 19.99; // Double variable
```

### Multiple Variable Declaration
```java
int x, y, z;
int a = 5, b = 10, c = 15;

// Not recommended - different types
// int x, double y;  // Compilation error
```

## Data Types

Java is a statically-typed language, meaning variables must be declared with a type.

### 1. Primitive Data Types (8 types)

#### Integer Types

| Type | Size | Range | Default |
|------|------|-------|---------|
| `byte` | 8 bits | -128 to 127 | 0 |
| `short` | 16 bits | -32,768 to 32,767 | 0 |
| `int` | 32 bits | -2³¹ to 2³¹-1 | 0 |
| `long` | 64 bits | -2⁶³ to 2⁶³-1 | 0L |

```java
byte age = 30;
short year = 2024;
int population = 1000000;
long distanceToSun = 149600000L; // Note the 'L' suffix
```

#### Floating-Point Types

| Type | Size | Precision | Default |
|------|------|-----------|---------|
| `float` | 32 bits | ~6-7 decimal digits | 0.0f |
| `double` | 64 bits | ~15 decimal digits | 0.0d |

```java
float temperature = 98.6f;  // Note the 'f' suffix
double pi = 3.14159265359;
```

#### Character Type

| Type | Size | Range | Default |
|------|------|-------|---------|
| `char` | 16 bits | 0 to 65,535 (Unicode) | '\u0000' |

```java
char grade = 'A';
char symbol = '$';
char unicode = '\u0041';  // 'A' in Unicode
```

#### Boolean Type

| Type | Size | Values | Default |
|------|------|--------|---------|
| `boolean` | 1 bit (implementation-dependent) | true, false | false |

```java
boolean isJavaFun = true;
boolean isCodingHard = false;
```

### 2. Reference Data Types

Reference types refer to objects and include:
- Classes
- Interfaces
- Arrays
- Strings (special reference type)

```java
String message = "Hello, Java!";
int[] numbers = {1, 2, 3, 4, 5};
Object obj = new Object();
```

### Primitive vs Reference Types

**Primitive Types:**
- Store actual values
- Fixed size
- Stored in stack memory
- Cannot be null

**Reference Types:**
- Store memory addresses
- Variable size
- Stored in heap memory
- Can be null

```java
int x = 10;           // Primitive - stores value 10
String s = "Hello";   // Reference - stores memory address

int y = x;            // Copies the value
String t = s;         // Copies the reference
```

## Type Conversion

### 1. Implicit Type Conversion (Widening)
Automatic conversion from smaller to larger type.

```java
byte → short → int → long → float → double
char → int
```

Example:
```java
int intValue = 100;
long longValue = intValue;        // int to long (automatic)
double doubleValue = intValue;    // int to double (automatic)

System.out.println(longValue);    // 100
System.out.println(doubleValue);  // 100.0
```

### 2. Explicit Type Conversion (Narrowing/Casting)
Manual conversion from larger to smaller type.

```java
dataType variableName = (dataType) value;
```

Example:
```java
double doubleValue = 9.78;
int intValue = (int) doubleValue;  // Explicit cast
System.out.println(intValue);      // 9 (decimal part lost)

long longValue = 1000L;
int intValue2 = (int) longValue;   // Explicit cast

// Potential data loss
int largeNumber = 130;
byte byteValue = (byte) largeNumber;  // May cause overflow
```

### Type Promotion in Expressions
```java
byte a = 10;
byte b = 20;
// byte c = a + b;  // Compilation error - result is int

int c = a + b;      // Correct - promoted to int
```

## Operators

### 1. Arithmetic Operators

| Operator | Name | Example |
|----------|------|---------|
| `+` | Addition | `a + b` |
| `-` | Subtraction | `a - b` |
| `*` | Multiplication | `a * b` |
| `/` | Division | `a / b` |
| `%` | Modulus (Remainder) | `a % b` |

```java
int a = 10, b = 3;
System.out.println(a + b);  // 13
System.out.println(a - b);  // 7
System.out.println(a * b);  // 30
System.out.println(a / b);  // 3 (integer division)
System.out.println(a % b);  // 1 (remainder)

double x = 10.0, y = 3.0;
System.out.println(x / y);  // 3.333... (floating-point division)
```

### 2. Unary Operators

| Operator | Name | Example |
|----------|------|---------|
| `+` | Unary plus | `+a` |
| `-` | Unary minus | `-a` |
| `++` | Increment | `++a` or `a++` |
| `--` | Decrement | `--a` or `a--` |
| `!` | Logical NOT | `!a` |

```java
int x = 10;
System.out.println(+x);   // 10
System.out.println(-x);   // -10

// Increment
int y = 5;
System.out.println(++y);  // 6 (pre-increment: increment then use)
System.out.println(y);    // 6

int z = 5;
System.out.println(z++);  // 5 (post-increment: use then increment)
System.out.println(z);    // 6

// Decrement works similarly
int w = 5;
System.out.println(--w);  // 4 (pre-decrement)
System.out.println(w--);  // 4 (post-decrement)
System.out.println(w);    // 3
```

### 3. Relational Operators

| Operator | Name | Example |
|----------|------|---------|
| `==` | Equal to | `a == b` |
| `!=` | Not equal to | `a != b` |
| `>` | Greater than | `a > b` |
| `<` | Less than | `a < b` |
| `>=` | Greater than or equal | `a >= b` |
| `<=` | Less than or equal | `a <= b` |

```java
int a = 10, b = 20;
System.out.println(a == b);  // false
System.out.println(a != b);  // true
System.out.println(a > b);   // false
System.out.println(a < b);   // true
System.out.println(a >= 10); // true
System.out.println(b <= 20); // true
```

### 4. Logical Operators

| Operator | Name | Example |
|----------|------|---------|
| `&&` | Logical AND | `a && b` |
| `\|\|` | Logical OR | `a \|\| b` |
| `!` | Logical NOT | `!a` |

```java
boolean x = true, y = false;
System.out.println(x && y);   // false (both must be true)
System.out.println(x || y);   // true (at least one is true)
System.out.println(!x);       // false (negation)

// Short-circuit evaluation
int a = 10, b = 20;
if (a > 5 && b < 30) {
    System.out.println("Both conditions are true");
}
```

### 5. Assignment Operators

| Operator | Example | Equivalent to |
|----------|---------|---------------|
| `=` | `a = 5` | `a = 5` |
| `+=` | `a += 3` | `a = a + 3` |
| `-=` | `a -= 3` | `a = a - 3` |
| `*=` | `a *= 3` | `a = a * 3` |
| `/=` | `a /= 3` | `a = a / 3` |
| `%=` | `a %= 3` | `a = a % 3` |

```java
int x = 10;
x += 5;   // x = x + 5 → x = 15
x -= 3;   // x = x - 3 → x = 12
x *= 2;   // x = x * 2 → x = 24
x /= 4;   // x = x / 4 → x = 6
x %= 4;   // x = x % 4 → x = 2
```

### 6. Bitwise Operators

| Operator | Name | Example |
|----------|------|---------|
| `&` | AND | `a & b` |
| `\|` | OR | `a \| b` |
| `^` | XOR | `a ^ b` |
| `~` | NOT | `~a` |
| `<<` | Left shift | `a << 2` |
| `>>` | Right shift | `a >> 2` |
| `>>>` | Unsigned right shift | `a >>> 2` |

```java
int a = 5;   // Binary: 0101
int b = 3;   // Binary: 0011

System.out.println(a & b);   // 1 (0001)
System.out.println(a | b);   // 7 (0111)
System.out.println(a ^ b);   // 6 (0110)
System.out.println(~a);      // -6 (inverts all bits)
System.out.println(a << 1);  // 10 (1010)
System.out.println(a >> 1);  // 2 (0010)
```

### 7. Ternary Operator

```java
condition ? valueIfTrue : valueIfFalse
```

```java
int age = 20;
String status = (age >= 18) ? "Adult" : "Minor";
System.out.println(status);  // "Adult"

int max = (a > b) ? a : b;
```

### Operator Precedence

From highest to lowest:
1. Postfix: `expr++`, `expr--`
2. Unary: `++expr`, `--expr`, `+expr`, `-expr`, `!`, `~`
3. Multiplicative: `*`, `/`, `%`
4. Additive: `+`, `-`
5. Shift: `<<`, `>>`, `>>>`
6. Relational: `<`, `>`, `<=`, `>=`, `instanceof`
7. Equality: `==`, `!=`
8. Bitwise AND: `&`
9. Bitwise XOR: `^`
10. Bitwise OR: `|`
11. Logical AND: `&&`
12. Logical OR: `||`
13. Ternary: `? :`
14. Assignment: `=`, `+=`, `-=`, etc.

```java
int result = 10 + 5 * 2;  // 20, not 30 (multiplication first)
int result2 = (10 + 5) * 2;  // 30 (parentheses change order)
```

## Constants

Constants are variables whose values cannot be changed.

### Using `final` Keyword
```java
final dataType CONSTANT_NAME = value;
```

```java
final double PI = 3.14159;
final int MAX_SIZE = 100;
final String COMPANY_NAME = "TechCorp";

// PI = 3.14;  // Compilation error - cannot reassign
```

### Naming Convention
- Use UPPERCASE letters
- Separate words with underscores
- Descriptive names

```java
final int MAX_STUDENTS = 50;
final double TAX_RATE = 0.08;
final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
```

## Naming Conventions

### Variables and Methods
- Use camelCase
- Start with lowercase letter
- Descriptive names

```java
int studentAge;
double accountBalance;
String firstName;
```

### Classes and Interfaces
- Use PascalCase
- Start with uppercase letter

```java
class StudentRecord { }
interface PaymentProcessor { }
```

### Constants
- Use UPPERCASE
- Separate words with underscores

```java
final int MAX_VALUE = 100;
final double PI = 3.14159;
```

### Packages
- Use lowercase
- Reverse domain name

```java
package com.company.project;
package org.example.utils;
```

## Best Practices

1. **Use meaningful names**
   ```java
   // Bad
   int x = 25;
   
   // Good
   int studentAge = 25;
   ```

2. **Initialize variables**
   ```java
   int count = 0;  // Good
   int total;      // May cause issues if used before initialization
   ```

3. **Use appropriate data types**
   ```java
   byte age = 30;          // Good for small numbers
   long population = 7_800_000_000L;  // Good for large numbers
   ```

4. **Use constants for fixed values**
   ```java
   final double TAX_RATE = 0.08;
   double tax = price * TAX_RATE;
   ```

5. **Use underscores in numeric literals (Java 7+)**
   ```java
   int million = 1_000_000;
   long creditCardNumber = 1234_5678_9012_3456L;
   ```

## Exercises

### Exercise 1: Variable Declaration and Initialization
Create a program that declares and initializes variables of different types:
- Your name (String)
- Your age (int)
- Your height in meters (double)
- Whether you like Java (boolean)
- Your grade (char)

Print all values with labels.

### Exercise 2: Arithmetic Operations
Write a program that:
1. Takes two integers (you can hardcode them)
2. Performs and prints all arithmetic operations (+, -, *, /, %)
3. Shows the results

### Exercise 3: Temperature Converter
Create a program that converts temperature from Celsius to Fahrenheit.
Formula: F = (C × 9/5) + 32

Test with:
- 0°C
- 100°C
- 37°C

### Exercise 4: Circle Calculations
Write a program that calculates:
- Area of a circle (πr²)
- Circumference of a circle (2πr)

Use a constant for PI. Test with radius = 5.

### Exercise 5: Type Conversion
Demonstrate:
1. Implicit type conversion (widening)
2. Explicit type conversion (casting)
3. Show data loss when narrowing

### Exercise 6: Operator Precedence
Create a program that demonstrates operator precedence with different expressions.
Show the result with and without parentheses.

### Exercise 7: Increment/Decrement
Write a program that demonstrates the difference between:
- Pre-increment (++x)
- Post-increment (x++)
- Pre-decrement (--x)
- Post-decrement (x--)

### Exercise 8: Logical Operators
Create a program that checks if a number is:
- Within a range (e.g., 1-100)
- Even or odd
- Divisible by both 3 and 5

Use logical operators (&&, ||, !).

## Summary

Today you learned:
- ✅ Variables and how to declare them
- ✅ Java's 8 primitive data types
- ✅ Reference types vs primitive types
- ✅ Type conversion (implicit and explicit)
- ✅ All types of operators in Java
- ✅ Constants using the final keyword
- ✅ Java naming conventions
- ✅ Best practices for variables and operators

Tomorrow, we'll explore **Control Flow Statements** including if-else and switch!

---

**Next**: [Day 03 - Control Flow Statements](../Day03-ControlFlow/README.md)

**Previous**: [Day 01 - Introduction to Java](../Day01-Introduction/README.md)
