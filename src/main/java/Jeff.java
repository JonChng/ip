import java.util.ArrayList;
import java.util.Scanner;

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

        UserInterface ui = new UserInterface();
        ui.welcome();

        Storage storage = new Storage();
        TaskList tasks = new TaskList();

        System.out.println("Loaded " + tasks.size() + " task(s) from storage.");


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
                            String[] parts;
                            if (split[1].contains("/by")) {
                                parts = split[1].split("/by", 2);
                            } else {
                                parts = split[1].split(" ", 2);
                            }
                            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                            added(input, tasks);
                            break;

                        case EVENT:

                            String[] parts2;
                            if (split[1].contains("/at")) {
                                parts = split[1].split("/at", 2);
                            } else {
                                parts = split[1].split(" ", 2);
                            }
                            tasks.add(new Event(parts[0].trim(), parts[1].trim()));
                            added(input, tasks);
                            break;
                        
                    }
                    updateStorage(tasks, storage);
                    

                    } 
                
                

            catch (JeffException e) {
                System.out.println(e.getMessage());
            } 
        } 
        System.out.println("Bye! Hope to you see you again soon!");
        sc.close();

    }

    private static String formatTask(Task t) {

        String done = t.isDone() ? "1" : "0";
        String type = t.getType();
        String description = t.getDescription();

        String formatted = String.format("%s|%s|%s", type, done, description);

        if (type == "D") {
            formatted += "|" + ((Deadline) t).getForStorage(); // this is ok beacuse type is deadline, safe typecast
        } else if (type == "E") {
            formatted += "|" + ((Event) t).getForStorage(); // this is ok beacuse type is event, safe typecast
        }

        return formatted;
    }

    private static Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
        case "T": 
            task = new Todo(description);
            break;
        case "D": 
            task = new Deadline(description, parts[3]);
            break;
        case "E": 
            task = new Event(description, parts[3]);
            break;
        default: 
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private static void added(String input, TaskList tasks) {
        System.out.println("______________________________");
        System.out.println("Task has been added: " + input);
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
        System.out.println("______________________________");
        
    }

    private static void updateStorage(TaskList tasks, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            lines.add(formatTask(task));
        }
        storage.save(lines);
    }


}
