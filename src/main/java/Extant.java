import java.util.Scanner;

/**
 * Entry point for the Extant chatbot application.
 * Handles user commands for adding, listing, and marking tasks.
 */
public class Extant {

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
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Farewell. Until our paths cross again.");
                printLine();
                break;
            }

            if (input.equals("list")) {
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "][" + (tasks[i].getDone() ? "X" : " ") + "] " + tasks[i]);
                }
                System.out.println("You have " + taskCount + " tasks in total.");
                printLine();
                continue;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                tasks[index].setDone(true);
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
                printLine();
                continue;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                tasks[index].setDone(false);
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
                printLine();
                continue;
            }

            if (input.startsWith("todo")) {
                String body = input.substring(5).trim();
                tasks[taskCount] = new Todo(body);
                taskCount++;

                printLine();
                System.out.println("added: " + body);
                printLine();
                continue;
            }

            if (input.startsWith("deadline")) {
                String[] parts = input.substring(9).trim().split(" /by ");
                if (parts.length != 2) {
                    printLine();
                    System.out.println("Format invalid, do <body> /by <dateEnd>");
                    printLine();
                    continue;
                }
                String body = parts[0];
                String dateEnd = parts[1];
                tasks[taskCount] = new Deadline(body, dateEnd);
                taskCount++;

                printLine();
                System.out.println("added: " + body + " (by: " + dateEnd + ")");
                printLine();
                continue;
            }

            if (input.startsWith("event")) {
                String[] parts = input.substring(6).trim().split(" /from ");
                if (parts.length != 2) {
                    printLine();
                    System.out.println("Format invalid, do <body> /from <dateStart> /to <dateEnd>");
                    printLine();
                    continue;
                }
                String body = parts[0];
                String[] dateParts = parts[1].split(" /to ");
                if (dateParts.length != 2) {
                    printLine();
                    System.out.println("Format invalid, do <body> /from <dateStart> /to <dateEnd>");
                    printLine();
                    continue;
                }
                String dateStart = dateParts[0];
                String dateEnd = dateParts[1];
                tasks[taskCount] = new Event(body, dateStart, dateEnd);
                taskCount++;

                printLine();
                System.out.println("added: " + body + " (from: " + dateStart + " to: " + dateEnd + ")");
                printLine();
                continue;
            }
        }

        

        scanner.close();
    }
}
