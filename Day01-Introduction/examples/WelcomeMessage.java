/**
 * WelcomeMessage.java
 * Demonstrates multiple print statements and different output methods
 */
public class WelcomeMessage {
    public static void main(String[] args) {
        // Using println - adds newline after output
        System.out.println("Welcome to 30 Days of Java!");
        System.out.println("This is Day 01: Introduction to Java");
        
        // Empty line
        System.out.println();
        
        // Using print - no newline
        System.out.print("Java is ");
        System.out.print("awesome! ");
        System.out.println();
        
        // Using printf - formatted output
        String courseName = "Java Programming";
        int days = 30;
        System.out.printf("Course: %s, Duration: %d days%n", courseName, days);
    }
}
