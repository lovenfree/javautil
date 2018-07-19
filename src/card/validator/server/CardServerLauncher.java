
package card.validator.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import card.validator.server.util.FileUtil;
import card.validator.utils.CardUtility;

public class CardServerLauncher {
	public static void main(String[] args) throws IOException, InterruptedException {
		CardServer cardSever = new CardServer();
		Thread thread = new Thread(cardSever);
		thread.start();
		
		Scanner scanner = new Scanner(System.in);
		
		String line;
		while ((line = scanner.nextLine()) != null) {
			if (line.equals("QUIT")) {
				cardSever.close();
				break;
			}else if(line.equals("REPORT")){
				CardServerLauncher laun = new CardServerLauncher();
				laun.makeReport();
			}
			
		}
	}
	
	
	
	public void makeReport(){
		ArrayList<String> files = FileUtil.listFilesAndFolders("../SERVER/");
		
		ArrayList<String> reportFile = new  ArrayList();
		for(String file : files){
			String fileName = file.split(" ")[0];
			System.out.println(fileName.substring(9, 17));
			
			System.out.println(CardUtility.getCurrentDateString());
			if(fileName.substring(9, 17).equals(CardUtility.getCurrentDateString())){
				reportFile.add(file);
					
			}
		}
		
		
		System.out.println(reportFile.size());
			
	}
	
	public load
	
 
}


