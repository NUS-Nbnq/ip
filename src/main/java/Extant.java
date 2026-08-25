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

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Farewell. Until our paths cross again.");
                printLine();
                break;
            }

            printLine();
            System.out.println(input);
            printLine();
        }

        scanner.close();
    }
}