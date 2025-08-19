public class Jeff {
    public static void main(String[] args) {
        System.out.println("Hello! I am Jeff! Your own personal chatbot.\n");
        System.out.println(
            "What can I do for you?"
        );

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = System.console().readLine();
            String[] split = input.split("\\s+", 2);
            String cmd = split[0];

            if (cmd.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else if (cmd.equalsIgnoreCase("bye")) {
                break;
            } else if (cmd.equalsIgnoreCase("mark")) {

                int length = Integer.parseInt(split[1]);
                tasks[length - 1].markAsDone();
                System.out.println("Task marked as done!");
                System.out.println(tasks[length - 1]);
                System.out.println("______________________________");

            } else if (cmd.equalsIgnoreCase("unmark")) {
                int length = Integer.parseInt(split[1]);
                tasks[length - 1].undo();
                System.out.println("Task marked as undone!");
                System.out.println(tasks[length - 1]);
            } 
            else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(input);
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
