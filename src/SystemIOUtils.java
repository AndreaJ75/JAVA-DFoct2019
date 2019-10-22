import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

class SystemIOUtils {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    static Integer promptToInt(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            printError("Votre choix doit être numérique !");
        }

        return 0;
    }

    static String promptToString(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static void printError(String msg) {
        System.out.println();
        System.out.println(ANSI_RED + msg + ANSI_RESET);
        System.out.println();
    }

    static void printList(String title, Collection<String> list) {

        if (list.isEmpty()) {
            printError("La liste est vide");
        } else {
            System.out.println("------- " + title + " -------");
            System.out.println();
            int i = 1;
            for (String item : list) {
                System.out.println(i + ". " + item);
                i++;
            }
            System.out.println();
            System.out.println();
        }
    }

    static void printCountLines(long lines) {
        System.out.println();
        System.out.println(ANSI_YELLOW + "Nombre de lignes : " + lines + ANSI_RESET);
        System.out.println();
    }

    static void printWords(Map words) {
        System.out.println();
        System.out.println(ANSI_YELLOW + "-------- nombre de mots différents du fichier --------" + ANSI_RESET);
        words.forEach((k, v) -> System.out.println(ANSI_YELLOW + k + " : " + v + ANSI_RESET));
        System.out.println();
    }

    static void printTitle(String title) {
        System.out.println();
        System.out.println("----- " + title + " -----");
        System.out.println();
    }

    static void printMessage(String msg) {
        System.out.println();
        System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
        System.out.println();
    }
}
