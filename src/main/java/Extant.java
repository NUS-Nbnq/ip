import java.util.Scanner;

public class Extant {

    public static void printLine()
    {
        System.out.println("____________________________________________________________");
    }
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
        String input;
        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Farewell. Until our paths cross again.");
                printLine();
                break;
            }

            if (input.equals("list")) {
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
                continue;
            }

            tasks[taskCount] = input;
            taskCount++;

            printLine();
            System.out.println("added: " + input);
            printLine();
        }

        scanner.close();
    }
}