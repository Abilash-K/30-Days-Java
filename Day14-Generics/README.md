# Day 14: Generics

Welcome to Day 14! Today we'll learn about Generics - a powerful feature for type-safe code.

## Table of Contents
- [What are Generics?](#what-are-generics)
- [Generic Classes](#generic-classes)
- [Generic Methods](#generic-methods)
- [Bounded Type Parameters](#bounded-type-parameters)
- [Wildcards](#wildcards)
- [Generic Interfaces](#generic-interfaces)
- [Type Erasure](#type-erasure)
- [Exercises](#exercises)

## What are Generics?

Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods.

### Benefits:
- **Type Safety**: Compile-time type checking
- **Code Reusability**: Write once, use with any type
- **Elimination of Casts**: No need for explicit casting
- **Generic Algorithms**: Work with different types

### Before Generics (Java < 5):
```java
List list = new ArrayList();
list.add("Hello");
list.add(10);  // Can add any object

String str = (String) list.get(0);  // Need casting
Integer num = (Integer) list.get(1);
```

### With Generics (Java 5+):
```java
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(10);  // Compilation error - type safe

String str = list.get(0);  // No casting needed
```

## Generic Classes

A class that can work with different types.

### Syntax:
```java
class ClassName<T> {
    private T data;
    
    public void setData(T data) {
        this.data = data;
    }
    
    public T getData() {
        return data;
    }
}
```

### Example 1: Box Class
```java
class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        // Box for Integer
        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer: " + intBox.get());
        
        // Box for String
        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        System.out.println("String: " + strBox.get());
        
        // Box for custom object
        Box<Person> personBox = new Box<>();
        personBox.set(new Person("Alice", 25));
        System.out.println("Person: " + personBox.get());
    }
}

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return name + ", " + age;
    }
}
```

### Example 2: Pair Class
```java
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Pair{" + key + " = " + value + "}";
    }
}

public class PairDemo {
    public static void main(String[] args) {
        Pair<String, Integer> pair1 = new Pair<>("Age", 25);
        System.out.println(pair1);
        
        Pair<Integer, String> pair2 = new Pair<>(1, "First");
        System.out.println(pair2);
        
        Pair<String, Person> pair3 = new Pair<>("Employee", new Person("Bob", 30));
        System.out.println(pair3);
    }
}
```

### Example 3: Generic Stack
```java
class Stack<T> {
    private List<T> elements = new ArrayList<>();
    
    public void push(T element) {
        elements.add(element);
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }
    
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public int size() {
        return elements.size();
    }
}

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
    }
}
```

## Generic Methods

Methods that declare their own type parameters.

### Syntax:
```java
public <T> returnType methodName(T parameter) {
    // Method body
}
```

### Example 1: Print Array
```java
public class GenericMethods {
    // Generic method to print array
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"A", "B", "C"};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        
        printArray(intArray);
        printArray(strArray);
        printArray(doubleArray);
    }
}
```

### Example 2: Generic Swap
```java
public class SwapDemo {
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Before: " + Arrays.toString(numbers));
        swap(numbers, 0, 4);
        System.out.println("After: " + Arrays.toString(numbers));
        
        String[] words = {"A", "B", "C"};
        System.out.println("Before: " + Arrays.toString(words));
        swap(words, 0, 2);
        System.out.println("After: " + Arrays.toString(words));
    }
}
```

### Example 3: Generic Compare
```java
public class CompareDemo {
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    public static void main(String[] args) {
        System.out.println("Max: " + max(10, 20));
        System.out.println("Max: " + max("Apple", "Banana"));
        System.out.println("Max: " + max(3.14, 2.71));
    }
}
```

## Bounded Type Parameters

Restrict types that can be used with generics.

### Upper Bound (extends):
```java
// T must be Number or its subclass
class NumberBox<T extends Number> {
    private T number;
    
    public void setNumber(T number) {
        this.number = number;
    }
    
    public T getNumber() {
        return number;
    }
    
    public double getDoubleValue() {
        return number.doubleValue();
    }
}

public class BoundedDemo {
    public static void main(String[] args) {
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.setNumber(100);
        System.out.println("Integer: " + intBox.getDoubleValue());
        
        NumberBox<Double> doubleBox = new NumberBox<>();
        doubleBox.setNumber(3.14);
        System.out.println("Double: " + doubleBox.getDoubleValue());
        
        // NumberBox<String> strBox = new NumberBox<>();  // Error
    }
}
```

### Multiple Bounds:
```java
interface Printable {
    void print();
}

class Document implements Comparable<Document>, Printable {
    private String name;
    
    public Document(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(Document other) {
        return this.name.compareTo(other.name);
    }
    
    @Override
    public void print() {
        System.out.println("Printing: " + name);
    }
}

// T must implement both Comparable and Printable
class Processor<T extends Comparable<T> & Printable> {
    public void process(T item) {
        item.print();
    }
    
    public T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}
```

## Wildcards

Use wildcards when you don't know the exact type.

### Unbounded Wildcard (?):
```java
public class WildcardDemo {
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("A", "B", "C");
        
        printList(intList);
        printList(strList);
    }
}
```

### Upper Bounded Wildcard (? extends):
```java
public class UpperBoundDemo {
    // Accept List of Number or its subclasses
    public static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Integer sum: " + sum(intList));
        System.out.println("Double sum: " + sum(doubleList));
    }
}
```

### Lower Bounded Wildcard (? super):
```java
public class LowerBoundDemo {
    // Accept List of Integer or its superclasses
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }
    
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        addNumbers(intList);
        System.out.println("Integer list: " + intList);
        
        List<Number> numList = new ArrayList<>();
        addNumbers(numList);
        System.out.println("Number list: " + numList);
        
        List<Object> objList = new ArrayList<>();
        addNumbers(objList);
        System.out.println("Object list: " + objList);
    }
}
```

## Generic Interfaces

Interfaces can also be generic.

```java
interface Container<T> {
    void add(T item);
    T get(int index);
    int size();
}

class SimpleContainer<T> implements Container<T> {
    private List<T> items = new ArrayList<>();
    
    @Override
    public void add(T item) {
        items.add(item);
    }
    
    @Override
    public T get(int index) {
        return items.get(index);
    }
    
    @Override
    public int size() {
        return items.size();
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Container<String> container = new SimpleContainer<>();
        container.add("A");
        container.add("B");
        container.add("C");
        
        System.out.println("First: " + container.get(0));
        System.out.println("Size: " + container.size());
    }
}
```

## Type Erasure

Java implements generics using type erasure - generic type information is removed at runtime.

```java
// At compile time
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// At runtime (after type erasure)
// Both become: List list = new ArrayList();

// Cannot do:
// if (stringList instanceof List<String>) { }  // Error
// T[] array = new T[10];  // Error
```

## Complete Example

```java
// Generic Repository pattern
interface Repository<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}

class User {
    private int id;
    private String name;
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}

class UserRepository implements Repository<User, Integer> {
    private Map<Integer, User> storage = new HashMap<>();
    
    @Override
    public void save(User entity) {
        storage.put(entity.getId(), entity);
    }
    
    @Override
    public User findById(Integer id) {
        return storage.get(id);
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public void delete(Integer id) {
        storage.remove(id);
    }
}

public class RepositoryDemo {
    public static void main(String[] args) {
        Repository<User, Integer> userRepo = new UserRepository();
        
        // Save users
        userRepo.save(new User(1, "Alice"));
        userRepo.save(new User(2, "Bob"));
        userRepo.save(new User(3, "Charlie"));
        
        // Find user
        User user = userRepo.findById(2);
        System.out.println("Found: " + user);
        
        // Find all
        System.out.println("All users: " + userRepo.findAll());
        
        // Delete user
        userRepo.delete(2);
        System.out.println("After delete: " + userRepo.findAll());
    }
}
```

## Exercises

### Exercise 1: Generic Pair
Create a generic Pair class that can swap its elements

### Exercise 2: Generic ArrayList
Implement your own generic ArrayList with add, get, remove, size

### Exercise 3: Generic Comparator
Create a generic method to find max/min in an array

### Exercise 4: Generic Cache
Implement a generic cache with get/put/remove operations

### Exercise 5: Generic Tree Node
Create a generic tree node class with add child functionality

## Summary

Today you learned:
- ✅ What generics are and their benefits
- ✅ Generic classes and methods
- ✅ Bounded type parameters
- ✅ Wildcards (?, ? extends, ? super)
- ✅ Generic interfaces
- ✅ Type erasure concept

Tomorrow, we'll explore **Lambda Expressions**!

---

**Next**: [Day 15 - Lambda Expressions](../Day15-Lambda/README.md)

**Previous**: [Day 13 - Collections Framework - Map and Queue](../Day13-Collections-MapQueue/README.md)
