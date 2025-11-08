# Day 18: Stream API - Part 2

Welcome to Day 18! Today we'll dive deeper into Stream API with advanced operations.

## Table of Contents
- [collect()](#collect)
- [Collectors](#collectors)
- [reduce()](#reduce)
- [flatMap()](#flatmap)
- [Matching Operations](#matching-operations)
- [Finding Operations](#finding-operations)
- [Numeric Streams](#numeric-streams)
- [Parallel Streams](#parallel-streams)
- [Exercises](#exercises)

## collect()

Collect stream elements into a collection or other data structure.

### Basic Collection:
```java
import java.util.*;
import java.util.stream.*;

public class CollectDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Collect to List
        List<String> list = names.stream()
                                 .filter(n -> n.length() > 3)
                                 .collect(Collectors.toList());
        System.out.println("List: " + list);
        
        // Collect to Set
        Set<String> set = names.stream()
                               .collect(Collectors.toSet());
        System.out.println("Set: " + set);
        
        // Collect to specific collection
        ArrayList<String> arrayList = names.stream()
                                           .collect(Collectors.toCollection(ArrayList::new));
        
        LinkedHashSet<String> linkedSet = names.stream()
                                               .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
```

## Collectors

Predefined collectors for common operations.

### Joining:
```java
import java.util.*;
import java.util.stream.*;

public class CollectorsJoinDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Join with comma
        String joined = names.stream()
                            .collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);
        
        // Join with prefix and suffix
        String formatted = names.stream()
                               .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Formatted: " + formatted);
    }
}
```

### Counting and Statistics:
```java
import java.util.*;
import java.util.stream.*;

public class StatisticsDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Count
        long count = numbers.stream()
                           .filter(n -> n % 2 == 0)
                           .count();
        System.out.println("Even count: " + count);
        
        // Sum
        int sum = numbers.stream()
                        .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum: " + sum);
        
        // Average
        double avg = numbers.stream()
                           .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Average: " + avg);
        
        // Statistics
        IntSummaryStatistics stats = numbers.stream()
                                           .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
    }
}
```

### Grouping:
```java
import java.util.*;
import java.util.stream.*;

class Person {
    private String name;
    private String city;
    private int age;
    
    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
    
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getAge() { return age; }
}

public class GroupingDemo {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", "NYC", 25),
            new Person("Bob", "LA", 30),
            new Person("Charlie", "NYC", 35),
            new Person("David", "LA", 28)
        );
        
        // Group by city
        Map<String, List<Person>> byCity = people.stream()
                                                 .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("Grouped by city:");
        byCity.forEach((city, persons) -> {
            System.out.println(city + ": " + persons.size() + " people");
        });
        
        // Group and count
        Map<String, Long> countByCity = people.stream()
                                             .collect(Collectors.groupingBy(
                                                 Person::getCity,
                                                 Collectors.counting()
                                             ));
        System.out.println("\nCount by city: " + countByCity);
        
        // Group names by city
        Map<String, List<String>> namesByCity = people.stream()
                                                      .collect(Collectors.groupingBy(
                                                          Person::getCity,
                                                          Collectors.mapping(Person::getName, Collectors.toList())
                                                      ));
        System.out.println("\nNames by city: " + namesByCity);
    }
}
```

### Partitioning:
```java
import java.util.*;
import java.util.stream.*;

public class PartitioningDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Partition even/odd
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                                                        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even: " + partitioned.get(true));
        System.out.println("Odd: " + partitioned.get(false));
        
        // Partition with count
        Map<Boolean, Long> counts = numbers.stream()
                                          .collect(Collectors.partitioningBy(
                                              n -> n > 5,
                                              Collectors.counting()
                                          ));
        System.out.println("\nGreater than 5: " + counts.get(true));
        System.out.println("Less or equal 5: " + counts.get(false));
    }
}
```

## reduce()

Reduce stream to a single value.

```java
import java.util.*;
import java.util.stream.*;

public class ReduceDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Sum with reduce
        Optional<Integer> sum = numbers.stream()
                                      .reduce((a, b) -> a + b);
        System.out.println("Sum: " + sum.orElse(0));
        
        // Sum with identity
        int sum2 = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        System.out.println("Sum with identity: " + sum2);
        
        // Product
        int product = numbers.stream()
                            .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
        
        // Max
        Optional<Integer> max = numbers.stream()
                                      .reduce(Integer::max);
        System.out.println("Max: " + max.orElse(0));
        
        // Min
        Optional<Integer> min = numbers.stream()
                                      .reduce(Integer::min);
        System.out.println("Min: " + min.orElse(0));
        
        // Concatenate strings
        List<String> words = Arrays.asList("Hello", " ", "World", "!");
        String result = words.stream()
                            .reduce("", (a, b) -> a + b);
        System.out.println("Concatenated: " + result);
    }
}
```

## flatMap()

Flatten nested structures into a single stream.

```java
import java.util.*;
import java.util.stream.*;

public class FlatMapDemo {
    public static void main(String[] args) {
        // Flatten list of lists
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        
        List<Integer> flattened = listOfLists.stream()
                                             .flatMap(List::stream)
                                             .collect(Collectors.toList());
        System.out.println("Flattened: " + flattened);
        
        // Flatten array of arrays
        String[][] array = {
            {"a", "b"},
            {"c", "d"},
            {"e", "f"}
        };
        
        List<String> flatArray = Arrays.stream(array)
                                       .flatMap(Arrays::stream)
                                       .collect(Collectors.toList());
        System.out.println("Flat array: " + flatArray);
        
        // Split and flatten strings
        List<String> sentences = Arrays.asList(
            "Hello World",
            "Java Streams",
            "Are Awesome"
        );
        
        List<String> words = sentences.stream()
                                      .flatMap(s -> Arrays.stream(s.split(" ")))
                                      .collect(Collectors.toList());
        System.out.println("Words: " + words);
        
        // Unique words
        Set<String> uniqueWords = sentences.stream()
                                           .flatMap(s -> Arrays.stream(s.split(" ")))
                                           .map(String::toLowerCase)
                                           .collect(Collectors.toSet());
        System.out.println("Unique words: " + uniqueWords);
    }
}
```

## Matching Operations

Test if elements match certain criteria.

```java
import java.util.*;

public class MatchingDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // anyMatch - at least one matches
        boolean anyEven = numbers.stream()
                                .anyMatch(n -> n % 2 == 0);
        System.out.println("Any even? " + anyEven);
        
        // allMatch - all match
        boolean allPositive = numbers.stream()
                                    .allMatch(n -> n > 0);
        System.out.println("All positive? " + allPositive);
        
        // noneMatch - none match
        boolean noneNegative = numbers.stream()
                                     .noneMatch(n -> n < 0);
        System.out.println("None negative? " + noneNegative);
        
        // String matching
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        boolean anyStartsWithA = names.stream()
                                      .anyMatch(n -> n.startsWith("A"));
        System.out.println("\nAny starts with A? " + anyStartsWithA);
        
        boolean allLongerThan2 = names.stream()
                                      .allMatch(n -> n.length() > 2);
        System.out.println("All longer than 2? " + allLongerThan2);
    }
}
```

## Finding Operations

Find elements in stream.

```java
import java.util.*;

public class FindingDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        // findFirst
        Optional<Integer> first = numbers.stream()
                                        .filter(n -> n > 5)
                                        .findFirst();
        System.out.println("First > 5: " + first.orElse(0));
        
        // findAny (useful in parallel streams)
        Optional<Integer> any = numbers.stream()
                                      .filter(n -> n > 5)
                                      .findAny();
        System.out.println("Any > 5: " + any.orElse(0));
        
        // Find with objects
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
        
        Optional<String> startsWithC = names.stream()
                                            .filter(n -> n.startsWith("C"))
                                            .findFirst();
        System.out.println("\nFirst starts with C: " + startsWithC.orElse("None"));
        
        // Using Optional
        names.stream()
             .filter(n -> n.startsWith("A"))
             .findFirst()
             .ifPresent(name -> System.out.println("Found: " + name));
    }
}
```

## Numeric Streams

Specialized streams for primitives (IntStream, LongStream, DoubleStream).

```java
import java.util.stream.*;

public class NumericStreamDemo {
    public static void main(String[] args) {
        // IntStream
        IntStream intStream = IntStream.range(1, 6);  // 1,2,3,4,5
        intStream.forEach(System.out::println);
        
        // rangeClosed
        System.out.println("\nRangeClosed:");
        IntStream.rangeClosed(1, 5)  // 1,2,3,4,5
                 .forEach(System.out::println);
        
        // Sum
        int sum = IntStream.rangeClosed(1, 10).sum();
        System.out.println("\nSum 1-10: " + sum);
        
        // Average
        OptionalDouble avg = IntStream.rangeClosed(1, 10).average();
        System.out.println("Average: " + avg.orElse(0));
        
        // Max/Min
        OptionalInt max = IntStream.of(1, 5, 3, 9, 2).max();
        OptionalInt min = IntStream.of(1, 5, 3, 9, 2).min();
        System.out.println("Max: " + max.orElse(0));
        System.out.println("Min: " + min.orElse(0));
        
        // Convert to Stream
        Stream<Integer> boxedStream = IntStream.range(1, 5)
                                              .boxed();
        
        // mapToInt
        List<String> strings = Arrays.asList("1", "2", "3");
        int sumParsed = strings.stream()
                              .mapToInt(Integer::parseInt)
                              .sum();
        System.out.println("\nSum of parsed: " + sumParsed);
    }
}
```

## Parallel Streams

Process streams in parallel for better performance.

```java
import java.util.*;
import java.util.stream.*;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            numbers.add(i);
        }
        
        // Sequential stream
        long start = System.currentTimeMillis();
        long sumSeq = numbers.stream()
                            .mapToLong(i -> i)
                            .sum();
        long timeSeq = System.currentTimeMillis() - start;
        System.out.println("Sequential sum: " + sumSeq + " (" + timeSeq + " ms)");
        
        // Parallel stream
        start = System.currentTimeMillis();
        long sumPar = numbers.parallelStream()
                            .mapToLong(i -> i)
                            .sum();
        long timePar = System.currentTimeMillis() - start;
        System.out.println("Parallel sum: " + sumPar + " (" + timePar + " ms)");
        
        // Convert to parallel
        long sum = numbers.stream()
                         .parallel()
                         .mapToLong(i -> i)
                         .sum();
        System.out.println("Converted to parallel: " + sum);
        
        // Check if parallel
        boolean isParallel = numbers.parallelStream().isParallel();
        System.out.println("Is parallel? " + isParallel);
    }
}
```

## Complete Example

```java
import java.util.*;
import java.util.stream.*;

class Employee {
    private String name;
    private String department;
    private double salary;
    
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return name + " (" + department + "): $" + salary;
    }
}

public class EmployeeAnalysis {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000),
            new Employee("Bob", "HR", 50000),
            new Employee("Charlie", "IT", 80000),
            new Employee("David", "Sales", 60000),
            new Employee("Eve", "HR", 55000),
            new Employee("Frank", "IT", 70000)
        );
        
        // Total salary
        double totalSalary = employees.stream()
                                     .mapToDouble(Employee::getSalary)
                                     .sum();
        System.out.println("Total salary: $" + totalSalary);
        
        // Average salary
        double avgSalary = employees.stream()
                                   .mapToDouble(Employee::getSalary)
                                   .average()
                                   .orElse(0);
        System.out.println("Average salary: $" + avgSalary);
        
        // Highest paid employee
        Optional<Employee> highest = employees.stream()
                                             .max(Comparator.comparing(Employee::getSalary));
        highest.ifPresent(e -> System.out.println("Highest paid: " + e));
        
        // Group by department
        Map<String, List<Employee>> byDept = employees.stream()
                                                     .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\nEmployees by department:");
        byDept.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps.size());
        });
        
        // Average salary by department
        Map<String, Double> avgByDept = employees.stream()
                                                .collect(Collectors.groupingBy(
                                                    Employee::getDepartment,
                                                    Collectors.averagingDouble(Employee::getSalary)
                                                ));
        System.out.println("\nAverage salary by department:");
        avgByDept.forEach((dept, avg) -> 
            System.out.println(dept + ": $" + String.format("%.2f", avg))
        );
        
        // Employees earning > 60000
        List<String> highEarners = employees.stream()
                                           .filter(e -> e.getSalary() > 60000)
                                           .map(Employee::getName)
                                           .sorted()
                                           .collect(Collectors.toList());
        System.out.println("\nHigh earners (> $60000): " + highEarners);
        
        // Department names
        String departments = employees.stream()
                                     .map(Employee::getDepartment)
                                     .distinct()
                                     .sorted()
                                     .collect(Collectors.joining(", "));
        System.out.println("\nDepartments: " + departments);
    }
}
```

## Exercises

### Exercise 1: Student Grades
Calculate average, highest, lowest grades from list of students

### Exercise 2: Word Statistics
Count words, find longest word, group by length

### Exercise 3: Transaction Analysis
Sum, average, group transactions by category

### Exercise 4: Data Transformation
Flatten nested lists, remove duplicates, sort

### Exercise 5: Employee Report
Generate department-wise salary report with statistics

## Summary

Today you learned:
- ✅ collect() and Collectors
- ✅ reduce() for aggregation
- ✅ flatMap() for flattening
- ✅ Matching and finding operations
- ✅ Numeric streams
- ✅ Parallel streams
- ✅ Advanced stream operations

Tomorrow, we'll explore **Optional Class**!

---

**Next**: [Day 19 - Optional Class](../Day19-Optional/README.md)

**Previous**: [Day 17 - Stream API - Part 1](../Day17-Streams-Part1/README.md)
