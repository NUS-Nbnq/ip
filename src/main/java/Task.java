/**
 * Represents a task with a description and a done/not-done status.
 */
public class Task {
    protected String body;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task is not done by default.
     *
     * @param body Description of the task.
     */
    public Task(String body) {
        this.body = body;
        this.isDone = false;
    }

    /**
     * Sets the done status of this task.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + body;
    }
}
