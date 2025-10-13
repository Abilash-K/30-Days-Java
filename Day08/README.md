# Day 08: Inheritance and Polymorphism

Welcome to Day 8! Today we'll explore two fundamental OOP concepts: Inheritance and Polymorphism.

## Table of Contents
- [Inheritance](#inheritance)
- [Types of Inheritance](#types-of-inheritance)
- [Method Overriding](#method-overriding)
- [Polymorphism](#polymorphism)
- [super Keyword](#super-keyword)
- [Exercises](#exercises)

## Inheritance

Inheritance is a mechanism where one class acquires the properties and behaviors of another class.

### Syntax:
```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child class members + inherited members
}
```

## Types of Inheritance

Java supports Single, Multilevel, and Hierarchical inheritance. Multiple inheritance is achieved through interfaces.

## Method Overriding

Child class provides specific implementation of a parent class method.

```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

## Polymorphism

Ability of an object to take multiple forms.

### Compile-time Polymorphism (Method Overloading)
### Runtime Polymorphism (Method Overriding)

## super Keyword

References to parent class - access variables, methods, and constructors.

## Summary

Today you learned:
- ✅ Inheritance basics
- ✅ Types of inheritance
- ✅ Method overriding
- ✅ Polymorphism
- ✅ super keyword

Tomorrow, we'll explore **Abstraction and Interfaces**!

---

**Next**: [Day 09 - Abstraction and Interfaces](../Day09/README.md)

**Previous**: [Day 07 - Classes and Objects](../Day07/README.md)
