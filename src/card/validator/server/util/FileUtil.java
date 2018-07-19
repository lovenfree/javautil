
package card.validator.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileUtil {

	 public static void createFolder(String path){
		 File zipfileDirectory = new File(path); // zipfile가 폴더명
		 
	        if(!zipfileDirectory.exists()){
	 
	            zipfileDirectory.mkdirs();
	            System.out.println("zipfile 디렉토리를 생성했습니다. "+ path);
	        }else
	        {
	        	 System.out.println("zipfile is existed. "+ path);
	        }
//	        }else{ // zipfile 폴더가 존재하면 폴더 내 기존 파일 다 삭제
//	 
//	            File[] zipfileFiles = zipfileDirectory.listFiles();
//	 
//	            for (File file : zipfileFiles) {
//	 
//	                file.delete();
//	            }
//	 
//	            System.out.println("zipfile 폴더내의 기존 파일을 모두 삭제하였습니다."); }        

	 }
	 
	 
	 public static void createFile(String path){
			try {
	    		 
			      File file = new File(path);
			      
			      if (file.createNewFile()){
			        System.out.println("File is created!");
			      }else{
			        System.out.println("File already exists.");
			      }
			      
		    	} catch (IOException e) {
			      e.printStackTrace();
			}
	 }
	 
	 public static void updateFile(String path, String text){
		 
		  File f= new File(path);
		  System.out.println("path : " + path);
		  
		  RandomAccessFile rf= null;
		  
		 try{
              rf= new RandomAccessFile(f, "rw");
              rf.seek(rf.length());
              if(rf.length()!=0){
            	  rf.writeBytes("\r\n");
              }
              rf.writeBytes(text);
              rf.close();
	          
		 }catch(Exception e){
	              e.printStackTrace();
	          }
	 }
	 
	 public static ArrayList<String> listFilesAndFolders(String directoryName){
		 ArrayList<String> fileList = new ArrayList();

	     File directory = new File(directoryName);

	     //get all the files from a directory

	     File[] fList = directory.listFiles();

	     for (File file : fList){
	       	 long fileSize = file.length();
	         System.out.println(file.getName() + "  " +  fileSize+"bytes");
	         String data = file.getName() + "  " +  fileSize+"bytes";
	         fileList.add(data);
	         
	     }
	     
	     return fileList;
	 }
	 

//	 public static void fileMove(String srcFile, String outFile){
//		 final int BUF_SIZE = 512; 
//	     byte[] buffer = new byte[BUF_SIZE]; 
//	     InputStream in;
//	     OutputStream out;
//		try {
//			in = new BufferedInputStream(new FileInputStream(srcFile));
//			out = new BufferedOutputStream(new FileOutputStream(outFile)); 
//		      int nRead; 
//		      
//		      while ((nRead = in.read(buffer)) > 0) 
//		      { 
//		          out.write(buffer,  0, nRead ); 
//		      } 
//		      in.close(); 
//		      out.close(); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	   
//	 } 
	 
	 
//		public static void moveFileToBackup(String src, String dsc) {
//	
//		}


		public static void moveFileToBackup(String src, String dsc) {
			// TODO Auto-generated method stub
			File fileFrom = new File(src); // source
			File fileTo = new File(dsc); //"..//BACKUP//" + name); // destination
			fileTo.delete();
			fileFrom.renameTo(fileTo);			
		}



}


