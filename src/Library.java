import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Library {
    private List<Book> books;
    private int choiceMenu = 0;
    static final String BOOKS_FOLDER = System.getProperty("user.dir") + "/books/";
    private static final List<String> MENU = Arrays.asList("Lister les fichiers", "Ajouter un fichier", "Supprimer un fichier", "Afficher des informations sur un livre", "Quitter le programme");
    private static final List<String> SUB_MENU = Arrays.asList("Afficher le nombre de lignes du fichier", "Afficher le nombre de mots différents du fichier");

    Library(List<Book> books) {
        this.books = books;
    }

    void startMenu() {

        while(choiceMenu != 5) {
            SystemIOUtils.printList("MENU", MENU);

            choiceMenu = SystemIOUtils.promptToInt("Votre choix : ?");

            switch (choiceMenu) {
                case 1:
                    listBooks();
                    startMenu();
                    break;
                case 2:
                    addFile();
                    startMenu();
                    break;
                case 3:
                    deleteFile();
                    startMenu();
                    break;
                case 4:
                    startSubMenu();
                    startMenu();
                    break;
                case 5:
                    SystemIOUtils.printMessage("Goodbye !");
                    // End of program
                    break;
                case 0:
                    // In case of error
                    break;
                default:
                    SystemIOUtils.printError("Votre choix n'est pas reconnu !");
            }
        }

    }

    private void startSubMenu() {
        listBooks();

        int fileChoice = SystemIOUtils.promptToInt("Choisissez un fichier : ?");

        if (fileChoice != 0) {
            try {
                Book book = books.get(fileChoice - 1);
                SystemIOUtils.printMessage("Fichier selectionné : " + book.getName());

                SystemIOUtils.printList("SOUS MENU", SUB_MENU);

                int menuChoice = SystemIOUtils.promptToInt("Votre choix : ?");

                if (menuChoice == 1) {
                    SystemIOUtils.printCountLines(book.countLines());
                } else if (menuChoice == 2) {
                    SystemIOUtils.printWords(book.countWords());
                } else {
                    startSubMenu();
                }

            } catch (IndexOutOfBoundsException e) {
                SystemIOUtils.printError("Votre choix n'est pas correct");
            } catch (IOException e) {

            }
        }
    }

    private void listBooks() {
        SystemIOUtils.printList("Liste des livres", books.stream().map(Book::getName).collect(Collectors.toList()));
    }

    private void addFile() {
        SystemIOUtils.printTitle("Ajouter un fichier");
        String name = SystemIOUtils.promptToString("Nom du fichier : ?");

        if (!isNotAlreadyPresent(name)) {
            SystemIOUtils.printError("Le fichier existe déjà dans la bibliothèque");
            return;
        }

        if (bookExist(name)) {
            books.add(new Book(name));
            SystemIOUtils.printMessage("Le fichier " + name + " a été ajouté");
        }
    }

    private boolean isNotAlreadyPresent(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    private void deleteFile() {
        SystemIOUtils.printTitle("Supprimer un fichier");

        listBooks();

        int fileChoice = SystemIOUtils.promptToInt("Choisissez un fichier : ?");

        try {
            books.remove(fileChoice - 1);
            SystemIOUtils.printMessage("Le fichier a été supprimé");
        } catch (IndexOutOfBoundsException e) {
            SystemIOUtils.printError("Le choix n'est pas correct");
        }

    }

    static boolean bookExist(String name) {
        File file = new File(BOOKS_FOLDER + name);

        if (!file.exists() || name.equals("") || name.equals(".") || name.equals("..")) {
            SystemIOUtils.printError("Le fichier " + name + " n'existe pas");
            return false;
        }

        return true;
    }

}
