package sp.utils.console;

import java.util.Scanner;

public class consolUtil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line;
        while ((line = scanner.nextLine()) != null) {
            if (line.equals("QUIT")) {
                System.out.println("QUIT");
                break;
            } else if (line.equals("REPORT")) {
                System.out.println("REPORT");
            } else { // Date

            }
        }

    }
}
