
public class Jeff {
    public static void main(String[] args) {
        System.out.println("Hello! I am Jeff! Your own personal chatbot.\n");
        System.out.println(
            "What can I do for you?"
        );

        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String input = System.console().readLine();

            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("______________________________");
                    System.out.println("added: " + input);
                    System.out.println("______________________________");
                } else {
                    System.out.println("Task list is full!");
                }
            }

        }
        System.out.println("Bye! Hope to you see you again soon!\n");

    }
}
