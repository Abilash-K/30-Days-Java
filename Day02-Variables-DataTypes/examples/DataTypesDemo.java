/**
 * DataTypesDemo.java
 * Demonstrates all primitive data types in Java
 */
public class DataTypesDemo {
    public static void main(String[] args) {
        // Integer types
        byte age = 25;
        short year = 2024;
        int population = 1_000_000;
        long distanceToMoon = 384_400_000L;
        
        System.out.println("=== Integer Types ===");
        System.out.println("byte age: " + age);
        System.out.println("short year: " + year);
        System.out.println("int population: " + population);
        System.out.println("long distance to moon: " + distanceToMoon + " meters");
        
        // Floating-point types
        float temperature = 36.6f;
        double pi = 3.14159265359;
        
        System.out.println("\n=== Floating-Point Types ===");
        System.out.println("float temperature: " + temperature + "°C");
        System.out.println("double pi: " + pi);
        
        // Character type
        char grade = 'A';
        char symbol = '$';
        char unicodeChar = '\u0041';  // 'A'
        
        System.out.println("\n=== Character Type ===");
        System.out.println("char grade: " + grade);
        System.out.println("char symbol: " + symbol);
        System.out.println("char unicode: " + unicodeChar);
        
        // Boolean type
        boolean isJavaFun = true;
        boolean isCodingHard = false;
        
        System.out.println("\n=== Boolean Type ===");
        System.out.println("Is Java fun? " + isJavaFun);
        System.out.println("Is coding hard? " + isCodingHard);
        
        // Reference type - String
        String message = "Hello, Java!";
        System.out.println("\n=== Reference Type (String) ===");
        System.out.println("String message: " + message);
    }
}
