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

    public static Event fromString(String cmd) throws IllegalEventException {
        String[] parts = cmd.split("/from");
        if (parts.length != 2) {
            throw new IllegalEventException("Format invalid, do <body> /from <dateStart> /to <dateEnd>");
        }
        String body = parts[0].trim();
        String[] dateParts = parts[1].split("/to");
        if (dateParts.length != 2) {
            throw new IllegalEventException("Format invalid, do <body> /from <dateStart> /to <dateEnd>");
        }
        String dateStart = dateParts[0].trim();
        String dateEnd = dateParts[1].trim();
        return new Event(body, dateStart, dateEnd);
    }

    @Override 
    public char getStatusIcon() {
        return 'E';
    }
}
