package jeff.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks for the Jeff chatbot.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {

        this.tasks = new ArrayList<>();

    }

    /**
     * Constructor for a TaskList with existing tasks.
     *
     * @param existing the existing list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> existing) {

        this.tasks = existing;

    }

    /**
     * Add a task to the list.
     *
     * @param t the task to add
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Get a task at the specified index.
     *
     * @param i the index of the task to get
     * @return the task at the specified index
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Remove a task at the specified index.
     *
     * @param i the index of the task to remove
     * @return the removed task
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Get the current number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }
}
