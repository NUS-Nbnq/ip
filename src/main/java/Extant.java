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
        System.out.println("Exiting");
    }
}
