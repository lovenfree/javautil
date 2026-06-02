package sp.src.com.checker.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Scanner;

public class CheckerStarter {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ParseException {
		Scanner scanner = new Scanner(System.in);
		String line, strId, strPassword;

		while (true) {
			Checker checker = new Checker();
			
			line = scanner.nextLine(); // id, password
			if (line.length() < 10) {
				System.out.println("Wrong ID Password");
				continue;
			}
			strId = line.substring(0, 8);
			strPassword = line.substring(9);

			if (checker.login(strId, strPassword)) {
				System.out.println("LOGIN SUCCESS");
				
				// Inspection
				while (true) {
					line = scanner.nextLine();	// busId
					
					if (line.equals("LOGOUT")) {
						checker.logout();
						break;
					}
					
					checker.getOnBus(line);

					// Card Validation
					while (true) {
						line = scanner.nextLine();	//cardInfo
						
						if (line.equals("DONE")) {
							checker.getOffBus();
							break;
						}
						
						checker.inspectCard(line);
					}
				}
			} else {
				System.out.println("LOGIN FAIL");
			}
		}
	}
}