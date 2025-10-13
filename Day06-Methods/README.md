# Day 06: Methods and Method Overloading

Welcome to Day 6! Today we'll learn about methods - reusable blocks of code that perform specific tasks.

## Table of Contents
- [What are Methods?](#what-are-methods)
- [Method Declaration](#method-declaration)
- [Method Parameters](#method-parameters)
- [Return Types](#return-types)
- [Method Overloading](#method-overloading)
- [Variable Arguments (Varargs)](#variable-arguments-varargs)
- [Recursion](#recursion)
- [Exercises](#exercises)

## What are Methods?

Methods are blocks of code that perform a specific task. They help organize code, promote reusability, and make programs easier to understand and maintain.

### Benefits of Methods:
- **Code Reusability**: Write once, use multiple times
- **Modularity**: Break complex problems into smaller parts
- **Readability**: Make code more organized and understandable
- **Maintainability**: Easier to update and debug

## Method Declaration

### Syntax:
```java
accessModifier returnType methodName(parameters) {
    // Method body
    return value; // if returnType is not void
}
```

### Components:
1. **Access Modifier**: public, private, protected, or default
2. **Return Type**: Data type of the value returned (or void)
3. **Method Name**: Identifier following naming conventions
4. **Parameters**: Input values (optional)
5. **Method Body**: Code to execute

### Example:
```java
public class Calculator {
    // Method with no parameters and no return value
    public void displayWelcome() {
        System.out.println("Welcome to Calculator!");
    }
    
    // Method with parameters and return value
    public int add(int a, int b) {
        return a + b;
    }
    
    // Method with no parameters but has return value
    public String getVersion() {
        return "1.0";
    }
}
```

## Method Parameters

### Single Parameter:
```java
public void greet(String name) {
    System.out.println("Hello, " + name + "!");
}
```

### Multiple Parameters:
```java
public int multiply(int x, int y) {
    return x * y;
}

public void displayInfo(String name, int age, double salary) {
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Salary: $" + salary);
}
```

### Pass by Value:
Java is strictly pass-by-value. For primitive types, the value is copied. For objects, the reference value is copied.

```java
public class ParameterDemo {
    public void modifyPrimitive(int num) {
        num = num * 2;  // Changes local copy only
    }
    
    public void modifyObject(StringBuilder sb) {
        sb.append(" World");  // Modifies the actual object
    }
    
    public static void main(String[] args) {
        ParameterDemo demo = new ParameterDemo();
        
        int value = 5;
        demo.modifyPrimitive(value);
        System.out.println(value);  // Still 5
        
        StringBuilder text = new StringBuilder("Hello");
        demo.modifyObject(text);
        System.out.println(text);  // "Hello World"
    }
}
```

## Return Types

### Void Methods:
Methods that don't return a value.

```java
public void printMessage(String message) {
    System.out.println(message);
}
```

### Methods Returning Primitive Types:
```java
public int getAge() {
    return 25;
}

public double calculateArea(double radius) {
    return Math.PI * radius * radius;
}

public boolean isEven(int number) {
    return number % 2 == 0;
}
```

### Methods Returning Objects:
```java
public String getFullName(String firstName, String lastName) {
    return firstName + " " + lastName;
}

public int[] createArray(int size) {
    return new int[size];
}
```

### Multiple Return Statements:
```java
public String getGrade(int score) {
    if (score >= 90) {
        return "A";
    } else if (score >= 80) {
        return "B";
    } else if (score >= 70) {
        return "C";
    } else if (score >= 60) {
        return "D";
    } else {
        return "F";
    }
}
```

## Method Overloading

Method overloading allows multiple methods with the same name but different parameters.

### Rules for Method Overloading:
1. Method name must be the same
2. Parameter list must be different (type, number, or order)
3. Return type alone is not sufficient for overloading

### Example 1: Different Number of Parameters
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public int add(int a, int b, int c, int d) {
        return a + b + c + d;
    }
}
```

### Example 2: Different Parameter Types
```java
public class Printer {
    public void print(int num) {
        System.out.println("Integer: " + num);
    }
    
    public void print(double num) {
        System.out.println("Double: " + num);
    }
    
    public void print(String text) {
        System.out.println("String: " + text);
    }
    
    public void print(boolean value) {
        System.out.println("Boolean: " + value);
    }
}
```

### Example 3: Different Parameter Order
```java
public class Display {
    public void show(String name, int age) {
        System.out.println(name + " is " + age + " years old");
    }
    
    public void show(int age, String name) {
        System.out.println(name + " is " + age + " years old");
    }
}
```

### Complete Overloading Example:
```java
public class MathOperations {
    // Add two integers
    public int add(int a, int b) {
        return a + b;
    }
    
    // Add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Add two doubles
    public double add(double a, double b) {
        return a + b;
    }
    
    // Add array of integers
    public int add(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}

// Usage
MathOperations math = new MathOperations();
System.out.println(math.add(5, 10));           // 15
System.out.println(math.add(5, 10, 15));       // 30
System.out.println(math.add(5.5, 10.5));       // 16.0
System.out.println(math.add(new int[]{1,2,3})); // 6
```

## Variable Arguments (Varargs)

Varargs allow methods to accept variable number of arguments.

### Syntax:
```java
returnType methodName(dataType... variableName) {
    // Method body
}
```

### Example:
```java
public class VarargsDemo {
    public int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
    
    public void printNames(String... names) {
        for (String name : names) {
            System.out.println(name);
        }
    }
    
    public static void main(String[] args) {
        VarargsDemo demo = new VarargsDemo();
        
        System.out.println(demo.sum(1, 2));           // 3
        System.out.println(demo.sum(1, 2, 3));        // 6
        System.out.println(demo.sum(1, 2, 3, 4, 5));  // 15
        
        demo.printNames("Alice", "Bob", "Charlie");
    }
}
```

### Varargs Rules:
1. Only one varargs parameter per method
2. Varargs must be the last parameter
3. Can have other parameters before varargs

```java
public void process(String prefix, int... numbers) {
    System.out.print(prefix + ": ");
    for (int num : numbers) {
        System.out.print(num + " ");
    }
    System.out.println();
}
```

## Recursion

A method that calls itself is called a recursive method.

### Example 1: Factorial
```java
public class RecursionDemo {
    public int factorial(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case
        return n * factorial(n - 1);
    }
}
```

### Example 2: Fibonacci
```java
public int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

### Example 3: Sum of Digits
```java
public int sumOfDigits(int num) {
    if (num == 0) {
        return 0;
    }
    return (num % 10) + sumOfDigits(num / 10);
}
```

## Complete Example

```java
public class StringUtility {
    // Method with no parameters
    public void displayMenu() {
        System.out.println("=== String Utility ===");
        System.out.println("1. Reverse String");
        System.out.println("2. Count Characters");
        System.out.println("3. Check Palindrome");
    }
    
    // Method with parameter and return value
    public String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }
    
    // Overloaded method
    public String reverseString(String str, boolean ignoreSpaces) {
        if (ignoreSpaces) {
            str = str.replaceAll(" ", "");
        }
        return reverseString(str);
    }
    
    // Method returning boolean
    public boolean isPalindrome(String str) {
        String reversed = reverseString(str);
        return str.equalsIgnoreCase(reversed);
    }
    
    // Method with varargs
    public String concatenate(String... strings) {
        StringBuilder result = new StringBuilder();
        for (String s : strings) {
            result.append(s);
        }
        return result.toString();
    }
    
    // Recursive method
    public String reverseRecursive(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        StringUtility util = new StringUtility();
        
        util.displayMenu();
        
        String text = "Hello";
        System.out.println("Original: " + text);
        System.out.println("Reversed: " + util.reverseString(text));
        
        String palindrome = "radar";
        System.out.println(palindrome + " is palindrome: " + util.isPalindrome(palindrome));
        
        String result = util.concatenate("Java", " is", " awesome");
        System.out.println(result);
    }
}
```

## Exercises

### Exercise 1: Temperature Converter
Create methods to:
- Convert Celsius to Fahrenheit
- Convert Fahrenheit to Celsius
- Overload to handle both int and double

### Exercise 2: Array Operations
Create methods to:
- Find maximum value in array
- Find minimum value in array
- Calculate average
- Overload for int[] and double[]

### Exercise 3: String Validator
Create methods to:
- Check if string is empty or null
- Check if string contains only digits
- Check if string contains only letters
- Count vowels and consonants

### Exercise 4: Mathematical Operations
Create methods to:
- Calculate power (x^n) using recursion
- Calculate GCD using recursion
- Generate Fibonacci series

### Exercise 5: Banking System
Create methods for:
- Deposit money
- Withdraw money (overloaded for different account types)
- Display balance
- Transfer between accounts

## Summary

Today you learned:
- ✅ What methods are and why they're important
- ✅ Method declaration and components
- ✅ Method parameters and return types
- ✅ Method overloading
- ✅ Variable arguments (varargs)
- ✅ Recursion
- ✅ Best practices for writing methods

Tomorrow, we'll explore **Classes and Objects**!

---

**Next**: [Day 07 - Classes and Objects](../Day07/README.md)

**Previous**: [Day 05 - Arrays](../Day05-Arrays/README.md)
