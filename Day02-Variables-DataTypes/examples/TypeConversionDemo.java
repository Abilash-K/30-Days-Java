/**
 * TypeConversionDemo.java
 * Demonstrates type conversion (implicit and explicit)
 */
public class TypeConversionDemo {
    public static void main(String[] args) {
        // Implicit type conversion (Widening)
        System.out.println("=== Implicit Type Conversion (Widening) ===");
        byte byteValue = 100;
        int intValue = byteValue;  // byte to int (automatic)
        long longValue = intValue;  // int to long (automatic)
        float floatValue = longValue;  // long to float (automatic)
        double doubleValue = floatValue;  // float to double (automatic)
        
        System.out.println("byte value: " + byteValue);
        System.out.println("Converted to int: " + intValue);
        System.out.println("Converted to long: " + longValue);
        System.out.println("Converted to float: " + floatValue);
        System.out.println("Converted to double: " + doubleValue);
        
        // Explicit type conversion (Narrowing/Casting)
        System.out.println("\n=== Explicit Type Conversion (Narrowing) ===");
        double doubleNum = 9.78;
        int intNum = (int) doubleNum;  // Explicit cast
        System.out.println("double value: " + doubleNum);
        System.out.println("Casted to int: " + intNum);
        System.out.println("Data lost: " + (doubleNum - intNum));
        
        // Potential data loss
        System.out.println("\n=== Data Loss in Narrowing ===");
        int largeInt = 130;
        byte smallByte = (byte) largeInt;
        System.out.println("int value: " + largeInt);
        System.out.println("Casted to byte: " + smallByte);
        System.out.println("(byte can only hold -128 to 127)");
        
        // Type promotion in expressions
        System.out.println("\n=== Type Promotion in Expressions ===");
        byte b1 = 10;
        byte b2 = 20;
        // byte b3 = b1 + b2;  // This would cause compilation error
        int b3 = b1 + b2;  // Correct - result is promoted to int
        System.out.println("byte1 + byte2 = " + b3 + " (result is int)");
        
        // Division examples
        System.out.println("\n=== Integer vs Floating-Point Division ===");
        int x = 10, y = 3;
        System.out.println("int 10 / 3 = " + (x / y));  // 3
        System.out.println("double 10.0 / 3.0 = " + (10.0 / 3.0));  // 3.333...
        System.out.println("int 10 / double 3.0 = " + (x / 3.0));  // 3.333...
    }
}
