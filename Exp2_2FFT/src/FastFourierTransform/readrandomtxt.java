package FastFourierTransform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readrandomtxt 
{
	public static  void read(int n,int[] x,int[] y)throws IOException 
		{
			 
		     File file = new File("e:\\input.txt");  //存放数组数据的文件  
			 BufferedReader in = new BufferedReader(new FileReader(file));  //  
		     String line;  //一行数据  
		   
		     for(int j=0;(j<n&&((line = in.readLine()) != null));j++)
			     {  
			    	x[j] = Integer.parseInt(line);
			    	
				 }  
		     for(int j=n;(j<2*n&&((line = in.readLine()) != null));j++)
			     {  
			    	 y[j-n] = Integer.parseInt(line);
			    	
				 }  
		     in.close();  
		     
		    // return x;
		}

}
