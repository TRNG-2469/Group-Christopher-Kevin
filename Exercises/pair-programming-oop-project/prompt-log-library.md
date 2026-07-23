## Initial Code
```
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