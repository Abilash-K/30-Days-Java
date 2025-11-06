# Day 19: Optional Class

Welcome to Day 19! Today we'll learn about the Optional class introduced in Java 8 to handle null values elegantly.

## Table of Contents
- [Introduction to Optional](#introduction-to-optional)
- [Creating Optional Objects](#creating-optional-objects)
- [Checking Values](#checking-values)
- [Retrieving Values](#retrieving-values)
- [Functional Methods](#functional-methods)
- [Best Practices](#best-practices)
- [Exercises](#exercises)

## Introduction to Optional

Optional is a container object that may or may not contain a non-null value. It helps avoid NullPointerException.

### Why Optional?

**Before Optional:**
```java
public String getUserEmail(int userId) {
    User user = findUser(userId);
    if (user != null) {
        String email = user.getEmail();
        if (email != null) {
            return email.toLowerCase();
        }
    }
    return "default@email.com";
}
```

**With Optional:**
```java
public String getUserEmail(int userId) {
    return findUser(userId)
        .map(User::getEmail)
        .map(String::toLowerCase)
        .orElse("default@email.com");
}
```

## Creating Optional Objects

### 1. Optional.of()
Creates Optional with non-null value.

```java
Optional<String> optional = Optional.of("Hello");
// Optional.of(null);  // Throws NullPointerException
```

### 2. Optional.ofNullable()
Creates Optional that may contain null.

```java
String value = null;
Optional<String> optional = Optional.ofNullable(value);  // Safe with null
```

### 3. Optional.empty()
Creates empty Optional.

```java
Optional<String> empty = Optional.empty();
```

## Checking Values

### isPresent()
```java
Optional<String> optional = Optional.of("Hello");

if (optional.isPresent()) {
    System.out.println("Value is present");
}
```

### isEmpty() (Java 11+)
```java
Optional<String> empty = Optional.empty();

if (empty.isEmpty()) {
    System.out.println("No value present");
}
```

### ifPresent()
```java
Optional<String> optional = Optional.of("Hello");

optional.ifPresent(value -> System.out.println(value));
// or
optional.ifPresent(System.out::println);
```

### ifPresentOrElse() (Java 9+)
```java
optional.ifPresentOrElse(
    value -> System.out.println("Found: " + value),
    () -> System.out.println("Not found")
);
```

## Retrieving Values

### get()
Returns value if present, throws exception otherwise.

```java
Optional<String> optional = Optional.of("Hello");
String value = optional.get();  // "Hello"

Optional<String> empty = Optional.empty();
// String value = empty.get();  // Throws NoSuchElementException
```

### orElse()
Returns value or default.

```java
Optional<String> optional = Optional.ofNullable(null);
String value = optional.orElse("Default");  // "Default"
```

### orElseGet()
Returns value or result from Supplier.

```java
String value = optional.orElseGet(() -> "Default from supplier");

// Difference: orElseGet is lazy
String value1 = optional.orElse(getDefaultValue());        // Always called
String value2 = optional.orElseGet(() -> getDefaultValue()); // Called only if empty
```

### orElseThrow()
Returns value or throws exception.

```java
// With default exception
String value = optional.orElseThrow();  // NoSuchElementException

// With custom exception
String value = optional.orElseThrow(
    () -> new IllegalStateException("Value not found")
);
```

## Functional Methods

### map()
Transforms the value if present.

```java
Optional<String> name = Optional.of("alice");
Optional<String> upperName = name.map(String::toUpperCase);
System.out.println(upperName.get());  // "ALICE"

// Chaining
Optional<Integer> length = name
    .map(String::toUpperCase)
    .map(String::length);
```

### flatMap()
Like map, but for methods that return Optional.

```java
public class User {
    private String name;
    private Address address;
    
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

public class Address {
    private String city;
    
    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }
}

// Using flatMap
Optional<User> user = Optional.of(new User());
Optional<String> city = user
    .flatMap(User::getAddress)
    .flatMap(Address::getCity);
```

### filter()
Filters value based on predicate.

```java
Optional<Integer> number = Optional.of(25);

Optional<Integer> filtered = number.filter(n -> n > 20);
System.out.println(filtered.isPresent());  // true

Optional<Integer> filtered2 = number.filter(n -> n > 30);
System.out.println(filtered2.isPresent());  // false
```

## Best Practices

### DO: Return Optional from methods
```java
public Optional<User> findUserById(int id) {
    User user = database.find(id);
    return Optional.ofNullable(user);
}
```

### DON'T: Use Optional for fields
```java
// Bad
public class Person {
    private Optional<String> name;  // Don't do this
}

// Good
public class Person {
    private String name;  // Can be null
}
```

### DO: Use Optional with Stream
```java
List<Optional<String>> list = Arrays.asList(
    Optional.of("A"),
    Optional.empty(),
    Optional.of("B")
);

List<String> result = list.stream()
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());
```

### DON'T: Call get() without checking
```java
// Bad
Optional<String> opt = Optional.empty();
String value = opt.get();  // Exception!

// Good
String value = opt.orElse("default");
```

## Practical Examples

### Example 1: Safe Property Access
```java
public String getCountryCode(Person person) {
    return Optional.ofNullable(person)
        .flatMap(Person::getAddress)
        .flatMap(Address::getCountry)
        .map(Country::getCode)
        .orElse("UNKNOWN");
}
```

### Example 2: Configuration Values
```java
public int getConfigValue(String key) {
    return Optional.ofNullable(properties.get(key))
        .map(Integer::parseInt)
        .filter(value -> value > 0)
        .orElse(100);  // default value
}
```

### Example 3: Database Query
```java
public Optional<User> findUserByEmail(String email) {
    List<User> users = database.query(email);
    return users.isEmpty() 
        ? Optional.empty() 
        : Optional.of(users.get(0));
}

// Usage
findUserByEmail("user@example.com")
    .ifPresentOrElse(
        user -> System.out.println("Found: " + user.getName()),
        () -> System.out.println("User not found")
    );
```

## Exercises

### Exercise 1: Basic Optional
Create methods that return Optional for:
- Finding a number in an array
- Getting user by ID
- Parsing a string to integer

### Exercise 2: Optional Chain
Create a chain of Optional operations to:
- Get user address
- Extract city name
- Convert to uppercase
- Return default if not found

### Exercise 3: Collection Processing
Filter a list using Optional:
- Remove null values
- Transform remaining values
- Collect to list

### Exercise 4: Custom Repository
Create a repository class that:
- Returns Optional from find methods
- Uses orElseThrow for get methods
- Implements search with Optional

## Summary

Today you learned:
- ✅ What Optional is and why it's useful
- ✅ Creating Optional objects
- ✅ Checking and retrieving values
- ✅ Functional methods (map, flatMap, filter)
- ✅ Best practices for using Optional
- ✅ Real-world examples

Tomorrow, we'll explore **Date and Time API**!

---

**Next**: [Day 20 - Date and Time API](../Day20-DateTime-API/README.md)

**Previous**: [Day 18 - Stream API Part 2](../Day18-Streams-Part2/README.md)

