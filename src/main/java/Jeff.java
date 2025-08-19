import java.util.Scanner;
import java.util.ArrayList;

enum Command {
    LIST, BYE, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
}

public class Jeff {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am Jeff! Your own personal chatbot.\n");
        System.out.println(
            "What can I do for you?"
        );

        ArrayList<Task> tasks = new ArrayList<>();
        boolean shouldBreak = false;
        
            while (!shouldBreak && sc.hasNextLine()) {
                try {
                    String input = sc.nextLine().trim();
                    String[] split = input.split("\\s+", 2);
                    Command cmd = Command.fromString(split[0]);


                    
                    if (cmd == null) {
                        throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");
                    }

                    switch (cmd) {
                        case LIST:
                            for (int i = 0; i < tasks.size(); i++) {
                                if (tasks.get(i) != null) {
                                    System.out.println((i + 1) + ". " + tasks.get(i));
                                }
                            }
                            break;
                        case BYE:
                            
                            shouldBreak = true;
                            break;

                        case MARK:

                            int markIdx = Integer.parseInt(split[1]);
                            tasks.get(markIdx - 1).markAsDone();
                            System.out.println("Task marked as done!");
                            System.out.println(tasks.get(markIdx - 1));
                            System.out.println("______________________________");
                            break;
                        
                        case UNMARK:

                            int unmarkIdx = Integer.parseInt(split[1]);
                            tasks.get(unmarkIdx - 1).undo();
                            System.out.println("Task marked as undone!");
                            System.out.println(tasks.get(unmarkIdx - 1));
                            break;

                        case DELETE:

                            int idx = (Integer.parseInt(split[1]) - 1); // This would be the index to be deleted.

                            if (idx < 0 || idx >= tasks.size()) {
                                throw new JeffException("Invalid task number. Please try again.");
                            }
                            tasks.remove(idx);
                            System.out.println("Task has been deleted.");
                            System.out.println("You now have " + tasks.size() + " tasks in the list.");
                            break;
                        
                        case TODO:
                            tasks.add(new Todo(split[1]));
                            added(input, tasks);
                            break;

                        case DEADLINE:
                            String[] deadlineParts = split[1].split("/by", 2);
                            tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                            added(input, tasks);
                            break;

                        case EVENT:
                            String[] eventParts = split[1].split("/at", 2);
                            tasks.add(new Event(eventParts[0].trim(), eventParts[1].trim()));
                            added(input, tasks);
                            break;
                        
                    }
                    

                    } 
                
                

            catch (JeffException e) {
                System.out.println(e.getMessage());
            } 
        } 
        System.out.println("Bye! Hope to you see you again soon!");
        sc.close();

    }

    private static void added(String input, ArrayList<Task> tasks) {
        System.out.println("______________________________");
        System.out.println("Task has been added: " + input);
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
        System.out.println("______________________________");
    }


}
