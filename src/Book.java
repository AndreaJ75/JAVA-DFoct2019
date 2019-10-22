import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Book {
    private File file;

    Book(String name) {
        this.file = new File(Library.BOOKS_FOLDER + name);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    String getName() {
        return this.file.getName();
    }

    long countLines() throws IOException {
        try (Stream<String> stream = Files.lines(this.file.toPath())) {
            return stream.count();
        }
    }

    Map<String, Long> countWords() throws IOException {
        try (Stream<String> stream = Files.lines(this.file.toPath())) {
            return stream.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        }
    }
}
