package jeff.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() { 

        this.tasks = new ArrayList<>(); 

    }
    public TaskList(ArrayList<Task> existing) {

         this.tasks = existing; 
        
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }
}