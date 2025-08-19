
public class Jeff {
    public static void main(String[] args) {
        System.out.println("Hello! I am Jeff! Your own personal chatbot.\n");
        System.out.println(
            "What can I do for you?"
        );

        while (true) {
            String input = System.console().readLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("I can do " + input + " for you?\nSure I can!");

            System.out.println("________________________________________");
            System.out.println(
                "How are you feeling?\n" 
            );

            String feeling = System.console().readLine();
            if (feeling.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("I AM FEELING " + feeling + " TOO!\n");

            System.out.println("________________________________________");

        }
        System.out.println("Bye! Hope to you see you again soon!\n");

    }
}
