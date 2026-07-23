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