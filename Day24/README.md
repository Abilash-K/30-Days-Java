# Day 24: Multithreading - Basics

Welcome to Day 24! Today we'll learn about multithreading in Java.

## Table of Contents
- [What is Multithreading?](#what-is-multithreading)
- [Creating Threads](#creating-threads)
- [Thread Methods](#thread-methods)
- [Thread States](#thread-states)
- [Synchronization](#synchronization)
- [Exercises](#exercises)

## What is Multithreading?

Multithreading allows concurrent execution of multiple threads.

### Benefits:
- Better CPU utilization
- Improved performance
- Responsive applications
- Concurrent task execution

## Creating Threads

Two ways to create threads.

### Extending Thread Class:
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        
        t1.start();
        t2.start();
    }
}
```

### Implementing Runnable:
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        
        t1.start();
        t2.start();
        
        // Using lambda
        Thread t3 = new Thread(() -> {
            System.out.println("Lambda thread");
        });
        t3.start();
    }
}
```

## Thread Methods

Common thread methods.

```java
public class ThreadMethodsDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Running: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                    return;
                }
            }
        });
        
        t.start();                        // Start thread
        System.out.println("Name: " + t.getName());
        System.out.println("Priority: " + t.getPriority());
        System.out.println("Alive: " + t.isAlive());
        
        t.join();                         // Wait for completion
        System.out.println("Thread finished");
    }
}
```

## Thread States

Threads go through different states.

```java
public class ThreadStatesDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            synchronized (ThreadStatesDemo.class) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        System.out.println("NEW: " + t.getState());
        
        t.start();
        System.out.println("RUNNABLE: " + t.getState());
        
        Thread.sleep(100);
        System.out.println("TIMED_WAITING: " + t.getState());
        
        t.join();
        System.out.println("TERMINATED: " + t.getState());
    }
}
```

## Synchronization

Prevent race conditions with synchronized keyword.

```java
class Counter {
    private int count = 0;
    
    // Synchronized method
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Count: " + counter.getCount());
    }
}
```

### Synchronized Block:
```java
class BankAccount {
    private double balance = 0;
    
    public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
    
    public double getBalance() {
        return balance;
    }
}
```

## Exercises

### Exercise 1: Print Numbers
Create two threads printing odd and even numbers

### Exercise 2: Producer Consumer
Implement producer-consumer pattern

### Exercise 3: Thread Pool
Create simple thread pool

### Exercise 4: Bank Transactions
Simulate concurrent bank transactions

### Exercise 5: Download Manager
Simulate multiple file downloads

## Summary

Today you learned:
- ✅ What multithreading is
- ✅ Creating threads
- ✅ Thread methods
- ✅ Thread states
- ✅ Synchronization
- ✅ Race conditions

Tomorrow, we'll explore **Multithreading - Advanced Concepts**!

---

**Next**: [Day 25 - Multithreading Advanced](../Day25/README.md)

**Previous**: [Day 23 - File I/O](../Day23/README.md)
