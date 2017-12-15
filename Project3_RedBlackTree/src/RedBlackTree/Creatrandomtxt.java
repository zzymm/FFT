package RedBlackTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Creatrandomtxt {
	/*建立一个存放整数1~100的整数数组*/
	/*建立一个规模为500随机数数组*/
	/*随机数范围是1~100*/
	/*随机数对应整数数组角标，取出对应元素并置0，下次取元素时通过判断是否为0防止元素重复出现*/
	public static final int num =100;
	public static final int max =500;
	public static void Creat(int n){
	 int[] arr_rand = new int[max]; 
	 int[] arr=new int[num];
	 for(int i=0;i<num;i++) {
		 arr[i]=i+1;
	 }
	 
     for(int i=0;i<max;i++){  
     arr_rand[i] = (int)(Math.random()*100);  
     //System.out.println((i+1)+"  "+arr[i]);  
     }  
    
     try{ 
     File file = new File("E:\\PB14210209-project3\\project\\input");
     if(!file.exists()){ 
    	 file.mkdirs(); 
    	}
    	 
     file = new File("E:\\PB14210209-project3\\project\\input","input.txt");  //存放数组数据的文件  
     
     FileWriter out = new FileWriter(file);  //文件写入流  
    
  
     for(int i=0,j=0;j<n;i++){ 
    	 if( arr[arr_rand[i]]!=0) {
    		 out.write(arr[arr_rand[i]]+"\r\n");
    		 System.out.println((j+1)+"  "+arr[arr_rand[i]]);  
        	 arr[arr_rand[i]]=0;
        	 j++;
        	 
    	 }
    	 if(i>=max) {
    		 break;
    	 }

    	 
     }  
     
     out.close();  
     	}
	
	catch(IOException e) {
		System.out.println(e.toString());
	}//catch
  }//Creat
}//class Creatrandomtxt
