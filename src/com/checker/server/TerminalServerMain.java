package sp.src.com.checker.server;

import java.io.IOException;
import java.util.Scanner;

public class TerminalServerMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		TerminalServer cardSever = new TerminalServer();
		Thread thread = new Thread(cardSever);
		thread.start();
		
		CheckerReport report = new CheckerReport();

		Scanner scanner = new Scanner(System.in);
		
		String line;
		while ((line = scanner.nextLine()) != null) {
			if (line.equals("QUIT")) {
				cardSever.close();
				break;
			} else if (line.equals("REPORT")) {
				if (report.reportValidator()) {
					System.out.println("REPORT FINISH");
				}
			} else { // Date
				String option = null;
				if (line.length() > 9) {
					option = line.split(" ")[1];
				}
				report.printReport(line.substring(0, 8), option);
			}
		}
	}
}