package Matrix_Multi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Random;
//import java.io.*;




public class Exp1_MatrixChainOrder
{
	public static int n=35;
	public static int[] nsize= {5,10,20,30};
	public static float[] runtime=new float[4];
	//public static String[] Matrix_Result=new String();
	public static StringBuilder Matrix_Result= new StringBuilder("");
	private static int[][] m = new int[100][100];
	private static int[][] s = new int[100][100];
	private static int[] p =new int [105];
	
/*		public static void print(int i, int j)
		    {
		        if (i == j)
		            System.out.print("A" + i);
		        else
		        {
		            System.out.print("(");
		            print(i, s[i][j]);
		            print(s[i][j] + 1, j);
		            System.out.print(")");
		        }
		    }*/
		
		public static void print2_0(int i, int j)
	    {
	        if (i == j)
		        {  System.out.print("A" + i);
		         // Matrix_Result[index]=Matrix_Result[index]+"A"+i;
		        Matrix_Result.append("A"+i);
		        }
	        else
	        {
	            System.out.print("(");
	           // Matrix_Result[index]=Matrix_Result[index]+"(";
	            Matrix_Result.append("(");
	            print2_0(i, s[i][j]);
	            print2_0(s[i][j] + 1, j);
	            System.out.print(")");
	          //  Matrix_Result[index]=Matrix_Result[index]+")";
	            Matrix_Result.append(")");
	        }
	    }
		
		


   	public static void main(String[] args) throws IOException 
	{  
	  
	     int[] arr = new int[n]; 
	 
	     for(int i=0;i<n;i++)
	     {  
	  
	     arr[i] = (int)(Math.random()*100+2);  
	     System.out.println(i+"  "+arr[i]);  
	   
	     }  
	    
	     File file = new File("e:\\input.txt");  //存放数组数据的文件  
	    
	     FileWriter out = new FileWriter(file);  //文件写入流  
	    
	  
	     for(int i=0;i<n;i++)
	     {  
	  
	    	 out.write(arr[i]+"\r\n");  
	  
	     }  
	     
	     out.close();  
	     /*
	      * 以上产生随机数文件input。txt文件
	      * */
	     
//		Scanner scan = new Scanner(System.in);
		
//		System.out.println("please enter the number of matrices");
//		n=scan.nextInt();
		
//		System.out.println("please enter the size of matrices");
	     
	     BufferedReader in = new BufferedReader(new FileReader(file));  //  
	     String line;  //一行数据  
	   
	     for(int j=0;((line = in.readLine()) != null);j++)
	     {  
	    	p[j] = Integer.parseInt(line);  
		 }  
	     in.close();  
	       
	     /*
	      * 以上读取随机数文件input。txt文件
	      * */
/*	     for(int i=0;i<n;i++)
	     {  
	     
	       System.out.println(i+"  "+p[i]+"\t");  
	     
	     }  
	     */

	     
		for(int i=0;i<=n;i++)
		{
//			System.out.print("please enter p"+i+":");
//			p[i]=scan.nextInt();
			m[i][i]=0;
		}
		for(int index=0;index<4;index++)
		{
			//long startTime = System.currentTimeMillis();
			long startTime = System.nanoTime();
				for(int l=2;l<=nsize[index];l++)
				{
					for(int i =1;i<=nsize[index]-l+1;i++)
					{
						int j=i+l-1;
						
						m[i][j]=9999999;
						
						for(int k=i;k<=j-1;k++)
						{	
							int q=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
							
							if(q<=m[i][j])
							{
								m[i][j]=q;
								s[i][j]=k;
								
							}//if
						
						}//for int k
					}//for int i
					
				}//for length
		//	long endTime = System.currentTimeMillis();
			long endTime = System.nanoTime();

            runtime[index] = (endTime - startTime) ;

		    System.out.println(Float.toString(runtime[index]) + " ns");

				System.out.printf(" %d\n", m[1][nsize[index]]);
				print2_0(1, nsize[index]);
				//Matrix_Result.append('\n');
				//Matrix_Result.append('\r');
				Matrix_Result.append("\r\n");  
				System.out.println("completed");
		}//for
		  
		System.out.println(Matrix_Result);
		
		
		
		
	     File file1 = new File("e:\\time.txt");  //存放数组数据的文件  
		    
	     FileWriter out1 = new FileWriter(file1);  //文件写入流  
	    
	  
	     for(int i=0;i<4;i++)
	     {  
	  
	    	 out1.write(runtime[i]+"ns"+"\r\n");  
	  
	     }  
	     
	     out1.close();  
				
	     
	     File file2 = new File("e:\\result.txt");  //存放数组数据的文件  
		    
	     FileWriter out2 = new FileWriter(file2);  //文件写入流  
	    
	  
	     
	  
	     out2.write(Matrix_Result.toString());  
	  
	    
	     
	     out2.close();  
				
	     
	     
	 	
	}//main
	
	
	
}//class
