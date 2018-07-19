package sp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtil {

	public static void main(String[] args) {
		File directory =  new File("./INPUT");
		
		File[] fList = directory.listFiles();
		for(File file:fList) {
			System.out.println(file.getName()+":    "+file.length()+"bytes");
			if(file.length() > 2048) {
				copyFile(file.getName());
			}
		}
	}
	
	static void copyFile(String fileName){
		final int BUFFER_SIZE = 512;
		int readLen;
		
		//copy File
		try {
			
			//create folder
			File destFolder = new File("./OUTPUT");
			if(!destFolder.exists()) {
				destFolder.mkdirs();
			}
			
			InputStream inputStream = new FileInputStream("./INPUT/"+fileName);
			OutputStream outputStream = new FileOutputStream("./OUTPUT/"+fileName);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			while((readLen = inputStream.read(buffer))!=-1) {
				outputStream.write(buffer, 0, readLen);
			}
			
			inputStream.close();
			outputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//movefile
	
	//deletefile
	
	//reculsive
	//https://stackoverflow.com/questions/2056221/recursively-list-files-in-java
	public static void reculsiveScan(String path) {
		
		File root = new File(path);
		File[] list = root.listFiles();
		
		if(list == null) return;
		
		for(File f: list) {
			if(f.isDirectory()) {
				reculsiveScan(f.getAbsolutePath());
				System.out.println("Dir: "+f.getAbsolutePath());
				
			}else {
				System.out.println("File:" + f.getAbsolutePath());
			}
		}
		
	}
	
	
	//read and write by binary
	public static void binaryFileCopy() {
		final int BUF_SIZE = 512;
		byte[] buffer = new byte[BUF_SIZE];
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("test.txt"));
			OutputStream out = new BufferedOutputStream(new FileOutputStream("test2.txt"));
			
			int nRead;
		
				while((nRead=in.read(buffer))>0) {
					out.write(buffer, 0, nRead);	
				}
				in.close();
				out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//read file by line
	public static void fileRead(String path) {
	
		String str;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			while((str = in.readLine())!=null) {
				System.out.println(str);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
