package Matrix_Multi;



//import java.util.Random;
import java.io.*;

public class Write_Read_Randnum {  
 public static void main(String[] args) throws IOException {  
  int n = 35;  //N*N����  
  //double[][] arr = new double[n][n]; //���������  
  //double[][] arr2 = new double[n][n];;  //��ȡ��������  
   int[] arr = new int[n]; 
  //�����ʼ����������ɵ�[0,100)֮���double��  
  for(int i=0;i<n;i++){  
  
    arr[i] = (int)(Math.random()*100+2);  
    System.out.println(arr[i]);  
   
  }  
    
  File file = new File("e:\\input.txt");  //����������ݵ��ļ�  
    
  FileWriter out = new FileWriter(file);  //�ļ�д����  
    
  //�������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���  
  for(int i=0;i<n;i++){  
  
    out.write(arr[i]+"\r\n");  
  
  }  
  out.close();  
    
  BufferedReader in = new BufferedReader(new FileReader(file));  //  
  String line;  //һ������  
  int row=0;  
  //���ж�ȡ������ÿ��������뵽������  
  while((line = in.readLine()) != null)
  {  
   String[] temp = line.split("\t");   
   for(int j=0;j<temp.length;j++)
   {  
    arr2[row][j] = Double.parseDouble(temp[j]);  
   }  
   row++;  
  }  
  in.close();  
    
  //��ʾ��ȡ��������  
  for(int i=0;i<n;i++)
  {  
   for(int j=0;j<n;j++)
   {  
    System.out.print(arr2[i][j]+"\t");  
   }  
   System.out.println();  
  }  
 }  
}  