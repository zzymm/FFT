package RedBlackTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Creatrandomtxt {
	/*����һ���������1~100����������*/
	/*����һ����ģΪ500���������*/
	/*�������Χ��1~100*/
	/*�������Ӧ��������Ǳ꣬ȡ����ӦԪ�ز���0���´�ȡԪ��ʱͨ���ж��Ƿ�Ϊ0��ֹԪ���ظ�����*/
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
    	 
     file = new File("E:\\PB14210209-project3\\project\\input","input.txt");  //����������ݵ��ļ�  
     
     FileWriter out = new FileWriter(file);  //�ļ�д����  
    
  
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
