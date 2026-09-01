public class Deadline extends Task {
    private String dateEnd;

    /**
     * Creates a new deadline task with the given description and date.
     *
     * @param body Description of the deadline task.
     * @param dateEnd End date of the deadline task.
     */
    public Deadline(String body, String dateEnd) {
        super(body);
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dateEnd + ")";
    }
    
        /**
     * Returns the status icon of this task.
     *
     * @return 'T' for todo, 'D' for deadline, 'E' for event.
     */
    @Override
    public char getStatusIcon() {
        return 'D';
    }
}
