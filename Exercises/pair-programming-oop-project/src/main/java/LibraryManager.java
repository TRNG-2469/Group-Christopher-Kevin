public class LibraryManager {
    public static void main(String[] args) {
        EBook book1 = new EBook("Wuthering Heights", "Emily Bronte", 10.1);
        EBook book2 = new EBook("Emily Bronte", "Wuthering Heights", 1.01);
        EBook book3 = new EBook("Green Eggs and Ham", "Dr. Seuss", 1000.1);
        EBook book4 = new EBook("The Alphabet", "Someone", 1.1);
        EBook book5 = new EBook("Wuthering Heights 2", "Emily Bronte", 20.1);

        Book[] bookList = {book1, book2, book3, book4, book5};

        for(int i = 0; i < bookList.length; i++){
            Book book = bookList[i];
            if(book instanceof EBook) {
                ((EBook)book).borrowItem();
            }
        }

        for(int i = 0; i < bookList.length; i++){
            Book book = bookList[i];
            if(book instanceof EBook) {
                ((EBook)book).returnItem();
            }
        }
    }
}