public class LibraryManager {
    public static void main(String[] args) {
        Ebook book1 = new Ebook("Wuthering Heights", "Emily Bronte", 10.1);
        Ebook book2 = new Ebook("Emily Bronte", "Wuthering Heights", 1.01);
        Ebook book3 = new Ebook("Green Eggs and Ham", "Dr. Seuss", 1000.1);
        Ebook book4 = new Ebook("The Alphabet", "Someone", 1.1);
        Ebook book5 = new Ebook("Wuthering Heights 2", "Emily Bronte", 20.1);

        Book[] bookList = {book1, book2, book3, book4, book5};

        for(int i = 0; i < bookList.length; i++){
            bookList[i].borrowItem();
        }

        for(int i = 0; i < bookList.length; i++){
            bookList[i].returnItem();
        }
    }
}