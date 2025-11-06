# Day 27: Java 11 - HTTP Client API

Welcome to Day 27! Today we'll explore the HTTP Client API that was standardized in Java 11.

## Table of Contents
- [Introduction](#introduction)
- [Creating HTTP Client](#creating-http-client)
- [Sending Requests](#sending-requests)
- [Handling Responses](#handling-responses)
- [Asynchronous Requests](#asynchronous-requests)
- [Advanced Features](#advanced-features)
- [Exercises](#exercises)

## Introduction

The HTTP Client API provides a modern, easy-to-use way to send HTTP requests and receive responses.

### Key Features:
- Supports HTTP/1.1 and HTTP/2
- Synchronous and asynchronous requests
- WebSocket support
- Stream-based request/response
- Better performance than HttpURLConnection

### Main Classes:
- `HttpClient` - Client to send requests
- `HttpRequest` - HTTP request
- `HttpResponse` - HTTP response
- `HttpHeaders` - HTTP headers

## Creating HTTP Client

```java
import java.net.http.HttpClient;
import java.time.Duration;

// Simple client
HttpClient client = HttpClient.newHttpClient();

// Custom client with builder
HttpClient customClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .followRedirects(HttpClient.Redirect.NORMAL)
    .build();
```

### Client Configuration Options:

```java
HttpClient client = HttpClient.newBuilder()
    // HTTP version
    .version(HttpClient.Version.HTTP_1_1)  // or HTTP_2
    
    // Connection timeout
    .connectTimeout(Duration.ofSeconds(20))
    
    // Redirect policy
    .followRedirects(HttpClient.Redirect.ALWAYS)  // NEVER, NORMAL, ALWAYS
    
    // Executor for async requests
    .executor(Executors.newFixedThreadPool(2))
    
    // Priority (HTTP/2)
    .priority(1)
    
    .build();
```

## Sending Requests

### GET Request

```java
import java.net.URI;
import java.net.http.*;

HttpClient client = HttpClient.newHttpClient();

// Create request
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .GET()
    .build();

// Send request (synchronous)
HttpResponse<String> response = client.send(
    request,
    HttpResponse.BodyHandlers.ofString()
);

// Get response
System.out.println("Status Code: " + response.statusCode());
System.out.println("Body: " + response.body());
```

### POST Request

```java
// JSON body
String json = "{\"name\":\"John\",\"email\":\"john@example.com\"}";

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(json))
    .build();

HttpResponse<String> response = client.send(
    request,
    HttpResponse.BodyHandlers.ofString()
);
```

### PUT Request

```java
String json = "{\"name\":\"John Updated\"}";

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users/1"))
    .header("Content-Type", "application/json")
    .PUT(HttpRequest.BodyPublishers.ofString(json))
    .build();
```

### DELETE Request

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users/1"))
    .DELETE()
    .build();
```

### Request with Headers

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .header("Authorization", "Bearer token123")
    .header("Accept", "application/json")
    .header("User-Agent", "MyApp/1.0")
    .GET()
    .build();
```

### Request with Timeout

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .timeout(Duration.ofSeconds(5))
    .GET()
    .build();
```

## Handling Responses

### Response Body Handlers

```java
// String response
HttpResponse<String> response1 = client.send(
    request,
    HttpResponse.BodyHandlers.ofString()
);

// Byte array response
HttpResponse<byte[]> response2 = client.send(
    request,
    HttpResponse.BodyHandlers.ofByteArray()
);

// Save to file
HttpResponse<Path> response3 = client.send(
    request,
    HttpResponse.BodyHandlers.ofFile(Path.of("output.txt"))
);

// Stream response
HttpResponse<InputStream> response4 = client.send(
    request,
    HttpResponse.BodyHandlers.ofInputStream()
);

// Discard response body
HttpResponse<Void> response5 = client.send(
    request,
    HttpResponse.BodyHandlers.discarding()
);
```

### Accessing Response Data

```java
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

// Status code
int statusCode = response.statusCode();

// Headers
HttpHeaders headers = response.headers();
headers.map().forEach((key, values) -> {
    System.out.println(key + ": " + values);
});

// Specific header
Optional<String> contentType = headers.firstValue("content-type");

// Body
String body = response.body();

// URI
URI uri = response.uri();

// HTTP version
HttpClient.Version version = response.version();
```

## Asynchronous Requests

### Send Async

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .build();

// Asynchronous request
CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(
    request,
    HttpResponse.BodyHandlers.ofString()
);

// Process when complete
futureResponse.thenAccept(response -> {
    System.out.println("Status: " + response.statusCode());
    System.out.println("Body: " + response.body());
});

// Or wait for result
HttpResponse<String> response = futureResponse.join();
```

### Multiple Async Requests

```java
List<String> urls = Arrays.asList(
    "https://api.example.com/users/1",
    "https://api.example.com/users/2",
    "https://api.example.com/users/3"
);

List<CompletableFuture<HttpResponse<String>>> futures = urls.stream()
    .map(url -> HttpRequest.newBuilder().uri(URI.create(url)).build())
    .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
    .collect(Collectors.toList());

// Wait for all to complete
CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
    .thenAccept(v -> {
        futures.forEach(future -> {
            HttpResponse<String> response = future.join();
            System.out.println(response.body());
        });
    });
```

## Advanced Features

### Authenticator

```java
HttpClient client = HttpClient.newBuilder()
    .authenticator(new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                "username",
                "password".toCharArray()
            );
        }
    })
    .build();
```

### Cookie Handler

```java
CookieManager cookieManager = new CookieManager();
cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

HttpClient client = HttpClient.newBuilder()
    .cookieHandler(cookieManager)
    .build();
```

### Proxy

```java
HttpClient client = HttpClient.newBuilder()
    .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 8080)))
    .build();
```

## Practical Examples

### Example 1: REST API Client

```java
public class UserApiClient {
    private final HttpClient client;
    private final String baseUrl;
    
    public UserApiClient(String baseUrl) {
        this.client = HttpClient.newHttpClient();
        this.baseUrl = baseUrl;
    }
    
    public User getUser(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl + "/users/" + id))
            .header("Accept", "application/json")
            .build();
        
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
        
        if (response.statusCode() == 200) {
            // Parse JSON to User object
            return parseUser(response.body());
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }
    }
    
    public void createUser(User user) throws Exception {
        String json = userToJson(user);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
        
        System.out.println("Created: " + response.statusCode());
    }
}
```

### Example 2: File Download

```java
public void downloadFile(String url, Path destination) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
    
    HttpResponse<Path> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofFile(destination)
    );
    
    System.out.println("Downloaded to: " + response.body());
}
```

### Example 3: Retry Logic

```java
public HttpResponse<String> sendWithRetry(HttpRequest request, int maxRetries) {
    int attempts = 0;
    
    while (attempts < maxRetries) {
        try {
            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
            
            if (response.statusCode() == 200) {
                return response;
            }
            
            attempts++;
            Thread.sleep(1000 * attempts); // Exponential backoff
            
        } catch (Exception e) {
            attempts++;
            if (attempts >= maxRetries) {
                throw new RuntimeException("Max retries exceeded", e);
            }
        }
    }
    
    throw new RuntimeException("Request failed");
}
```

## Exercises

### Exercise 1: Simple GET Request
Create a program that:
- Sends GET request to a public API
- Prints status code and response body
- Handles errors

### Exercise 2: POST Data
Create a REST client that:
- Posts JSON data
- Reads response
- Extracts specific fields

### Exercise 3: Async Multiple Requests
Send multiple requests asynchronously:
- Create list of URLs
- Send all requests concurrently
- Collect and display all responses

### Exercise 4: File Downloader
Create a file downloader that:
- Downloads file from URL
- Shows progress (if possible)
- Saves to specified location

### Exercise 5: API Wrapper
Create a complete API wrapper class with:
- GET, POST, PUT, DELETE methods
- Authentication support
- Error handling
- Retry logic

## Summary

Today you learned:
- ✅ HTTP Client API basics
- ✅ Creating and configuring clients
- ✅ Sending synchronous requests
- ✅ Handling responses
- ✅ Asynchronous requests
- ✅ Advanced features (auth, cookies, proxy)
- ✅ Building REST API clients

Tomorrow, we'll explore **More Java 11 Features**!

---

**Next**: [Day 28 - Java 11 Additional Features](../Day28-Java11-More/README.md)

**Previous**: [Day 26 - Java 11 New Features](../Day26-Java11-Features/README.md)

