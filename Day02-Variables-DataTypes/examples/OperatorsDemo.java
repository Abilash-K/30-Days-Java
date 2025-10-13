/**
 * OperatorsDemo.java
 * Demonstrates various operators in Java
 */
public class OperatorsDemo {
    public static void main(String[] args) {
        // Arithmetic operators
        int a = 10, b = 3;
        System.out.println("=== Arithmetic Operators ===");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));
        
        // Unary operators
        System.out.println("\n=== Unary Operators ===");
        int x = 5;
        System.out.println("x = " + x);
        System.out.println("++x = " + (++x));  // Pre-increment: 6
        System.out.println("x = " + x);        // 6
        System.out.println("x++ = " + (x++));  // Post-increment: 6
        System.out.println("x = " + x);        // 7
        
        // Relational operators
        System.out.println("\n=== Relational Operators ===");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));
        
        // Logical operators
        System.out.println("\n=== Logical Operators ===");
        boolean p = true, q = false;
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("p && q: " + (p && q));
        System.out.println("p || q: " + (p || q));
        System.out.println("!p: " + (!p));
        
        // Assignment operators
        System.out.println("\n=== Assignment Operators ===");
        int num = 10;
        System.out.println("num = " + num);
        num += 5;
        System.out.println("num += 5: " + num);
        num -= 3;
        System.out.println("num -= 3: " + num);
        num *= 2;
        System.out.println("num *= 2: " + num);
        num /= 4;
        System.out.println("num /= 4: " + num);
        
        // Ternary operator
        System.out.println("\n=== Ternary Operator ===");
        int age = 20;
        String status = (age >= 18) ? "Adult" : "Minor";
        System.out.println("Age: " + age + " -> Status: " + status);
    }
}
