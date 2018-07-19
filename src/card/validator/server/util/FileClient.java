package card.validator.client.util;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

    
//	public static void SendToServer(String path) throws UnknownHostException, IOException
//	{
//		Socket s = new Socket("127.0.0.1", 27015);
//		//OutputStream out = s.getOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//		byte[] buffer = new byte[4096];
//		int readLen;
//		
//        //get all the files from a directory
//        FileInputStream inputStream = new FileInputStream(path);
//        oos.writeObject(new File(path).getName());
//        while((readLen = inputStream.read(buffer)) != -1) {
//        	oos.write(buffer,0,readLen);
//        }   
//        inputStream.close();    
//		
//        s.close();
//        
//        System.out.println(path+" is sent.");
//	}
//	
	
	//파일명(String),파일크기(int),파일데이터(NByte)
		public static void sendToServer(String path) throws IOException {
			Socket socket = null;
			DataOutputStream os = null;
			try {
				socket = new Socket("127.0.0.1", 27015);
				os = new DataOutputStream(socket.getOutputStream());
				
				byte[] buffer = new byte[4096];
				int length;
				
				// get all the files from a directory
				//File directory = new File("..//" + insId);
				//File[] fList = directory.listFiles();
				//for (File file : fList) {
				File file = new File(path);
				
					if (file.isFile()) {
						os.writeUTF(file.getName());
						os.writeInt((int) file.length());
						
						FileInputStream is = null;
						try {
							is = new FileInputStream(file.getPath());
							while ((length = is.read(buffer)) != -1) {
								os.write(buffer, 0, length);
							}
						} finally {
							if (is != null) { is.close(); }
						}
		
						// move file to backup folder
						//moveFileToBackup(file.getPath(), file.getName());
					}
				//}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (os != null) { os.close(); }
				if (socket != null) { socket.close(); }
			}
		}
	
}

