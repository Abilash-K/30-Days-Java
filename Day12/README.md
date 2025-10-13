# Day 12: Collections Framework - List and Set

Welcome to Day 12! Today we'll explore the Collections Framework, focusing on List and Set interfaces.

## Table of Contents
- [What is Collections Framework?](#what-is-collections-framework)
- [Collection Hierarchy](#collection-hierarchy)
- [List Interface](#list-interface)
- [ArrayList](#arraylist)
- [LinkedList](#linkedlist)
- [Vector](#vector)
- [Set Interface](#set-interface)
- [HashSet](#hashset)
- [LinkedHashSet](#linkedhashset)
- [TreeSet](#treeset)
- [Exercises](#exercises)

## What is Collections Framework?

The Collections Framework is a unified architecture for representing and manipulating collections of objects.

### Benefits:
- **Reduced programming effort**: Pre-built data structures
- **Increased performance**: Optimized implementations
- **Interoperability**: Common interface for collections
- **Easy to learn**: Consistent API

### Core Interfaces:
- **Collection**: Root interface
- **List**: Ordered collection (allows duplicates)
- **Set**: No duplicate elements
- **Queue**: FIFO operations
- **Map**: Key-value pairs

## Collection Hierarchy

```
Collection (interface)
├── List (interface)
│   ├── ArrayList (class)
│   ├── LinkedList (class)
│   └── Vector (class)
│       └── Stack (class)
├── Set (interface)
│   ├── HashSet (class)
│   ├── LinkedHashSet (class)
│   └── SortedSet (interface)
│       └── TreeSet (class)
└── Queue (interface)
    ├── PriorityQueue (class)
    └── Deque (interface)
        └── ArrayDeque (class)
```

## List Interface

List is an ordered collection that allows duplicate elements.

### Common Methods:
```java
add(E element)              // Add element
add(int index, E element)   // Add at index
get(int index)              // Get element
set(int index, E element)   // Replace element
remove(int index)           // Remove by index
remove(Object o)            // Remove by object
size()                      // Get size
clear()                     // Remove all
contains(Object o)          // Check if exists
indexOf(Object o)           // First occurrence
lastIndexOf(Object o)       // Last occurrence
```

## ArrayList

Dynamic array implementation of List interface.

### Characteristics:
- Fast random access (O(1))
- Slow insertion/deletion in middle (O(n))
- Maintains insertion order
- Allows null elements
- Not synchronized

### Basic Operations:
```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create ArrayList
        List<String> fruits = new ArrayList<>();
        
        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple");  // Duplicates allowed
        
        System.out.println("Fruits: " + fruits);
        
        // Add at specific index
        fruits.add(1, "Mango");
        System.out.println("After insertion: " + fruits);
        
        // Get element
        String first = fruits.get(0);
        System.out.println("First fruit: " + first);
        
        // Update element
        fruits.set(2, "Grapes");
        System.out.println("After update: " + fruits);
        
        // Remove element
        fruits.remove(0);               // Remove by index
        fruits.remove("Banana");        // Remove by object
        System.out.println("After removal: " + fruits);
        
        // Size
        System.out.println("Size: " + fruits.size());
        
        // Check if exists
        boolean hasApple = fruits.contains("Apple");
        System.out.println("Has Apple: " + hasApple);
        
        // Index of element
        int index = fruits.indexOf("Apple");
        System.out.println("Index of Apple: " + index);
    }
}
```

### Iterating ArrayList:
```java
List<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

// Using for loop
for (int i = 0; i < numbers.size(); i++) {
    System.out.println(numbers.get(i));
}

// Using enhanced for loop
for (Integer num : numbers) {
    System.out.println(num);
}

// Using Iterator
Iterator<Integer> iterator = numbers.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// Using forEach (Java 8)
numbers.forEach(num -> System.out.println(num));
```

### ArrayList with Custom Objects:
```java
class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

public class StudentList {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        
        students.add(new Student("Alice", 20));
        students.add(new Student("Bob", 22));
        students.add(new Student("Charlie", 21));
        
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
```

## LinkedList

Doubly-linked list implementation.

### Characteristics:
- Slow random access (O(n))
- Fast insertion/deletion (O(1))
- Implements both List and Deque
- Allows null elements

### Basic Operations:
```java
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        
        // Add elements
        list.add("A");
        list.add("B");
        list.add("C");
        
        // Add at first/last
        list.addFirst("Start");
        list.addLast("End");
        
        System.out.println("List: " + list);
        
        // Get first/last
        String first = list.getFirst();
        String last = list.getLast();
        System.out.println("First: " + first + ", Last: " + last);
        
        // Remove first/last
        list.removeFirst();
        list.removeLast();
        System.out.println("After removal: " + list);
    }
}
```

### ArrayList vs LinkedList:
```java
// ArrayList - better for random access
List<Integer> arrayList = new ArrayList<>();
arrayList.add(10);
int value = arrayList.get(0);  // O(1)

// LinkedList - better for insertions
LinkedList<Integer> linkedList = new LinkedList<>();
linkedList.addFirst(10);  // O(1)
linkedList.addLast(20);   // O(1)
```

## Vector

Synchronized version of ArrayList (legacy class).

```java
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        
        vector.add("One");
        vector.add("Two");
        vector.add("Three");
        
        // Thread-safe operations
        System.out.println("Vector: " + vector);
        System.out.println("Capacity: " + vector.capacity());
    }
}
```

## Set Interface

Collection that contains no duplicate elements.

### Common Methods:
```java
add(E element)         // Add if not present
remove(Object o)       // Remove element
contains(Object o)     // Check existence
size()                 // Get size
clear()                // Remove all
isEmpty()              // Check if empty
```

## HashSet

Unordered set using hash table.

### Characteristics:
- No duplicates
- No guaranteed order
- Fast operations (O(1) average)
- Allows one null element
- Not synchronized

### Basic Operations:
```java
import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        
        // Add elements
        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Apple");  // Duplicate - won't be added
        
        System.out.println("Set: " + set);  // Order not guaranteed
        System.out.println("Size: " + set.size());  // 3
        
        // Check if exists
        boolean hasApple = set.contains("Apple");
        System.out.println("Has Apple: " + hasApple);
        
        // Remove element
        set.remove("Banana");
        System.out.println("After removal: " + set);
        
        // Iterate
        for (String fruit : set) {
            System.out.println(fruit);
        }
    }
}
```

### HashSet with Custom Objects:
```java
import java.util.HashSet;
import java.util.Objects;

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class PersonSet {
    public static void main(String[] args) {
        Set<Person> people = new HashSet<>();
        
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Alice", 25));  // Duplicate
        
        System.out.println("People: " + people);  // Only 2 people
    }
}
```

## LinkedHashSet

Maintains insertion order using linked list.

### Characteristics:
- Maintains insertion order
- Slightly slower than HashSet
- No duplicates

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        
        set.add("C");
        set.add("A");
        set.add("B");
        set.add("A");  // Duplicate
        
        System.out.println("LinkedHashSet: " + set);  // [C, A, B]
        // Maintains insertion order
    }
}
```

## TreeSet

Sorted set using red-black tree.

### Characteristics:
- Elements in sorted order
- Slower operations (O(log n))
- No duplicates
- Does not allow null

```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetDemo {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        
        set.add(30);
        set.add(10);
        set.add(20);
        set.add(10);  // Duplicate
        
        System.out.println("TreeSet: " + set);  // [10, 20, 30] - sorted
        
        // Additional TreeSet methods
        TreeSet<Integer> treeSet = (TreeSet<Integer>) set;
        System.out.println("First: " + treeSet.first());  // 10
        System.out.println("Last: " + treeSet.last());    // 30
        System.out.println("HeadSet < 20: " + treeSet.headSet(20));  // [10]
        System.out.println("TailSet >= 20: " + treeSet.tailSet(20)); // [20, 30]
    }
}
```

### TreeSet with Custom Objects:
```java
import java.util.TreeSet;
import java.util.Comparator;

class Employee implements Comparable<Employee> {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
    
    @Override
    public String toString() {
        return name + ": $" + salary;
    }
}

public class EmployeeSet {
    public static void main(String[] args) {
        TreeSet<Employee> employees = new TreeSet<>();
        
        employees.add(new Employee("Alice", 70000));
        employees.add(new Employee("Bob", 50000));
        employees.add(new Employee("Charlie", 60000));
        
        System.out.println("Sorted by salary:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
```

## Set Comparison

```java
import java.util.*;

public class SetComparison {
    public static void main(String[] args) {
        // HashSet - no order
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        System.out.println("HashSet: " + hashSet);  // Random order
        
        // LinkedHashSet - insertion order
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("C");
        linkedSet.add("A");
        linkedSet.add("B");
        System.out.println("LinkedHashSet: " + linkedSet);  // [C, A, B]
        
        // TreeSet - sorted order
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("C");
        treeSet.add("A");
        treeSet.add("B");
        System.out.println("TreeSet: " + treeSet);  // [A, B, C]
    }
}
```

## Exercises

### Exercise 1: Shopping List
Create a shopping list using ArrayList:
- Add items
- Remove items
- Check if item exists
- Display all items

### Exercise 2: Student Management
Use ArrayList to manage students:
- Add students
- Remove by index
- Search by name
- Sort by age

### Exercise 3: Remove Duplicates
Write a method to remove duplicates from ArrayList using HashSet

### Exercise 4: Unique Words
Read a text and find unique words using Set

### Exercise 5: Sorted Names
Store names in TreeSet and display sorted

## Summary

Today you learned:
- ✅ Collections Framework overview
- ✅ List interface and implementations (ArrayList, LinkedList, Vector)
- ✅ Set interface and implementations (HashSet, LinkedHashSet, TreeSet)
- ✅ When to use each collection type
- ✅ Iterating collections
- ✅ Custom objects in collections

Tomorrow, we'll explore **Collections Framework - Map and Queue**!

---

**Next**: [Day 13 - Collections Framework - Map and Queue](../Day13/README.md)

**Previous**: [Day 11 - Exception Handling](../Day11/README.md)
