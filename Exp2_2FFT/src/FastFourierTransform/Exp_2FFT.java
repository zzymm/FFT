package FastFourierTransform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exp_2FFT {
	/*
	 * 
	 * 
	 * 
	 * 
	 *����Ҷ�任
	 */
	
public  static Complex[] fft(Complex[] a)
		{
			int n = a.length;
			if(n==1)
				{
				  return a;
				}
			double nth = 2 *  Math.PI / n;  
			Complex wn = new Complex(Math.cos(nth), Math.sin(nth));  
			double _1th = 2 *  Math.PI;  
			Complex w  = new Complex(Math.cos(_1th), Math.sin(_1th)); 
			Complex[] even = new Complex[n/2];
			for(int i=0;i<(n/2);i++)
				{
					even[i]=a[2*i];
				}
			Complex[] odd = new Complex[n/2];
			for(int i=0;i<(n/2);i++)
				{
					odd[i]=a[2*i+1];
				}
			
			Complex[] y0=fft(even);
			Complex[] y1=fft(odd);
			Complex[] y=new Complex[n];
			for(int i=0;i<=(n/2)-1;i++)
				{
					y[i]      =   y0[i].plus(w.times(y1[i]));
					y[i+(n/2)]=   y0[i].minus(w.times(y1[i]));
				    w=w.times(wn);
				}
			return y;
			
		}//function fft


/*
 * 
 * 
 * 
 * 
 *����Ҷ��任ͨ����������Ҷ�任���ٹ���������n��ȡ 
 */
 
public static Complex[] ifft(Complex[] x) 
		{  
		    int n = x.length;  
		    Complex[] y = new Complex[n];  
		
		    // take conjugate  
		    for (int i = 0; i < n; i++) {  
		        y[i] = x[i].conjugate();  
		    }  
		
		    // compute forward FFT  
		    y = fft(y);  
		
		    // take conjugate again  
		    for (int i = 0; i < n; i++) {  
		        y[i] = y[i].conjugate();  
		    }  
		
		    // divide by N  
		    for (int i = 0; i < n; i++) {  
		        y[i] = y[i].scale(1.0 / n);  
		    }  
		
		    return y;  
		
		}  


public static void show(Complex[] x, String title) {  
    System.out.println(title);  
    System.out.println("-------------------");  
    for (int i = 0; i < x.length; i++) {  
        System.out.println(x[i]);  
    }  
    System.out.println();  
}  


public static void main(String[] args) throws IOException 
		{  int k;
		   int j;
           float[] runtimefft=new float[4];//��¼�ø���Ҷ�任ʵ�ֶ���ʽϵ����˵�ʱ��
           float[] runtimeslow=new float[4];//��¼����ͨ�˷�ʵ�ֶ���ʽ��˵�ʱ��
		     int[] N= {4,16,32,60};//����ʽ�Ĺ�ģ����
			 int[] power_2= {1,2,4,8,16,32,64,128,256,512,1024};//2��������
			 Creatrandomtxt.Creat(130);   //�����Լ�д���ഴ��������ļ�
			 File file1 = new File("E:\\PB14210209-project2\\ex2\\output","result.txt");  //����������ݵ��ļ�  
			 FileWriter out1 = new FileWriter(file1);  //�ļ�д����  			
			 
			 
			for(int i=0;i<4;i++)//4�ֲ�ͬ��ģ�Ķ���ʽϵ������
				{   int[] A = new int[N[i]]; 
			        int[] B = new int[N[i]];
			        readrandomtxt.read(N[i] ,A,B);
			        
			        //���´���Ϊ��0
					for(k=0;N[i]>power_2[k];k++);
					if(N[i]!=power_2[k])
						{
							int[] temp1 = new int[power_2[k]]; 
							int[] temp2 = new int[power_2[k]];
							for(j=0;j<N[i];j++)
								{ temp1[j] = A[j];
								  temp2[j] = B[j]; 
								}
							A=temp1;
							B=temp2;
						}
					 
			        Complex[] Ax= new Complex[power_2[k]*2];  //A����ʽ
			        Complex[] Bx= new Complex[power_2[k]*2];  //B����ʽ
			        Complex[] ABy= new Complex[power_2[k]*2]; //AB����ʽ����Ҷ�任����˽��---��ֵ���ʽ
			        Complex[] ABx= new Complex[power_2[k]*2]; //AB����ʽ����Ҷ�任����˽�����任��Ľ��
			        Complex[] ABx_slow= new Complex[power_2[k]*2];//ԭʼ��˷����õ��Ľ��
			  
			        
			        for (j = 0; j < power_2[k]*2; j++) //��int������ת��ΪComplex������
				        {  if(j<power_2[k])
					        {
					            Ax[j] = new Complex(A[j], 0);  
					            Bx[j] = new Complex(B[j], 0);  
					            ABx_slow[j]=new Complex(0, 0);
					        }
				        	else
				        	{
				        		Ax[j] = new Complex(0, 0);  
					            Bx[j] = new Complex(0, 0);  
					            ABx_slow[j]=new Complex(0, 0);
				        	}
				        }  
			        
			        
			        long startTime = System.nanoTime();//��fft�����������ʽ��ˣ�ͳ��ʱ��
			        Complex[] Ay = fft(Ax); 
			        show(Ax, "Ax"); 
			        Complex[] By = fft(Bx); 
			        show(Bx, "Bx"); 
			        for(int timenum=0;timenum<power_2[k]*2;timenum++)
			        	{
			        		ABy[timenum]=Ay[timenum].times(By[timenum]);
			        	
			        	}
			         ABx = ifft(ABy); 
			         show(ABx, "ABx"); 
			         long endTime = System.nanoTime();
			         runtimefft[i] = (endTime - startTime) ;
			         System.out.println(runtimefft[i]+"ns");
			         
			         
			        startTime = System.nanoTime();//����ͨ��������
			         for(int timenum=0;timenum<power_2[k]*2;timenum++)
			         	{ 
			        	 for(j=0;j<=timenum;j++)
				         	{
				        	 ABx_slow[timenum]=(Ax[j].times(Bx[timenum-j])).plus(ABx_slow[timenum]);
				         	}
				        	
			         	}
			         show(ABx_slow, "ABx_slow"); 
			         endTime = System.nanoTime();
			         runtimeslow[i] = (endTime - startTime) ;
			         System.out.println(runtimeslow[i]+"ns");
			         
			         
			        
			        
				     for(j=0;j<power_2[k]*2;j++)
				     {   out1.write((i+1)+"  "+j+":");
				    	 out1.write((int)ABx_slow[j].re()+"\r\n");
				     }  
				     
				    
					
					
					
				}//for
			
			 out1.close();  
			
			 File file2 = new File("E:\\PB14210209-project2\\ex2\\output","time.txt");  //����������ݵ��ļ�  
			    
		     FileWriter out2 = new FileWriter(file2);  //�ļ�д����  
		    
		  
		     for(int i=0;i<4;i++)
		     {  
		    	 out2.write("��ģ"+N[i]+"fft����ʱ��"+"\r\n");
		    	 out2.write(runtimefft[i]+"ns"+"\r\n");
		    	 out2.write("��ģ"+N[i]+"��ͨ�������ʱ��"+"\r\n");
		    	 out2.write(runtimeslow[i]+"ns"+"\r\n");
		    	 out2.write("\r\n");
		  
		     }  
		     
		     out2.close();  
		     
		     
		  
		}  //main




}
