package FastFourierTransform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Creatrandomtxt {
	public static void Creat(int n)throws IOException 
	{
	 
	 int[] arr = new int[n]; 
	 
     for(int i=0;i<130;i++)
     {  
  
     arr[i] = (int)(Math.random()*100+2);  
     System.out.println(i+"  "+arr[i]);  
   
     }  
    
     File file = new File("E:\\PB14210209-project2\\ex2\\input","input.txt");  //存放数组数据的文件  
    
     FileWriter out = new FileWriter(file);  //文件写入流  
    
  
     for(int i=0;i<n;i++)
     {  
  
    	 out.write(arr[i]+"\r\n");  
  
     }  
     
     out.close();  
	}
}
