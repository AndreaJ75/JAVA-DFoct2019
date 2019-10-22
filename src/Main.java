import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        for (String name : args) {
            if (Library.bookExist(name)) {
                books.add(new Book(name));
            }
        }

        Library library = new Library(books);
        library.startMenu();
    }
}
