# Day 05: Arrays and ArrayList

Welcome to Day 5! Today we'll learn about arrays and ArrayList - fundamental data structures for storing collections of elements.

## Table of Contents
- [Arrays](#arrays)
- [Multidimensional Arrays](#multidimensional-arrays)
- [ArrayList](#arraylist)
- [Comparing Arrays and ArrayList](#comparing-arrays-and-arraylist)
- [Exercises](#exercises)

## Arrays

An array is a container object that holds a fixed number of values of a single type.

### Declaration and Initialization

```java
// Declaration
int[] numbers;
String[] names;

// Initialization
numbers = new int[5];           // Array of 5 integers
names = new String[3];          // Array of 3 strings

// Declaration and initialization together
int[] scores = new int[10];
double[] prices = new double[5];

// Initialize with values
int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
```

### Accessing Elements

```java
int[] numbers = {10, 20, 30, 40, 50};

// Access elements (0-indexed)
System.out.println(numbers[0]);  // 10
System.out.println(numbers[4]);  // 50

// Modify elements
numbers[2] = 35;
System.out.println(numbers[2]);  // 35

// Array length
System.out.println(numbers.length);  // 5
```

### Iterating Through Arrays

```java
int[] numbers = {1, 2, 3, 4, 5};

// Using for loop
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// Using enhanced for loop (preferred)
for (int num : numbers) {
    System.out.println(num);
}
```

### Common Array Operations

```java
import java.util.Arrays;

int[] numbers = {5, 2, 8, 1, 9};

// Sorting
Arrays.sort(numbers);
System.out.println(Arrays.toString(numbers));  // [1, 2, 5, 8, 9]

// Searching (array must be sorted)
int index = Arrays.binarySearch(numbers, 5);
System.out.println(index);  // 2

// Copying
int[] copy = Arrays.copyOf(numbers, numbers.length);

// Filling
int[] filled = new int[5];
Arrays.fill(filled, 10);  // All elements = 10

// Comparing
boolean equal = Arrays.equals(numbers, copy);
```

## Multidimensional Arrays

Arrays of arrays, commonly used for matrices and tables.

### 2D Arrays

```java
// Declaration and initialization
int[][] matrix = new int[3][4];  // 3 rows, 4 columns

// Initialize with values
int[][] table = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Accessing elements
System.out.println(table[0][0]);  // 1
System.out.println(table[1][2]);  // 6

// Iterating
for (int i = 0; i < table.length; i++) {
    for (int j = 0; j < table[i].length; j++) {
        System.out.print(table[i][j] + " ");
    }
    System.out.println();
}

// Enhanced for loop
for (int[] row : table) {
    for (int value : row) {
        System.out.print(value + " ");
    }
    System.out.println();
}
```

### Jagged Arrays

Arrays with different row lengths.

```java
int[][] jagged = {
    {1, 2},
    {3, 4, 5, 6},
    {7}
};

for (int[] row : jagged) {
    System.out.println(Arrays.toString(row));
}
```

## ArrayList

ArrayList is a resizable array implementation from the Collections Framework.

### Import and Creation

```java
import java.util.ArrayList;

// Creating ArrayList
ArrayList<String> names = new ArrayList<>();
ArrayList<Integer> numbers = new ArrayList<>();

// With initial capacity
ArrayList<String> list = new ArrayList<>(20);

// From existing collection
ArrayList<String> copy = new ArrayList<>(names);
```

### Adding Elements

```java
ArrayList<String> fruits = new ArrayList<>();

// Add at end
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Orange");

// Add at specific index
fruits.add(1, "Mango");

// Add all from another collection
ArrayList<String> moreFruits = new ArrayList<>();
moreFruits.add("Grape");
moreFruits.add("Kiwi");
fruits.addAll(moreFruits);
```

### Accessing Elements

```java
String first = fruits.get(0);
String second = fruits.get(1);

// Size
int size = fruits.size();

// Check if empty
boolean empty = fruits.isEmpty();

// Contains
boolean hasApple = fruits.contains("Apple");
```

### Modifying Elements

```java
// Update element
fruits.set(0, "Pineapple");

// Remove by index
fruits.remove(0);

// Remove by object
fruits.remove("Banana");

// Remove all
fruits.clear();
```

### Iterating ArrayList

```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.add("Charlie");

// Using for loop
for (int i = 0; i < names.size(); i++) {
    System.out.println(names.get(i));
}

// Using enhanced for loop
for (String name : names) {
    System.out.println(name);
}

// Using forEach with lambda (Java 8+)
names.forEach(name -> System.out.println(name));

// Using method reference
names.forEach(System.out::println);
```

### ArrayList Methods

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(20);

// Index of element
int index = numbers.indexOf(20);      // First occurrence: 1
int lastIndex = numbers.lastIndexOf(20);  // Last occurrence: 3

// Convert to array
Integer[] array = numbers.toArray(new Integer[0]);

// Sublist
List<Integer> subList = numbers.subList(1, 3);  // [20, 30]

// Sort
Collections.sort(numbers);

// Reverse
Collections.reverse(numbers);
```

## Comparing Arrays and ArrayList

| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed | Dynamic |
| Type | Primitive or Object | Object only |
| Performance | Faster | Slightly slower |
| Methods | Limited | Rich API |
| Syntax | `int[]` | `ArrayList<Integer>` |
| Multidimensional | `int[][]` | `ArrayList<ArrayList<Integer>>` |

### When to Use What

**Use Arrays when:**
- Size is known and fixed
- Need primitive types
- Performance is critical
- Working with multidimensional data

**Use ArrayList when:**
- Size varies
- Need flexibility
- Want convenient methods
- Don't mind slight overhead

## Exercises

### Exercise 1: Array Basics
Create an array of 10 integers, fill it with even numbers, and print them.

### Exercise 2: Find Maximum
Write a program to find the maximum element in an array.

### Exercise 3: Array Reverse
Reverse an array without using extra space.

### Exercise 4: ArrayList Operations
Create an ArrayList of strings, add 5 names, remove one, and display all.

### Exercise 5: Search Element
Implement linear search to find an element in an array.

### Exercise 6: 2D Array Sum
Calculate the sum of all elements in a 2D array.

### Exercise 7: ArrayList Sorting
Create an ArrayList of integers, sort it in descending order.

### Exercise 8: Remove Duplicates
Remove duplicate elements from an ArrayList.

## Summary

Today you learned:
- ✅ Arrays - declaration, initialization, and operations
- ✅ Multidimensional arrays
- ✅ ArrayList - dynamic resizing
- ✅ Common operations on arrays and ArrayList
- ✅ When to use arrays vs ArrayList
- ✅ Converting between arrays and ArrayList

Tomorrow, we'll explore **Methods and Method Overloading**!

---

**Next**: [Day 06 - Methods](../Day06/README.md)

**Previous**: [Day 04 - Loops](../Day04-Loops/README.md)
