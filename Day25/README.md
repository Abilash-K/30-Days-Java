# Day 25: Multithreading - Advanced Concepts

Welcome to Day 25! Today we'll learn advanced multithreading concepts.

## Table of Contents
- [ExecutorService](#executorservice)
- [Thread Pools](#thread-pools)
- [Callable and Future](#callable-and-future)
- [CompletableFuture](#completablefuture)
- [Concurrent Collections](#concurrent-collections)
- [Exercises](#exercises)

## ExecutorService

Manage thread execution with ExecutorService.

```java
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " by " + 
                                 Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        executor.shutdown();
    }
}
```

## Thread Pools

Different types of thread pools.

```java
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // Fixed thread pool
        ExecutorService fixed = Executors.newFixedThreadPool(2);
        
        // Cached thread pool
        ExecutorService cached = Executors.newCachedThreadPool();
        
        // Single thread executor
        ExecutorService single = Executors.newSingleThreadExecutor();
        
        // Scheduled thread pool
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        
        // Schedule task
        scheduled.schedule(() -> {
            System.out.println("Delayed task");
        }, 2, TimeUnit.SECONDS);
        
        // Schedule at fixed rate
        scheduled.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task");
        }, 0, 1, TimeUnit.SECONDS);
        
        // Shutdown
        scheduled.shutdown();
    }
}
```

## Callable and Future

Return values from threads.

```java
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };
        
        Future<Integer> future = executor.submit(task);
        
        System.out.println("Waiting for result...");
        Integer result = future.get();  // Blocks until complete
        System.out.println("Result: " + result);
        
        // Multiple tasks
        Callable<String> task1 = () -> "Task 1";
        Callable<String> task2 = () -> "Task 2";
        
        Future<String> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        
        System.out.println(future1.get());
        System.out.println(future2.get());
        
        executor.shutdown();
    }
}
```

## CompletableFuture

Asynchronous programming with CompletableFuture.

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // Simple async task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });
        
        future.thenAccept(result -> System.out.println("Result: " + result));
        
        // Chain operations
        CompletableFuture<String> chained = CompletableFuture.supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World")
            .thenApply(String::toUpperCase);
        
        chained.thenAccept(System.out::println);
        
        // Combine futures
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        
        CompletableFuture<Integer> combined = future1.thenCombine(future2, (a, b) -> a + b);
        combined.thenAccept(result -> System.out.println("Sum: " + result));
        
        // Wait for completion
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## Concurrent Collections

Thread-safe collections.

```java
import java.util.concurrent.*;

public class ConcurrentCollectionsDemo {
    public static void main(String[] args) {
        // ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // CopyOnWriteArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        
        // ConcurrentLinkedQueue
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("Task 1");
        queue.offer("Task 2");
        
        // BlockingQueue
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        
        // Producer
        new Thread(() -> {
            try {
                blockingQueue.put("Message");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        // Consumer
        new Thread(() -> {
            try {
                String message = blockingQueue.take();
                System.out.println("Received: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
```

## Exercises

### Exercise 1: Parallel Processing
Process list of numbers in parallel

### Exercise 2: Task Scheduler
Create task scheduler with priorities

### Exercise 3: Async API Calls
Simulate multiple API calls concurrently

### Exercise 4: Thread-Safe Cache
Implement thread-safe cache

### Exercise 5: Producer-Consumer
Implement using BlockingQueue

## Summary

Today you learned:
- ✅ ExecutorService
- ✅ Thread pools
- ✅ Callable and Future
- ✅ CompletableFuture
- ✅ Concurrent collections
- ✅ Asynchronous programming

Tomorrow, we'll explore **Java 11 - New Features**!

---

**Next**: [Day 26 - Java 11 Features](../Day26/README.md)

**Previous**: [Day 24 - Multithreading Basics](../Day24/README.md)
