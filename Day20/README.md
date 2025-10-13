# Day 20: Date and Time API

Welcome to Day 20! Today we'll explore the modern Date and Time API introduced in Java 8.

## Table of Contents
- [Introduction](#introduction)
- [LocalDate](#localdate)
- [LocalTime](#localtime)
- [LocalDateTime](#localdatetime)
- [ZonedDateTime](#zoneddatetime)
- [Period and Duration](#period-and-duration)
- [Formatting and Parsing](#formatting-and-parsing)
- [Exercises](#exercises)

## Introduction

Java 8 introduced a new Date and Time API (`java.time` package) to replace the old `java.util.Date` and `java.util.Calendar`.

### Why New API?
- **Immutable**: Thread-safe
- **Clear**: Better method names
- **Comprehensive**: More functionality
- **ISO-8601**: Standard calendar system

### Main Classes:
- `LocalDate` - Date without time
- `LocalTime` - Time without date
- `LocalDateTime` - Date and time
- `ZonedDateTime` - Date and time with timezone
- `Instant` - Timestamp
- `Period` - Date-based amount (years, months, days)
- `Duration` - Time-based amount (hours, minutes, seconds)

## LocalDate

Represents a date (year, month, day) without time.

### Creating LocalDate

```java
import java.time.LocalDate;

// Current date
LocalDate today = LocalDate.now();
System.out.println(today);  // 2024-01-15

// Specific date
LocalDate date1 = LocalDate.of(2024, 1, 15);
LocalDate date2 = LocalDate.of(2024, Month.JANUARY, 15);

// Parse from string
LocalDate date3 = LocalDate.parse("2024-01-15");
```

### LocalDate Methods

```java
LocalDate today = LocalDate.now();

// Get components
int year = today.getYear();           // 2024
int month = today.getMonthValue();    // 1
int day = today.getDayOfMonth();      // 15
DayOfWeek dayOfWeek = today.getDayOfWeek();  // MONDAY

// Manipulate dates
LocalDate tomorrow = today.plusDays(1);
LocalDate nextWeek = today.plusWeeks(1);
LocalDate nextMonth = today.plusMonths(1);
LocalDate nextYear = today.plusYears(1);

LocalDate yesterday = today.minusDays(1);

// With methods (replace components)
LocalDate newDate = today.withYear(2025);
LocalDate firstDay = today.withDayOfMonth(1);

// Comparisons
boolean isBefore = date1.isBefore(date2);
boolean isAfter = date1.isAfter(date2);
boolean isEqual = date1.isEqual(date2);

// Leap year
boolean isLeap = today.isLeapYear();
```

## LocalTime

Represents time without date.

### Creating LocalTime

```java
import java.time.LocalTime;

// Current time
LocalTime now = LocalTime.now();
System.out.println(now);  // 14:30:45.123

// Specific time
LocalTime time1 = LocalTime.of(14, 30);          // 14:30
LocalTime time2 = LocalTime.of(14, 30, 45);      // 14:30:45
LocalTime time3 = LocalTime.of(14, 30, 45, 123); // with nanos

// Parse
LocalTime time4 = LocalTime.parse("14:30:45");
```

### LocalTime Methods

```java
LocalTime time = LocalTime.now();

// Get components
int hour = time.getHour();         // 14
int minute = time.getMinute();     // 30
int second = time.getSecond();     // 45

// Manipulate time
LocalTime later = time.plusHours(2);
LocalTime earlier = time.minusMinutes(30);

// Constants
LocalTime midnight = LocalTime.MIDNIGHT;  // 00:00
LocalTime noon = LocalTime.NOON;          // 12:00
LocalTime max = LocalTime.MAX;            // 23:59:59.999999999
LocalTime min = LocalTime.MIN;            // 00:00
```

## LocalDateTime

Combines date and time.

### Creating LocalDateTime

```java
import java.time.LocalDateTime;

// Current date-time
LocalDateTime now = LocalDateTime.now();

// Specific date-time
LocalDateTime dt1 = LocalDateTime.of(2024, 1, 15, 14, 30);
LocalDateTime dt2 = LocalDateTime.of(
    LocalDate.of(2024, 1, 15),
    LocalTime.of(14, 30)
);

// Parse
LocalDateTime dt3 = LocalDateTime.parse("2024-01-15T14:30:45");
```

### LocalDateTime Methods

```java
LocalDateTime dateTime = LocalDateTime.now();

// Get components
LocalDate date = dateTime.toLocalDate();
LocalTime time = dateTime.toLocalTime();

// Manipulate
LocalDateTime future = dateTime.plusDays(5).plusHours(3);

// Conversions
String isoString = dateTime.toString();  // ISO-8601 format
```

## ZonedDateTime

Date-time with timezone information.

```java
import java.time.ZonedDateTime;
import java.time.ZoneId;

// Current date-time with system timezone
ZonedDateTime now = ZonedDateTime.now();

// Specific timezone
ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

// Convert timezone
ZonedDateTime utc = now.withZoneSameInstant(ZoneId.of("UTC"));

// Available zones
Set<String> zones = ZoneId.getAvailableZoneIds();
```

## Period and Duration

### Period
Date-based amount (years, months, days).

```java
import java.time.Period;

// Create period
Period period1 = Period.of(1, 2, 3);  // 1 year, 2 months, 3 days
Period period2 = Period.ofDays(10);
Period period3 = Period.ofMonths(3);

// Between dates
LocalDate start = LocalDate.of(2024, 1, 1);
LocalDate end = LocalDate.of(2024, 12, 31);
Period between = Period.between(start, end);

System.out.println(between.getYears());   // 0
System.out.println(between.getMonths());  // 11
System.out.println(between.getDays());    // 30

// Add to date
LocalDate newDate = start.plus(period1);
```

### Duration
Time-based amount (hours, minutes, seconds).

```java
import java.time.Duration;

// Create duration
Duration duration1 = Duration.ofHours(5);
Duration duration2 = Duration.ofMinutes(30);
Duration duration3 = Duration.ofSeconds(45);

// Between times
LocalTime time1 = LocalTime.of(9, 0);
LocalTime time2 = LocalTime.of(17, 30);
Duration workDay = Duration.between(time1, time2);

System.out.println(workDay.toHours());    // 8
System.out.println(workDay.toMinutes());  // 510

// Add to time
LocalTime newTime = time1.plus(duration1);
```

## Formatting and Parsing

### DateTimeFormatter

```java
import java.time.format.DateTimeFormatter;

LocalDateTime dateTime = LocalDateTime.now();

// Predefined formatters
String iso = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
String basic = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);

// Custom patterns
DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

String formatted1 = dateTime.format(formatter1);  // "15/01/2024"
String formatted2 = dateTime.format(formatter2);  // "2024-01-15 14:30:45"
String formatted3 = dateTime.format(formatter3);  // "Monday, January 15, 2024"

// Parsing
LocalDate parsedDate = LocalDate.parse("15/01/2024", formatter1);
LocalDateTime parsedDT = LocalDateTime.parse("2024-01-15 14:30:45", formatter2);
```

### Common Pattern Symbols

```
y   - Year
M   - Month
d   - Day
H   - Hour (0-23)
m   - Minute
s   - Second
E   - Day of week
a   - AM/PM
```

## Practical Examples

### Example 1: Age Calculator

```java
public int calculateAge(LocalDate birthDate) {
    LocalDate today = LocalDate.now();
    return Period.between(birthDate, today).getYears();
}

// Usage
LocalDate birthDate = LocalDate.of(1990, 5, 15);
int age = calculateAge(birthDate);
System.out.println("Age: " + age);
```

### Example 2: Business Days Calculator

```java
public int businessDays(LocalDate start, LocalDate end) {
    int days = 0;
    LocalDate current = start;
    
    while (!current.isAfter(end)) {
        DayOfWeek day = current.getDayOfWeek();
        if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
            days++;
        }
        current = current.plusDays(1);
    }
    
    return days;
}
```

### Example 3: Time Until Event

```java
public String timeUntilEvent(LocalDateTime eventTime) {
    Duration duration = Duration.between(LocalDateTime.now(), eventTime);
    
    long days = duration.toDays();
    long hours = duration.toHours() % 24;
    long minutes = duration.toMinutes() % 60;
    
    return String.format("%d days, %d hours, %d minutes", days, hours, minutes);
}
```

## Exercises

### Exercise 1: Date Operations
Create a program that:
- Gets current date
- Adds 30 days
- Finds day of week
- Checks if it's a weekend

### Exercise 2: Time Calculator
Calculate:
- Duration between two times
- Add hours and minutes to a time
- Check if time is within business hours (9-17)

### Exercise 3: Birthday Tracker
Create a program that:
- Takes a birthdate
- Calculates age
- Finds next birthday
- Calculates days until birthday

### Exercise 4: Date Formatter
Format a date in multiple ways:
- dd/MM/yyyy
- MMMM dd, yyyy
- EEEE, dd MMM yyyy

### Exercise 5: Timezone Converter
Convert current time to different timezones:
- New York
- London
- Tokyo
- Sydney

## Summary

Today you learned:
- ✅ Modern Date and Time API
- ✅ LocalDate, LocalTime, LocalDateTime
- ✅ ZonedDateTime for timezones
- ✅ Period and Duration
- ✅ Formatting and parsing dates
- ✅ Practical date/time calculations

Tomorrow, we'll explore **Method References**!

---

**Next**: [Day 21 - Method References](../Day21/README.md)

**Previous**: [Day 19 - Optional Class](../Day19/README.md)

