# Day 11: Exception Handling

Welcome to Day 11! Today we'll learn about exception handling - a crucial mechanism for writing robust Java applications.

## Table of Contents
- [What are Exceptions?](#what-are-exceptions)
- [Exception Hierarchy](#exception-hierarchy)
- [Try-Catch Block](#try-catch-block)
- [Multiple Catch Blocks](#multiple-catch-blocks)
- [Finally Block](#finally-block)
- [Throw and Throws](#throw-and-throws)
- [Custom Exceptions](#custom-exceptions)
- [Try-with-Resources](#try-with-resources)
- [Exercises](#exercises)

## What are Exceptions?

An exception is an event that disrupts the normal flow of program execution. Exception handling allows you to handle runtime errors gracefully.

### Why Exception Handling?
- Prevents program crashes
- Provides meaningful error messages
- Separates error-handling code from regular code
- Maintains normal program flow

### Types of Errors:
1. **Compile-time errors**: Syntax errors caught by compiler
2. **Runtime errors**: Errors that occur during execution
3. **Logical errors**: Program runs but produces wrong results

## Exception Hierarchy

```
Throwable
├── Error (System errors, cannot be handled)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── ...
└── Exception (Can be handled)
    ├── IOException
    ├── SQLException
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ArithmeticException
    │   └── ...
    └── ... (Checked exceptions)
```

### Checked vs Unchecked Exceptions:

**Checked Exceptions** (Must be handled):
- IOException
- SQLException
- ClassNotFoundException
- FileNotFoundException

**Unchecked Exceptions** (RuntimeException):
- NullPointerException
- ArrayIndexOutOfBoundsException
- ArithmeticException
- IllegalArgumentException

## Try-Catch Block

Basic syntax for handling exceptions:

```java
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
}
```

### Example 1: ArithmeticException
```java
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;  // Throws ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println("Program continues...");
    }
}
```

### Example 2: ArrayIndexOutOfBoundsException
```java
public class ArrayDemo {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        
        try {
            System.out.println(numbers[10]);  // Index out of bounds
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid array index");
            System.out.println("Message: " + e.getMessage());
        }
    }
}
```

### Example 3: NullPointerException
```java
public class NullDemo {
    public static void main(String[] args) {
        String text = null;
        
        try {
            System.out.println(text.length());  // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Error: String is null");
        }
    }
}
```

## Multiple Catch Blocks

Handle different exceptions differently:

```java
public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);  // ArrayIndexOutOfBoundsException
            
            int result = 10 / 0;  // ArithmeticException
            
            String text = null;
            System.out.println(text.length());  // NullPointerException
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null pointer error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }
    }
}
```

### Multi-catch (Java 7+):
```java
try {
    // Code that might throw exceptions
} catch (IOException | SQLException e) {
    System.out.println("IO or SQL error: " + e.getMessage());
}
```

## Finally Block

The finally block always executes, whether an exception occurs or not.

```java
public class FinallyDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error occurred");
        } finally {
            System.out.println("Finally block always executes");
        }
    }
}
```

### Use Case: Resource Cleanup
```java
import java.io.*;

public class FileDemo {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();  // Always close resources
                }
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
```

## Throw and Throws

### throw
Used to explicitly throw an exception:

```java
public class AgeValidator {
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or above");
        }
        System.out.println("Age is valid");
    }
    
    public static void main(String[] args) {
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### throws
Declares that a method might throw exceptions:

```java
import java.io.*;

public class FileReader {
    // Method declares it throws IOException
    public static void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        System.out.println(line);
        reader.close();
    }
    
    public static void main(String[] args) {
        try {
            readFile("data.txt");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
```

### Rethrowing Exceptions:
```java
public class RethrowDemo {
    public static void processData() throws Exception {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Logging error: " + e.getMessage());
            throw e;  // Rethrow the exception
        }
    }
    
    public static void main(String[] args) {
        try {
            processData();
        } catch (Exception e) {
            System.out.println("Caught in main: " + e.getMessage());
        }
    }
}
```

## Custom Exceptions

Create your own exception classes for specific scenarios:

### Example 1: Checked Custom Exception
```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds: $" + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private double balance;
    
    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
    }
    
    public double getBalance() {
        return balance;
    }
}

// Usage
public class BankingApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        
        try {
            account.withdraw(1500);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Short by: $" + e.getAmount());
        }
    }
}
```

### Example 2: Unchecked Custom Exception
```java
// Custom unchecked exception
class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class User {
    private String email;
    
    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
        this.email = email;
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public String getEmail() {
        return email;
    }
}

// Usage
public class UserApp {
    public static void main(String[] args) {
        User user = new User();
        
        try {
            user.setEmail("invalid-email");
        } catch (InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Try-with-Resources

Automatically closes resources (Java 7+):

### Syntax:
```java
try (ResourceType resource = new ResourceType()) {
    // Use the resource
} catch (Exception e) {
    // Handle exception
}
// Resource is automatically closed
```

### Example 1: File Reading
```java
import java.io.*;

public class TryWithResourcesDemo {
    public static void main(String[] args) {
        // Resource automatically closed
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
```

### Example 2: Multiple Resources
```java
import java.io.*;

public class CopyFile {
    public static void main(String[] args) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Complete Example

```java
// Custom exceptions
class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}

class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

// Product class
class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    
    public Product(String id, String name, double price, int stock) 
            throws InvalidProductException {
        if (price < 0) {
            throw new InvalidProductException("Price cannot be negative");
        }
        if (stock < 0) {
            throw new InvalidProductException("Stock cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    public void purchase(int quantity) throws OutOfStockException {
        if (quantity > stock) {
            throw new OutOfStockException(
                "Only " + stock + " units available, requested: " + quantity
            );
        }
        stock -= quantity;
        System.out.println("Purchased " + quantity + " units of " + name);
    }
    
    public void restock(int quantity) throws InvalidProductException {
        if (quantity <= 0) {
            throw new InvalidProductException("Restock quantity must be positive");
        }
        stock += quantity;
        System.out.println("Restocked " + quantity + " units");
    }
    
    public void displayInfo() {
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock);
    }
}

// Main application
public class InventorySystem {
    public static void main(String[] args) {
        Product product = null;
        
        try {
            // Create product
            product = new Product("P001", "Laptop", 999.99, 10);
            product.displayInfo();
            
            // Purchase items
            product.purchase(3);
            System.out.println("Purchase successful!");
            
            // Try to purchase more than available
            product.purchase(20);
            
        } catch (InvalidProductException e) {
            System.out.println("Product Error: " + e.getMessage());
        } catch (OutOfStockException e) {
            System.out.println("Stock Error: " + e.getMessage());
            
            // Try to restock
            try {
                if (product != null) {
                    product.restock(15);
                    product.displayInfo();
                }
            } catch (InvalidProductException ex) {
                System.out.println("Restock Error: " + ex.getMessage());
            }
        } finally {
            System.out.println("Transaction completed");
        }
    }
}
```

## Best Practices

1. **Catch specific exceptions first**
2. **Use finally for cleanup**
3. **Don't catch Exception unless necessary**
4. **Provide meaningful error messages**
5. **Log exceptions appropriately**
6. **Don't ignore exceptions**
7. **Use try-with-resources for AutoCloseable resources**

## Exercises

### Exercise 1: Calculator
Create a calculator with exception handling for:
- Division by zero
- Invalid input
- Number format exceptions

### Exercise 2: File Processor
Create a program that:
- Reads from a file
- Handles FileNotFoundException
- Handles IOException
- Uses try-with-resources

### Exercise 3: User Registration
Create a registration system with custom exceptions for:
- InvalidUsernameException
- WeakPasswordException
- DuplicateUserException

### Exercise 4: Banking System
Implement:
- InsufficientBalanceException
- InvalidAmountException
- AccountNotFoundException

### Exercise 5: Array Operations
Handle exceptions for:
- Array index out of bounds
- Null arrays
- Empty arrays

## Summary

Today you learned:
- ✅ What exceptions are and exception hierarchy
- ✅ Try-catch-finally blocks
- ✅ Multiple catch blocks
- ✅ throw and throws keywords
- ✅ Custom exceptions
- ✅ Try-with-resources
- ✅ Best practices for exception handling

Tomorrow, we'll explore **Collections Framework - List and Set**!

---

**Next**: [Day 12 - Collections Framework](../Day12/README.md)

**Previous**: [Day 10 - Encapsulation](../Day10/README.md)
