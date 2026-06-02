package sp.utils.test.proxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class RunManager {

	public static List<Route> routes=null;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		byte[] proxy = readRoutingRule(args[0]);
		
		String json = new String(proxy);
		ProxyInfo info = parsingProxyJson(json);
		routes = info.routes;

		ProxyServer server = new ProxyServer();
		server.start("127.0.0.1", info.getPort(),info.getRoutes());
		
		
	}
	
	
	
	
	private static ProxyInfo parsingProxyJson(String json){
	       Gson gson = new Gson();
		ProxyInfo info = gson.fromJson(json, ProxyInfo.class);
		return info;
		
	}
	
	private static void sleep(int m) {
		try {

			Thread.sleep(m); //1�� ���

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
	}
	
	private static String consoleInput() {
		Scanner scanner = new Scanner(System.in);
		
		String line = scanner.nextLine();
		return line;
		
	}

	private static byte[] readRoutingRule(String path) throws IOException {
		byte[] array = Files.readAllBytes(Paths.get(path));
		return array;
	}
	
	
	private static String readfile(String path) {
		
		 String str = null;
		 String content =null;
	        try {
	            BufferedReader in = new BufferedReader(new FileReader(path));
	            while((str = in.readLine())!=null) {
	            	content = str;
	              
	            }
	            in.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return content;
		}
	
	


}
