# Day 13: Collections Framework - Map and Queue

Welcome to Day 13! Today we'll explore Map and Queue interfaces in the Collections Framework.

## Table of Contents
- [Map Interface](#map-interface)
- [HashMap](#hashmap)
- [LinkedHashMap](#linkedhashmap)
- [TreeMap](#treemap)
- [Hashtable](#hashtable)
- [Queue Interface](#queue-interface)
- [PriorityQueue](#priorityqueue)
- [Deque Interface](#deque-interface)
- [ArrayDeque](#arraydeque)
- [Exercises](#exercises)

## Map Interface

Map stores key-value pairs. Each key maps to exactly one value.

### Characteristics:
- No duplicate keys (values can be duplicate)
- Each key maps to one value
- Not part of Collection interface

### Common Methods:
```java
put(K key, V value)        // Add/update entry
get(Object key)            // Get value
remove(Object key)         // Remove entry
containsKey(Object key)    // Check if key exists
containsValue(Object val)  // Check if value exists
keySet()                   // Get all keys
values()                   // Get all values
entrySet()                 // Get all entries
size()                     // Get size
clear()                    // Remove all
isEmpty()                  // Check if empty
```

## HashMap

Hash table implementation of Map.

### Characteristics:
- No order guarantee
- Allows one null key and multiple null values
- Fast operations (O(1) average)
- Not synchronized

### Basic Operations:
```java
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        // Add entries
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 15);
        map.put("Apple", 12);  // Updates existing key
        
        System.out.println("Map: " + map);
        
        // Get value
        int appleCount = map.get("Apple");
        System.out.println("Apple count: " + appleCount);
        
        // Check if key exists
        boolean hasBanana = map.containsKey("Banana");
        System.out.println("Has Banana: " + hasBanana);
        
        // Check if value exists
        boolean hasValue15 = map.containsValue(15);
        System.out.println("Has value 15: " + hasValue15);
        
        // Remove entry
        map.remove("Orange");
        System.out.println("After removal: " + map);
        
        // Size
        System.out.println("Size: " + map.size());
    }
}
```

### Iterating HashMap:
```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 87);
scores.put("Charlie", 92);

// Using keySet
for (String name : scores.keySet()) {
    System.out.println(name + ": " + scores.get(name));
}

// Using values
for (Integer score : scores.values()) {
    System.out.println("Score: " + score);
}

// Using entrySet (recommended)
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Using forEach (Java 8)
scores.forEach((name, score) -> 
    System.out.println(name + ": " + score)
);
```

### HashMap with Custom Objects:
```java
import java.util.HashMap;
import java.util.Map;

class Student {
    private int id;
    private String name;
    
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

public class StudentMap {
    public static void main(String[] args) {
        Map<Integer, Student> students = new HashMap<>();
        
        students.put(101, new Student(101, "Alice"));
        students.put(102, new Student(102, "Bob"));
        students.put(103, new Student(103, "Charlie"));
        
        // Get student by ID
        Student student = students.get(102);
        System.out.println("Student 102: " + student);
        
        // Iterate
        for (Map.Entry<Integer, Student> entry : students.entrySet()) {
            System.out.println("ID: " + entry.getKey() + 
                             ", Student: " + entry.getValue());
        }
    }
}
```

## LinkedHashMap

Maintains insertion order using linked list.

### Characteristics:
- Maintains insertion order
- Slightly slower than HashMap
- Allows one null key

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        
        map.put("Third", 3);
        map.put("First", 1);
        map.put("Second", 2);
        
        System.out.println("LinkedHashMap: " + map);
        // Maintains insertion order: {Third=3, First=1, Second=2}
        
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
```

## TreeMap

Sorted map using red-black tree.

### Characteristics:
- Keys in sorted order
- Slower operations (O(log n))
- Does not allow null keys
- Natural ordering or custom Comparator

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        
        map.put("Charlie", 30);
        map.put("Alice", 10);
        map.put("Bob", 20);
        
        System.out.println("TreeMap: " + map);
        // Sorted by keys: {Alice=10, Bob=20, Charlie=30}
        
        // TreeMap specific methods
        TreeMap<String, Integer> treeMap = (TreeMap<String, Integer>) map;
        System.out.println("First key: " + treeMap.firstKey());  // Alice
        System.out.println("Last key: " + treeMap.lastKey());    // Charlie
        System.out.println("HeadMap < 'Bob': " + treeMap.headMap("Bob"));
        System.out.println("TailMap >= 'Bob': " + treeMap.tailMap("Bob"));
    }
}
```

### TreeMap with Custom Comparator:
```java
import java.util.TreeMap;
import java.util.Comparator;

public class ReverseTreeMap {
    public static void main(String[] args) {
        // Reverse order
        TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        
        map.put("A", 1);
        map.put("C", 3);
        map.put("B", 2);
        
        System.out.println("Reverse order: " + map);  // {C=3, B=2, A=1}
    }
}
```

## Hashtable

Legacy synchronized version of HashMap.

```java
import java.util.Hashtable;

public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, Integer> table = new Hashtable<>();
        
        table.put("One", 1);
        table.put("Two", 2);
        table.put("Three", 3);
        // table.put(null, 4);  // NullPointerException
        
        System.out.println("Hashtable: " + table);
    }
}
```

## Map Comparison

```java
import java.util.*;

public class MapComparison {
    public static void main(String[] args) {
        // HashMap - no order
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("C", 3);
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        System.out.println("HashMap: " + hashMap);
        
        // LinkedHashMap - insertion order
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("C", 3);
        linkedMap.put("A", 1);
        linkedMap.put("B", 2);
        System.out.println("LinkedHashMap: " + linkedMap);
        
        // TreeMap - sorted order
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        System.out.println("TreeMap: " + treeMap);
    }
}
```

## Queue Interface

Queue is a collection for holding elements prior to processing (FIFO).

### Common Methods:
```java
add(E element)       // Add element (throws exception if fails)
offer(E element)     // Add element (returns false if fails)
remove()             // Remove head (throws exception if empty)
poll()               // Remove head (returns null if empty)
element()            // Get head (throws exception if empty)
peek()               // Get head (returns null if empty)
```

## PriorityQueue

Queue that orders elements based on priority.

### Characteristics:
- Elements ordered by natural ordering or Comparator
- Not FIFO (orders by priority)
- Does not allow null
- Not synchronized

```java
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        
        // Add elements
        queue.offer(30);
        queue.offer(10);
        queue.offer(20);
        queue.offer(5);
        
        System.out.println("Queue: " + queue);
        
        // Remove elements (in priority order)
        while (!queue.isEmpty()) {
            System.out.println("Removed: " + queue.poll());
        }
        // Output: 5, 10, 20, 30 (ascending order)
    }
}
```

### PriorityQueue with Custom Objects:
```java
import java.util.PriorityQueue;
import java.util.Comparator;

class Task {
    private String name;
    private int priority;  // Lower number = higher priority
    
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return name + " (priority: " + priority + ")";
    }
}

public class TaskQueue {
    public static void main(String[] args) {
        PriorityQueue<Task> tasks = new PriorityQueue<>(
            Comparator.comparingInt(Task::getPriority)
        );
        
        tasks.offer(new Task("Low priority task", 3));
        tasks.offer(new Task("High priority task", 1));
        tasks.offer(new Task("Medium priority task", 2));
        
        System.out.println("Processing tasks:");
        while (!tasks.isEmpty()) {
            System.out.println(tasks.poll());
        }
    }
}
```

## Deque Interface

Double-ended queue (can add/remove from both ends).

### Common Methods:
```java
addFirst(E e)        // Add at beginning
addLast(E e)         // Add at end
offerFirst(E e)      // Add at beginning (returns false if fails)
offerLast(E e)       // Add at end (returns false if fails)
removeFirst()        // Remove first
removeLast()         // Remove last
pollFirst()          // Remove first (returns null if empty)
pollLast()           // Remove last (returns null if empty)
getFirst()           // Get first
getLast()            // Get last
peekFirst()          // Get first (returns null if empty)
peekLast()           // Get last (returns null if empty)
```

## ArrayDeque

Resizable array implementation of Deque.

### Characteristics:
- Faster than LinkedList for queue/stack operations
- No capacity restrictions
- Not thread-safe
- Does not allow null

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        
        // Add elements
        deque.addFirst("First");
        deque.addLast("Last");
        deque.offerFirst("New First");
        deque.offerLast("New Last");
        
        System.out.println("Deque: " + deque);
        
        // Get elements
        System.out.println("First: " + deque.getFirst());
        System.out.println("Last: " + deque.getLast());
        
        // Remove elements
        System.out.println("Removed first: " + deque.removeFirst());
        System.out.println("Removed last: " + deque.removeLast());
        
        System.out.println("Final deque: " + deque);
    }
}
```

### Using ArrayDeque as Stack:
```java
import java.util.ArrayDeque;

public class StackExample {
    public static void main(String[] args) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        
        // Push elements
        stack.push("A");
        stack.push("B");
        stack.push("C");
        
        System.out.println("Stack: " + stack);
        
        // Pop elements
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
```

## Complete Example

```java
import java.util.*;

// Word frequency counter
public class WordFrequency {
    public static void main(String[] args) {
        String text = "java is great java is powerful java is everywhere";
        
        // Count word frequencies
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = text.split(" ");
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("Word frequencies: " + wordCount);
        
        // Sort by frequency
        List<Map.Entry<String, Integer>> entries = 
            new ArrayList<>(wordCount.entrySet());
        
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        System.out.println("\nTop words:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Use PriorityQueue for processing
        PriorityQueue<String> queue = new PriorityQueue<>(
            (w1, w2) -> wordCount.get(w2).compareTo(wordCount.get(w1))
        );
        
        queue.addAll(wordCount.keySet());
        
        System.out.println("\nProcessing in priority order:");
        while (!queue.isEmpty()) {
            String word = queue.poll();
            System.out.println(word + ": " + wordCount.get(word));
        }
    }
}
```

## Exercises

### Exercise 1: Phone Book
Create a phone book using HashMap:
- Add contacts
- Search by name
- Remove contacts
- Display all

### Exercise 2: Student Grades
Use TreeMap to store student grades sorted by name:
- Add students with grades
- Calculate average
- Find highest/lowest grade

### Exercise 3: Task Scheduler
Implement task scheduler using PriorityQueue:
- Tasks with priorities
- Process tasks by priority
- Display pending tasks

### Exercise 4: Browser History
Use ArrayDeque to implement browser history:
- Go forward
- Go backward
- Add new page

### Exercise 5: Character Frequency
Count character frequency in a string using HashMap

## Summary

Today you learned:
- ✅ Map interface and implementations (HashMap, LinkedHashMap, TreeMap)
- ✅ When to use each Map type
- ✅ Queue interface and PriorityQueue
- ✅ Deque interface and ArrayDeque
- ✅ Iterating maps
- ✅ Custom objects in maps and queues

Tomorrow, we'll explore **Generics**!

---

**Next**: [Day 14 - Generics](../Day14-Generics/README.md)

**Previous**: [Day 12 - Collections Framework - List and Set](../Day12-Collections-ListSet/README.md)
