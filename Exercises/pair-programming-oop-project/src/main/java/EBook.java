public class EBook extends Book implements Borrowable{
    private double fileSizeMB;

    public EBook() {}

    public EBook(String title, String author, double fileSizeMB) {
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
}
