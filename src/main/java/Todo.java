public class Todo extends Task {
    /**
     * Creates a new todo task with the given description.
     *
     * @param body Description of the todo task.
     */
    public Todo(String body) {
        super(body);
    }
    
    /**
     * Returns the status icon of this task.
     *
     * @return 'T' for todo, 'D' for deadline, 'E' for event.
     */
    @Override
    public char getStatusIcon() {
        return 'T';
    }
}
