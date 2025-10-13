/**
 * CommentsDemo.java
 * This program demonstrates different types of comments in Java
 * 
 * @author 30 Days of Java
 * @version 1.0
 * @since 2024
 */
public class CommentsDemo {
    
    /**
     * The main method serves as the entry point for the application.
     * It demonstrates the use of various comment styles in Java.
     * 
     * @param args Command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // This is a single-line comment
        // It's useful for brief explanations
        
        System.out.println("Learning about comments in Java");
        
        /*
         * This is a multi-line comment
         * It can span multiple lines
         * Use it for longer explanations
         */
        
        /* You can also write multi-line comments in a single line */
        
        System.out.println("Comments help make code more readable!");
        
        // Best practices:
        // 1. Comment WHY, not WHAT
        // 2. Keep comments up-to-date
        // 3. Use Javadoc for public APIs
        // 4. Avoid obvious comments
        
        // Calculate circle area (example of a good comment explaining intention)
        double radius = 5.0;
        double area = Math.PI * radius * radius;  // Formula: π * r²
        
        System.out.println("Area of circle with radius " + radius + " is: " + area);
    }
}
