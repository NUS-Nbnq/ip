public class Event extends Task {
    private String dateStart;
    private String dateEnd;

    /**
     * Creates a new event task with the given description and date.
     *
     * @param body Description of the event task.
     * @param dateStart Start date of the event task.
     * @param dateEnd End date of the event task.
     */
    public Event(String body, String dateStart, String dateEnd) {
        super(body);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + dateStart + " to: " + dateEnd + ")";
    }
    
    /**
     * Returns the status icon of this task.
     *
     * @return 'T' for todo, 'D' for deadline, 'E' for event.
     */
    @Override
    public char getStatusIcon() {
        return 'E';
    }
}
