/**
 * Entry point for the Extant chatbot application.
 * Handles user commands for adding, listing, and marking tasks.
 */
public class Extant {
    private static final int MAX_TASKS = 100;
    static TaskList tasks = new TaskList();
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final Storage storage = new Storage("data/Extant.txt");

    static void addTask(Task task)
    {
        tasks.add(task);
        storage.save(tasks.asList(), tasks.size());

        ui.showMessage("added: " + tasks.get(tasks.size() - 1));
    }

    /**
     * Runs the main command loop for the Extant chatbot.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        storage.load(tasks.asList(), MAX_TASKS);
        String banner =
        """
███████╗██╗  ██╗████████╗ █████╗ ███╗   ██╗████████╗
██╔════╝╚██╗██╔╝╚══██╔══╝██╔══██╗████╗  ██║╚══██╔══╝
█████╗   ╚███╔╝    ██║   ███████║██╔██╗ ██║   ██║   
██╔══╝   ██╔██╗    ██║   ██╔══██║██║╚██╗██║   ██║   
███████╗██╔╝ ██╗   ██║   ██║  ██║██║ ╚████║   ██║   
╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   """;
        System.out.println(banner);
        ui.showWelcome();

        outerLoop:
        while (true) {
            try
            {
            String input = ui.readCommand();
            String keyword = parser.getKeyword(input);
            switch (keyword)
                {   
                case "bye":
                    ui.showMessage("Farewell. Until our paths cross again.");
                    break outerLoop; // idk what this does but it works?
                case "delete":
                    int indexDelete = parser.parseIndex(input, keyword);
                    doDelete(indexDelete);
                    break;
                case "list":
                    doList();
                    break;
                case "mark":
                    int indexMark = parser.parseIndex(input, keyword);
                    doMark(indexMark);
                    break;
                case "unmark":
                    int indexUnmark = parser.parseIndex(input, keyword);
                    doUnMark(indexUnmark);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(parser.parseTask(keyword, parser.getArgument(input, keyword)));
                    break;
                default:
                    throw new IllegalKeywordException();
                }                
            } catch (IllegalEventException e) {
                ui.showMessage(e.getMessage());
            } catch (IllegalKeywordException e) {
                ui.showMessage("Command Unrecognized. Please use 'todo', 'deadline', or 'event' to add tasks.");
            } catch (NumberFormatException e) {
                ui.showMessage("Invalid index format. Please provide a valid number.");
            }
        }
        ui.close();
    }

    private static void doUnMark(int index) {                
        tasks.unmark(index);
        storage.save(tasks.asList(), tasks.size());
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
    }

    private static void doMark(int index) {
        tasks.mark(index);
        storage.save(tasks.asList(), tasks.size());
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
    }

    private static void doList() {
        ui.showTaskList(tasks);
    }

    private static void doDelete(int index)
    {
        ui.showMessage("Deleting " + ( index + 1 ) + ": \n  " + tasks.get(index));
        tasks.remove(index);
        storage.save(tasks.asList(), tasks.size());
    }
}
