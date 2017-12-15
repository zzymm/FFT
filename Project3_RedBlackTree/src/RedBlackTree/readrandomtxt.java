package RedBlackTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readrandomtxt {
	public static  void read(int n,int[] x) {
		try{
			File file = new File("E:\\PB14210209-project3\\project\\input","input.txt");  //存放数组数据的文件  
		
			 BufferedReader in = new BufferedReader(new FileReader(file));  //  
		     String line;  //一行数据  
		   
		     for(int j=0;(j<n&&((line = in.readLine()) != null));j++)
			     {  
			    	x[j] = Integer.parseInt(line);
			    	
				 }  
		     
		     in.close();  
		     }//try
	catch(IOException e) {
		System.out.println("File read Error"+e);
	}//catch

}
}