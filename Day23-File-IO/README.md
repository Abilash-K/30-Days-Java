# Day 23: File I/O and NIO

Welcome to Day 23! Today we'll learn about File I/O operations in Java.

## Table of Contents
- [File Class](#file-class)
- [Reading Files](#reading-files)
- [Writing Files](#writing-files)
- [BufferedReader and BufferedWriter](#bufferedreader-and-bufferedwriter)
- [Java NIO](#java-nio)
- [Exercises](#exercises)

## File Class

Working with files and directories.

```java
import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        File file = new File("test.txt");
        
        System.out.println("Exists: " + file.exists());
        System.out.println("Is file: " + file.isFile());
        System.out.println("Is directory: " + file.isDirectory());
        System.out.println("Name: " + file.getName());
        System.out.println("Path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        
        // Create directory
        File dir = new File("mydir");
        if (dir.mkdir()) {
            System.out.println("Directory created");
        }
        
        // List files
        File folder = new File(".");
        String[] files = folder.list();
        if (files != null) {
            for (String name : files) {
                System.out.println(name);
            }
        }
    }
}
```

## Reading Files

Multiple ways to read files.

```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReadFileDemo {
    public static void main(String[] args) {
        // Using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Using Files (NIO)
        try {
            List<String> lines = Files.readAllLines(Paths.get("file.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Using Scanner
        try (Scanner scanner = new Scanner(new File("file.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## Writing Files

Multiple ways to write files.

```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WriteFileDemo {
    public static void main(String[] args) {
        // Using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("Hello, World!");
            writer.newLine();
            writer.write("Java File I/O");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Using Files (NIO)
        List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");
        try {
            Files.write(Paths.get("output.txt"), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Using PrintWriter
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            writer.println("First line");
            writer.println("Second line");
            writer.printf("Formatted: %d %s%n", 42, "answer");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## BufferedReader and BufferedWriter

Efficient I/O with buffering.

```java
import java.io.*;

public class BufferedIODemo {
    public static void main(String[] args) {
        // Copy file
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Java NIO

Modern file I/O with java.nio.file package.

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class NIODemo {
    public static void main(String[] args) {
        Path path = Paths.get("file.txt");
        
        try {
            // Read all lines
            Files.lines(path).forEach(System.out::println);
            
            // Write lines
            Files.write(path, "New content".getBytes());
            
            // Copy file
            Files.copy(path, Paths.get("copy.txt"), StandardCopyOption.REPLACE_EXISTING);
            
            // Move file
            Files.move(path, Paths.get("moved.txt"), StandardCopyOption.REPLACE_EXISTING);
            
            // Delete file
            Files.deleteIfExists(Paths.get("temp.txt"));
            
            // Create directory
            Files.createDirectories(Paths.get("dir/subdir"));
            
            // List files
            try (Stream<Path> paths = Files.walk(Paths.get("."))) {
                paths.filter(Files::isRegularFile)
                     .forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Exercises

### Exercise 1: File Statistics
Read file and count lines, words, characters

### Exercise 2: File Copy
Copy file with progress indication

### Exercise 3: Directory Browser
List all files in directory recursively

### Exercise 4: Text File Search
Search for text pattern in files

### Exercise 5: Log File Analyzer
Parse log file and extract information

## Summary

Today you learned:
- ✅ File class operations
- ✅ Reading files
- ✅ Writing files
- ✅ Buffered I/O
- ✅ Java NIO
- ✅ File operations

Tomorrow, we'll explore **Multithreading - Basics**!

---

**Next**: [Day 24 - Multithreading Basics](../Day24-Multithreading-Basics/README.md)

**Previous**: [Day 22 - Interface Methods](../Day22-Interface-Methods/README.md)
