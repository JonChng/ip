package jeff.ui;

import java.util.ArrayList;

import jeff.command.Command;
import jeff.command.Parser;
import jeff.storage.JeffException;
import jeff.storage.Storage;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.task.Todo;

/**
 * Main application for Jeff the automated chatbot.
 */
public class Jeff {

    private static final String SPACER = "______________________________";

    /**
     * Entry point for the Jeff chatbot application. Initializes and runs the
     * system.
     *
     * @param args command line arguments (not used)
     * @throws JeffException if an error occurs during execution
     */
    public static void main(String[] args) throws JeffException {

        UserInterface ui = new UserInterface();
        ui.welcome();

        Storage storage = new Storage();
        TaskList tasks = new TaskList();

        System.out.println("Loaded " + tasks.size() + " task(s) from storage.");

        boolean shouldBreak = false;

        while (!shouldBreak) {
            try {
                String input = ui.readCommand();

                Parser.Result result = Parser.parseCommand(input);
                Command cmd = result.command;
                String description = result.description; // this is the args

                if (cmd == Command.BYE) {
                    shouldBreak = true;
                }

                if (cmd == null) {
                    throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");
                }

                shouldBreak = handleCommand(cmd, description, tasks, input);
                updateStorage(tasks, storage);

            } catch (JeffException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye! Hope to you see you again soon!");

    }

    private static boolean handleCommand(Command cmd, String description, TaskList tasks, String input) throws JeffException {
        switch (cmd) {
            case LIST:
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) != null) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                break;
            case BYE:
                return true;

            case MARK:

                int markIdx = Integer.parseInt(description);
                tasks.get(markIdx - 1).markAsDone();
                System.out.println("Task marked as done!");
                System.out.println(tasks.get(markIdx - 1));
                System.out.println(SPACER);
                break;

            case UNMARK:

                int unmarkIdx = Integer.parseInt(description);
                tasks.get(unmarkIdx - 1).undo();
                System.out.println("Task marked as undone!");
                System.out.println(tasks.get(unmarkIdx - 1));
                break;

            case DELETE:

                int idx = (Integer.parseInt(description) - 1); // This would be the index to be deleted.

                if (idx < 0 || idx >= tasks.size()) {
                    throw new JeffException("Invalid task number. Please try again.");
                }
                tasks.remove(idx);
                System.out.println("Task has been deleted.");
                System.out.println("You now have " + tasks.size() + " tasks in the list.");
                break;

            case TODO:
                tasks.add(new Todo(description));
                added(input, tasks);
                break;

            case DEADLINE:
                String[] parts;
                if (description.contains("/by")) {
                    parts = description.split("/by", 2);
                } else {
                    parts = description.split(" ", 2);
                }
                tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                added(input, tasks);
                break;

            case EVENT:

                String[] parts2;
                if (description.contains("/at")) {
                    parts = description.split("/at", 2);
                } else {
                    parts = description.split(" ", 2);
                }
                tasks.add(new Event(parts[0].trim(), parts[1].trim()));
                added(input, tasks);
                break;

            case FIND:
                String keyword = description;
                findTasks(tasks, keyword);
                break;

        }
        return false;
    }

    private static void findTasks(TaskList tasks, String keyword) {

        System.out.println(SPACER);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {

                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(SPACER);

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

    private static Task parseTask(String line) throws JeffException {
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
                try {
                    task = new Deadline(description, parts[3]);
                } catch (JeffException e) {
                    throw new JeffException(e.getMessage());
                }
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
        System.out.println(SPACER);
        System.out.println("Task has been added: " + input);
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
        System.out.println(SPACER);

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
