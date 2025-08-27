package jeff.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? " [X] " : " [ ] "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void undo() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getStatusIcon();
    }
}
