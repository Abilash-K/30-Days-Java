/**
 * ConstantsDemo.java
 * Demonstrates the use of constants in Java
 */
public class ConstantsDemo {
    // Class-level constants
    public static final double PI = 3.14159265359;
    public static final int MAX_STUDENTS = 50;
    public static final String SCHOOL_NAME = "Java Academy";
    
    public static void main(String[] args) {
        // Using constants
        System.out.println("=== Using Constants ===");
        System.out.println("School Name: " + SCHOOL_NAME);
        System.out.println("Maximum Students: " + MAX_STUDENTS);
        System.out.println("Value of PI: " + PI);
        
        // Calculate circle properties
        double radius = 5.0;
        double area = PI * radius * radius;
        double circumference = 2 * PI * radius;
        
        System.out.println("\n=== Circle Calculations ===");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + area);
        System.out.println("Circumference: " + circumference);
        
        // Local constants
        final int DAYS_IN_WEEK = 7;
        final int HOURS_IN_DAY = 24;
        int totalHoursInWeek = DAYS_IN_WEEK * HOURS_IN_DAY;
        
        System.out.println("\n=== Time Calculations ===");
        System.out.println("Days in week: " + DAYS_IN_WEEK);
        System.out.println("Hours in day: " + HOURS_IN_DAY);
        System.out.println("Total hours in week: " + totalHoursInWeek);
        
        // Attempting to modify constant (will cause compilation error)
        // PI = 3.14;  // Uncommenting this line will cause an error
        
        System.out.println("\nConstants cannot be modified after initialization!");
    }
}
