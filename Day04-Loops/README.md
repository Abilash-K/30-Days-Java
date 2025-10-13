# Day 04: Loops and Iterations

Welcome to Day 4! Today we'll learn about loops - one of the most powerful control structures in programming.

## Table of Contents
- [Introduction](#introduction)
- [for Loop](#for-loop)
- [while Loop](#while-loop)
- [do-while Loop](#do-while-loop)
- [Enhanced for Loop](#enhanced-for-loop)
- [Loop Control Statements](#loop-control-statements)
- [Nested Loops](#nested-loops)
- [Exercises](#exercises)

## Introduction

Loops allow you to execute a block of code repeatedly. Java provides several types of loops to handle different scenarios.

## for Loop

The most commonly used loop when you know how many times to iterate.

### Syntax:
```java
for (initialization; condition; update) {
    // Code to execute
}
```

### Example:
```java
for (int i = 0; i < 5; i++) {
    System.out.println("Count: " + i);
}
// Output: Count: 0, Count: 1, Count: 2, Count: 3, Count: 4
```

## while Loop

Used when you don't know the number of iterations in advance.

### Syntax:
```java
while (condition) {
    // Code to execute
}
```

## do-while Loop

Executes at least once before checking the condition.

### Syntax:
```java
do {
    // Code to execute
} while (condition);
```

## Enhanced for Loop

Also called "for-each" loop, used to iterate over arrays and collections.

### Syntax:
```java
for (dataType item : collection) {
    // Code to execute
}
```

### Example:
```java
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}
```

## Loop Control Statements

### break
Exits the loop immediately.

### continue
Skips the current iteration and continues with the next.

## Nested Loops

Loops inside loops for multidimensional iteration.

## Summary

Today you learned:
- ✅ for loops for counted iterations
- ✅ while loops for conditional iterations
- ✅ do-while loops
- ✅ Enhanced for loops for collections
- ✅ break and continue statements
- ✅ Nested loops

Tomorrow, we'll explore **Arrays and ArrayList**!

---

**Next**: [Day 05 - Arrays and ArrayList](../Day05-Arrays/README.md)

**Previous**: [Day 03 - Control Flow](../Day03-ControlFlow/README.md)
