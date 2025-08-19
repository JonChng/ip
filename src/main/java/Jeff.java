import java.util.Scanner;

public class Jeff {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am Jeff! Your own personal chatbot.\n");
        System.out.println(
            "What can I do for you?"
        );

        Task[] tasks = new Task[100];
        int taskCount = 0;
        
        
            while (sc.hasNextLine()) {
                try {
                    String input = sc.nextLine().trim();
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

                    } else if (split.length < 2) {

                        throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");

                    } 
                        else if (cmd.equalsIgnoreCase("mark")) {

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
                    } else if (cmd.equalsIgnoreCase("delete")) {

                        int idx = (Integer.parseInt(split[1]) - 1); // This would be the index to be deleted.

                        if (idx < 0 || idx >= taskCount) {
                            throw new JeffException("Invalid task number. Please try again.");
                        }

                        for (int i = idx; i < taskCount - 1; i++) {
                            tasks[i] = tasks[i + 1];
                        }

                        tasks[taskCount - 1] = null; 
                        taskCount--;
                        System.out.println("Task has been deleted.");
                        System.out.println("You now have " + taskCount + " tasks in the list.");

                        
                    }
                    else {
                        if (taskCount < tasks.length) {

                            switch (cmd.toLowerCase()) {
                                case "todo":
                                    tasks[taskCount] = new Todo(split[1]);
                                    break;
                                case "deadline":
                                    String[] deadlineParts = split[1].split("/by", 2);
                                    tasks[taskCount] = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                                    break;
                                case "event":
                                    String[] eventParts = split[1].split("/at", 2);
                                    tasks[taskCount] = new Event(eventParts[0].trim(), eventParts[1].trim());
                                    break;
                            
                            }

                            
                            taskCount++;
                            System.out.println("______________________________");
                            System.out.println("Task has been added: " + input);
                            System.out.println("You now have " + taskCount + " tasks in the list.");
                            System.out.println("______________________________");
                        } else {
                            System.out.println("Task list is full!");
                        }
                    }
                    

                }
            catch (JeffException e) {
                System.out.println(e.getMessage());
            } 
        } 
        System.out.println("Bye! Hope to you see you again soon!");
        sc.close();

    }

}
