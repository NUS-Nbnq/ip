import java.util.ArrayList;
import java.util.Scanner;
/**
 * Entry point for the Extant chatbot application.
 * Handles user commands for adding, listing, and marking tasks.
 */
public class Extant {
    private static final int MAX_TASKS = 100;
    static ArrayList<Task> tasks = new ArrayList<>();
    static int taskCount = 0;

    static String prettify(Task task, int i)
    {
        return (i + 1) + ". [" + task.getStatusIcon() + "][" + (task.getDone() ? "X" : " ") + "] " + task;
    }

    static void addTask(Task task)
    {
        tasks.add(task);
        taskCount++;
        Storage.save(tasks, taskCount);

        printLine();
        System.out.println("added: " + tasks.get(taskCount - 1));
        printLine();
    }

    /**
     * Prints a horizontal divider line to the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Runs the main command loop for the Extant chatbot.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        taskCount = Storage.load(tasks, MAX_TASKS);
        String banner =
        """
███████╗██╗  ██╗████████╗ █████╗ ███╗   ██╗████████╗
██╔════╝╚██╗██╔╝╚══██╔══╝██╔══██╗████╗  ██║╚══██╔══╝
█████╗   ╚███╔╝    ██║   ███████║██╔██╗ ██║   ██║   
██╔══╝   ██╔██╗    ██║   ██╔══██║██║╚██╗██║   ██║   
███████╗██╔╝ ██╗   ██║   ██║  ██║██║ ╚████║   ██║   
╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   """;
        System.out.println(banner);
        printLine();
        System.out.println("Good day. State your intent.");
        printLine();

        Scanner scanner = new Scanner(System.in);

        outerLoop:
        while (true) {
            try
            {
            String input = scanner.nextLine();
            String keyword = input.split(" ")[0];
            switch (keyword)
                {   
                case "bye":
                    printLine();
                    System.out.println("Farewell. Until our paths cross again.");
                    printLine();
                    break outerLoop; // idk what this does but it works?
                case "delete":
                    int indexDelete = Integer.parseInt(input.substring("delete".length()).trim()) - 1;
                    doDelete(indexDelete);
                    break;
                case "list":
                    doList();
                    break;
                case "mark":
                    int indexMark = Integer.parseInt(input.substring("mark".length()).trim()) - 1;
                    doMark(indexMark);
                    break;
                case "unmark":
                    int indexUnmark = Integer.parseInt(input.substring("unmark".length()).trim()) - 1;
                    doUnMark(indexUnmark);
                    break;
                case "todo":
                    String cmdTodo = input.substring("todo".length()).trim();
                    Task taskTempTodo = Todo.fromString(cmdTodo);
                    addTask(taskTempTodo);
                    break;
                case "deadline":
                    String cmdDeadline = input.substring("deadline".length()).trim();
                    Task taskTempDeadline = Deadline.fromString(cmdDeadline);
                    addTask(taskTempDeadline);
                    break;
                case "event":
                    String cmdEvent = input.substring("event".length()).trim();
                    Task taskTempEvent = Event.fromString(cmdEvent);
                    addTask(taskTempEvent);
                    break;
                default:
                    throw new IllegalKeywordException();
                }                
            } catch (IllegalEventException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            } catch (IllegalKeywordException e) {
                printLine();
                System.out.println("Command Unrecognized. Please use 'todo', 'deadline', or 'event' to add tasks.");
                printLine();
            }
        }
        scanner.close();
    }

    private static void doUnMark(int index) {                
        tasks.get(index).setDone(false);
        Storage.save(tasks, taskCount);
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index));
        printLine();
    }

    private static void doMark(int index) {
        tasks.get(index).setDone(true);
        Storage.save(tasks, taskCount);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index)  );
        printLine();
    }

    private static void doList() {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(prettify(tasks.get(i), i));
        }
        System.out.println("You have " + tasks.size() + " tasks in total.");
        printLine();
    }

    private static void doDelete(int index)
    {
        printLine();
        System.out.println("Deleting " + ( index + 1 ) + ": ");
        System.out.println("  " + tasks.get(index));
        tasks.remove(index);
        taskCount--;
        Storage.save(tasks, taskCount);
        printLine();
    }

    private static void doDelete()
    {}

}
