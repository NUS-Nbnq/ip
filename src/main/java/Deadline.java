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
    

    public static Deadline fromString(String cmd) throws IllegalEventException {
        String[] parts = cmd.split("/by");
        if (parts.length != 2) {
            throw new IllegalEventException("Format invalid, do <body> /by <dateEnd>");
        }
        String body = parts[0].trim();
        String dateEnd = parts[1].trim();
        return new Deadline(body, dateEnd);
    }

    @Override 
    public char getStatusIcon() {
        return 'D';
    }
}
git 