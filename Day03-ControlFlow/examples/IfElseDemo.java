/**
 * IfElseDemo.java
 * Demonstrates if-else statements in Java
 */
public class IfElseDemo {
    public static void main(String[] args) {
        // Simple if statement
        int age = 20;
        if (age >= 18) {
            System.out.println("You are an adult");
        }
        
        // if-else statement
        int number = -5;
        if (number >= 0) {
            System.out.println("Positive number");
        } else {
            System.out.println("Negative number");
        }
        
        // if-else-if ladder
        int marks = 85;
        System.out.print("Grade: ");
        if (marks >= 90) {
            System.out.println("A+");
        } else if (marks >= 80) {
            System.out.println("A");
        } else if (marks >= 70) {
            System.out.println("B");
        } else if (marks >= 60) {
            System.out.println("C");
        } else {
            System.out.println("F");
        }
        
        // Nested if
        int userAge = 25;
        boolean hasLicense = true;
        
        if (userAge >= 18) {
            if (hasLicense) {
                System.out.println("You can drive");
            } else {
                System.out.println("You need a license");
            }
        } else {
            System.out.println("You are too young to drive");
        }
    }
}
