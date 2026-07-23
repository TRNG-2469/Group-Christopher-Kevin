## Round 1: Initial Code
```java
public class Book {
private String title;
private String author;
protected boolean isBorrowed;

    public Book(){}

    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "[BOOK] Title: " + this.title + " | Author: " + this.author;
    }
}
```
### AI Prompt
- Is this class fundamentally okay?

### AI Response
- Yes — it's fundamentally fine and will work. A few small improvements to consider:
- Make isBorrowed private (encapsulation).
- Remove the empty default constructor if unused.
- Use @Override (import) instead of fully-qualified annotation/type.
- Validate constructor args (Objects.requireNonNull) if nulls are invalid.
- Consider implementing equals/hashCode if Books should be value-equal by title/author.
  
### Analysis
- We ignored making isBorrowed private as making it protected is part of the specifications
- We kept the default constructor.
- The specifications did not mention a need to be comparing books between each other, so an equals method was unnecessary.

### Round 2: Initial Code
```java
public class Ebook extends Book implements Borrowable{
    private double fileSizeMB;

    public Ebook() {}

    public Ebook(String title, String author, double fileSizeMB) {
        super(title, author);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public java.lang.String toString() {
        return super.toString() + " | File Size: " + this.fileSizeMB + " MB";
    }

    @Override
    public void borrowItem() {
        this.isBorrowed = true;
    }

    @Override
    public void returnItem() {
        this.isBorrowed = false;
    }
}
```
### AI Prompt
- Write a method downloadBook that simulates a 2-second delay using Thread.sleep. Constrain the input size to be positive. Use standard try-catch blocks for interruption.

### AI Response
```java
public void downloadBook(double sizeMB) {
    if (sizeMB <= 0) {
        throw new IllegalArgumentException("sizeMB must be positive");
    }
    System.out.println("Starting download of " + sizeMB + " MB...");
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println("Download interrupted");
        return;
    }
    System.out.println("Download complete");
}
```

### Analysis
- The method downloadBook is implemented correctly with a positive size constraint and a 2-second delay.