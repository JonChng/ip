import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("Hello! I am Jeff! Your own personal chatbot.");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "";
    }

    public void printLine() {
        System.out.println("______________________________");
    }

    public void printError(String msg) {
        System.out.println("Error: " + msg);
    }

}