import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Saves and loads the current task list to disk.
 *
 * <p>Each task is stored on one line using the task type, completion status,
 * and display text.</p>
 */
public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "Extant.txt");

    private Storage() {
        // Utility class; do not instantiate.
    }

    /**
     * Writes all current tasks to the data file, creating its parent directory
     * if necessary.
     *
     * @param tasks the task array
     * @param taskCount number of valid tasks in the array
     */
    public static void save(Task[] tasks, int taskCount) {
        StringBuilder contents = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];
            contents.append(task.getStatusIcon())
                    .append(" | ")
                    .append(task.getDone() ? 1 : 0)
                    .append(" | ")
                    .append(task)
                    .append(System.lineSeparator());
        }

        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.writeString(FILE_PATH, contents.toString());
        } catch (IOException e) {
            System.out.println("Unable to save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from disk. Invalid lines are ignored so one bad record does
     * not prevent the chatbot from starting.
     *
     * @param tasks array to populate
     * @param maximumTasks maximum number of tasks to load
     * @return number of tasks loaded
     */
    public static int load(Task[] tasks, int maximumTasks) {
        if (!Files.exists(FILE_PATH)) {
            return 0;
        }

        int loaded = 0;
        try {
            for (String line : Files.readAllLines(FILE_PATH)) {
                if (loaded == maximumTasks) {
                    System.out.println("Warning: task limit reached; remaining tasks were skipped.");
                    break;
                }
                Task task = parseLine(line);
                if (task != null) {
                    tasks[loaded++] = task;
                } else if (!line.trim().isEmpty()) {
                    System.out.println("Warning: skipped malformed task: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to load tasks: " + e.getMessage());
        }
        return loaded;
    }

    private static Task parseLine(String line) {
        String[] parts = line.split("\\s*\\|\\s*", 3);
        if (parts.length != 3 || !parts[1].equals("0") && !parts[1].equals("1")) {
            return null;
        }

        Task task;
        String text = parts[2].trim();
        try {
            switch (parts[0].trim()) {
            case "T":
                task = new Todo(text);
                break;
            case "D":
                int deadlineMarker = text.indexOf(" (by: ");
                if (!text.endsWith(")") || deadlineMarker < 0) return null;
                task = new Deadline(text.substring(0, deadlineMarker),
                        text.substring(deadlineMarker + 6, text.length() - 1));
                break;
            case "E":
                int eventMarker = text.indexOf(" (from: ");
                int eventSeparator = text.indexOf(" to: ", eventMarker);
                if (!text.endsWith(")") || eventMarker < 0 || eventSeparator < 0) return null;
                task = new Event(text.substring(0, eventMarker),
                        text.substring(eventMarker + 8, eventSeparator),
                        text.substring(eventSeparator + 5, text.length() - 1));
                break;
            default:
                return null;
            }
        } catch (RuntimeException e) {
            return null;
        }
        task.setDone(parts[1].equals("1"));
        return task;
    }
}
