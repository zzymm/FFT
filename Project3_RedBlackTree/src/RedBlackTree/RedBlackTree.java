package RedBlackTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RedBlackTree {
public static RedBlackNode nil;
public static RedBlackNode root;
private  static int RANK=0;
public RedBlackTree() {
	//nil = new RedBlackNode();
	root  = nil;
}
public static void  Left_Rotate(RedBlackTree T,RedBlackNode x) {
	RedBlackNode y = x.right;
	x.right=y.left;
	if(y.left!=nil) {
		y.left.p = x;
	}
	y.p = x.p;
	if(x.p==nil) {
		root = y;
	}
	else if(x==x.p.left) {
		x.p.left = y;
	}
	else x.p.right = y;
	y.left = x;
	x.p = y;
}
public void Right_Rotate(RedBlackTree T,RedBlackNode y) {
	RedBlackNode x = y.left;
	y.left = x.right;
	if(x.right!=nil) {
		x.right.p = y;
	}
	x.p = y.p;
	if(y.p==nil) {
		root = x;
	}
	else if(y==y.p.left) {
		y.p.left = x;
	}
	else y.p.right = x;
	x.right = y;
	y.p = x;
	
}
public void Insert(RedBlackTree T,RedBlackNode z) {
	RedBlackNode y = nil;
	RedBlackNode x = root;
	while(x!=nil) {
		y = x;
		if(z.key<x.key) {
			x=x.left;
		}
		else {
			x=x.right;
		}
		}
	z.p=y;
	if(y==nil) {
		root=z;
	}
	else if(z.key<y.key) {
		y.left=z;
	}
	else y.right=z;
	z.left=nil;
	z.right=nil;
	z.color="red";
	Insert_Fixup(T,z);
//	T.SetandGet_Size(T,RedBlackTree.root);
//	T.Reset_RANK();
//	T.Set_Rank(T,RedBlackTree.root);
	
	
}
public void Insert_Fixup(RedBlackTree T,RedBlackNode z) {
	RedBlackNode y;
	while(z.p.color.equals("red")) {
		if(z.p==z.p.p.left) {
			y=z.p.p.right;
			if(y.color.equals("red")) {
				z.p.color="black";
				y.color="black";
				z.p.p.color="red";
				z=z.p.p;
			}
			else if(z==z.p.right) {
				z=z.p;
				Left_Rotate(T,z);
			}
			else {
				z.p.color="black";
				z.p.p.color="red";
				Right_Rotate(T,z.p.p);
			}
		}
		else {
			y=z.p.p.left;
			if(y.color.equals("red")) {
				z.p.color="black";
				y.color="black";
				z.p.p.color="red";
				z=z.p.p;
			}
			else if(z==z.p.left) {
				z=z.p;
				Right_Rotate(T,z);
			}
			else {
				z.p.color="black";
				z.p.p.color="red";
				Left_Rotate(T,z.p.p);
			}
			
		}
	//T.root.color="black";
	}//while
	root.color="black";

}//Insert_Fixup


public void Reset_RANK() {
	 RANK=1;
}
public void Set_Rank(RedBlackTree T,RedBlackNode t) {
	if(t!=nil) {
		
		Set_Rank(T,t.left);
		t.rank=RANK++;
		Set_Rank(T,t.right);
	}
	
}

public int SetandGet_Size(RedBlackTree T,RedBlackNode x) {
	if(x!=nil) {
		x.size=SetandGet_Size(T,x.left)+SetandGet_Size(T,x.right)+1;
		return x.size;
	}
	else{
		
		return 0;
	}
	
}
 public RedBlackNode OS_SELECT(RedBlackNode x,int i) {
	 int r=x.left.size+1;
	 if(i==r) {
		 return x;
	 }
	 else if(i<r) {
		 return OS_SELECT(x.left,i); 
	 }
	 else return OS_SELECT(x.right,i-r);
	 
 }

public int OS_RANK(RedBlackTree T,RedBlackNode x) {
	int r = x.left.size+1;
	RedBlackNode y=x;
	while(y!=root) {
		if(y==y.p.right) {
			r=r+y.p.left.size+1;
		}
		y=y.p;
	}
	return r;
}
 public  void Transplant(RedBlackTree T,RedBlackNode u,RedBlackNode v) {
	 if(u.p==nil) {
		 root=v;
	 }
	 else if(u==u.p.left) {
		 u.p.left=v;
	 }
	 else u.p.right=v;
	 v.p=u.p;
 }
 public RedBlackNode Treemin(RedBlackTree T,RedBlackNode z) {
	 RedBlackNode x = z;
	 for(;x!=nil;z=x,x=x.left);
	 return z;
 }
 
 public int TreeMaxSize(RedBlackTree T,RedBlackNode z) {
	 Reset_RANK();
	 Set_Rank(T,root);
	 RedBlackNode x = z;
	 for(;x!=nil;z=x,x=x.right);
	 return z.rank;
 }
 
 public void Delect(RedBlackTree T,RedBlackNode z) {
	 RedBlackNode y=z;
	 RedBlackNode x;
	 String Original_color=y.color;
	 if(z.left==nil) {
		 x=z.right;
		 Transplant(T,z,z.right);
	 }
	 else if(z.right==nil) {
		 x=z.left;
		 Transplant(T,z,z.left);
	 }
	 else {
		 y=Treemin(T,z.right);
		 Original_color=y.color;
		 x=y.right;
		 if(y.p==z) {
			 x.p=y;
		 }
		 else {
			 Transplant(T,y,y.right);
			 y.right=z.right;
			 y.right.p=y;
		 } 
		 Transplant(T,z,y);
		 y.left=z.left;
		 y.left.p=y;
		 y.color=z.color;
	 }
	 if(Original_color.equals("black")) {
		Delect_Fixup(T,x);
	 }
	
	 //Delect_Fixup(T,x);
	 Reset_RANK();
	 Set_Rank(T,root);
	 SetandGet_Size(T,root);
	 
 }
 
 public void Delect_Fixup(RedBlackTree T,RedBlackNode x) {
	 RedBlackNode w;
	 while(x!=root&&x.color.equals("black")) {
		 if(x==x.p.left) {
			w=x.p.right; 
			if(w.color.equals("red")) {
				w.color="black";
				x.p.color="red";
				Left_Rotate(T,x.p);
				w=x.p.right;
			}
			if(w.left.color.equals("black")&&w.right.color.equals("black")) {
				w.color="red";
				x=x.p;
				
			}
			else if(w.right.color.equals("black")) {
				w.left.color="black";
				w.color="red";
				Right_Rotate(T,w);
				w=x.p.right;
				
			}
			else {
				w.color=x.p.color;
				x.p.color="black";
				w.right.color="black";
				Left_Rotate(T,x.p);
				x=root;
			}
		 }//if(x!=T.root&&x.color.equals("black"))
		 else if(x==x.p.right){
			w=x.p.left; 
			if(w.color.equals("red")) {
				w.color="black";
				x.p.color="red";
				Right_Rotate(T,x.p);
				w=x.p.left;
			}
			if(w.left.color.equals("black")&&w.right.color.equals("black")) {
				w.color="red";
				x=x.p;
					
			}
			else if(w.left.color.equals("black")) {
				w.right.color="black";
				w.color="red";
				Left_Rotate(T,w);
				w=x.p.left;
					
			}
			else {
				w.color=x.p.color;
				x.p.color="black";
				w.left.color="black";
				Right_Rotate(T,x.p);
				x=root;
			}
		 }
		
		 
	 }//while
	 x.color = "black";
	 
 }//Fixup
 
 public void Print_Like_Tree(RedBlackTree T,FileWriter out)throws  IOException{
	 int Max_Height=2*(int)(Math.log(T.TreeMaxSize(T, root)+1)/Math.log(2));
	 RedBlackNode height[][]=new RedBlackNode[Max_Height][];
	 //***
	 for(int i=0;i<Max_Height;i++) {
			height[i]=new RedBlackNode[(int)Math.pow(2.0,i)];
			}
	  height[0][0]=RedBlackTree.root;
	  //建立数据结构存储树
		for(int i=0,m;i<Max_Height-1;i++) {
			m=(int)Math.pow(2.0,i)-1;
			for(int j=0;j<=m;j++) {
				if(height[i][j]!=RedBlackTree.nil&&height[i][j]!=null) {
				height[i+1][2*j]=height[i][j].left;
				}
				if(height[i][j]!=RedBlackTree.nil&&height[i][j]!=null) {
				height[i+1][2*j+1]=height[i][j].right;
				}
			}
			
		}
		//数组结构存储红黑树，给数组赋值
		for(int i=0,m;i<Max_Height;i++) {
			m=(int)Math.pow(2.0,i)-1;

					for(int j=0;j<=m;j++) {
		
						if(j==0) {
							for(int aa = 0;aa<(int)Math.pow(2.0,(7-i))-1;aa++) {
									System.out.print("  ");
									out.write("  ");
							}
						}
						if(i!=0&&j!=0) {
							if(j%2==0||height[i][j]==null) {
								for(int aa = 0;aa<(int)Math.pow(2.0,(8-i))-1;aa++) {
									System.out.print("  ");
									out.write("  ");
								}
							}
							else {
								for(int aa = 0;aa<(int)Math.pow(2.0,(8-i))-2;aa++) {
									System.out.print("--");
									out.write("--");
								}
							}
							
						}
						if(height[i][j]!=RedBlackTree.nil&&height[i][j]!=null) {
							//System.out.print(height[i][j].key+"("+height[i][j].size+")");
							if(height[i][j].key<10) {
								System.out.print(" ");
								out.write(" ");
							}
							System.out.print(height[i][j].key);
							out.write(String.valueOf(height[i][j].key));
							if(height[i][j].color.equals("black")) {
								System.out.print("b");
								out.write("b");
							}
							if(height[i][j].color.equals("red")) {
								System.out.print("r");
								out.write("r");
							}
						}
						else if(height[i][j]==RedBlackTree.nil) {
							System.out.print("00b");
							out.write("00b");
						}
						else {
							System.out.print("  ");
							out.write("  ");
						}
					
	 
					}//for打印一行
					System.out.println("");
					System.out.println("");
					out.write("\r\n");
					out.write("\r\n");
		}//for打印下一列
 }//printliketree 
 

 public void pre_print_Tree(RedBlackTree T,RedBlackNode t){
		if(t!=nil) {
			System.out.print(t.key+"("+t.rank+")"+"  ");
			pre_print_Tree(T,t.left);
			pre_print_Tree(T,t.right);
		}
		
	}
public void in_print_Tree(RedBlackTree T,RedBlackNode t){
		if(t!=nil) {
			in_print_Tree(T,t.left);
			System.out.print(t.key+"("+t.rank+")"+"  ");
			in_print_Tree(T,t.right);
		}
		
	}
public void post_print_Tree(RedBlackTree T,RedBlackNode t){
		if(t!=nil) {
			post_print_Tree(T,t.left);
			post_print_Tree(T,t.right);
			System.out.print(t.key+"("+t.rank+")"+"  ");
		}
		
	}

 public void pre_print_Tree(RedBlackTree T,RedBlackNode t,FileWriter out)throws  IOException{
		if(t!=nil) {
			System.out.print(t.key+"("+t.rank+")"+"  ");
			out.write(t.key+"("+t.rank+")"+"  ");
			pre_print_Tree(T,t.left,out);
			pre_print_Tree(T,t.right,out);
		}
		
	}
 public void in_print_Tree(RedBlackTree T,RedBlackNode t,FileWriter out)throws  IOException{
		if(t!=nil) {
			in_print_Tree(T,t.left,out);
			System.out.print(t.key+"("+t.rank+")"+"  ");
			out.write(t.key+"("+t.rank+")"+"  ");
			in_print_Tree(T,t.right,out);
		}
		
	}
 public void post_print_Tree(RedBlackTree T,RedBlackNode t,FileWriter out)throws  IOException{
		if(t!=nil) {
			post_print_Tree(T,t.left,out);
			post_print_Tree(T,t.right,out);
			System.out.print(t.key+"("+t.rank+")"+"  ");
			out.write(t.key+"("+t.rank+")"+"  ");
		}
		
	}
 
 public void output_txt(RedBlackTree T,int size,int[] delecet_data,long[]  time2) {
	  try{ 
		     File file = new File("E:\\PB14210209-project3\\project\\output\\size"+size);
		     if(!file.exists()){ 
		    	 file.mkdirs(); 
		    	}
	/*	     System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		     System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		     System.out.println("规模为"+size+"的树");
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"preoreder.txt");
		     FileWriter out = new FileWriter(file);  //文件写入流  
		     out.write("先序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("先序遍历输出，输出格式:data(rank)");
		     pre_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"inoreder.txt");
		     out = new FileWriter(file);  //文件写入流  
		     out.write("中序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("中序遍历输出，输出格式:data(rank)");
		     in_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"postoreder.txt");
		     out = new FileWriter(file);  //文件写入流  
		     out.write("后序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("后序遍历输出，输出格式:data(rank)");
		     post_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		   */  
/*		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"Tree_Graph.txt");
		     FileWriter out = new FileWriter(file,true);  //文件写入流  
		     out.write("删除结点后红黑树结构如图:"+"\r\n");
		     System.out.println("删除结点后红黑树结构如图:");
		     Print_Like_Tree(T,out);
		     out.close();*/
		     
	/*	     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"time1.txt");
		     out = new FileWriter(file);  //文件写入流  
		     for(int i=0;i<time1.length;i++) {
		    	 if(i!=time1.length-1) {
		    	 out.write("插入第"+(i+1)+"0个元素所耗费的时间：");
		    	 out.write(String.valueOf(time1[i])+"ns\r\n");
		    	 }
		    	 else {
		         out.write("插入全部元素所耗费的时间：");
			     out.write(String.valueOf(time1[i])+"ns\r\n");
		    	 }
		     }
		     out.close();
	*/
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"delete_data.txt");
		     FileWriter out = new FileWriter(file);  //文件写入流  
		     out.write("删除的第n/4="+size/4+"个元素的值：");
		     out.write(String.valueOf(delecet_data[0])+"\r\n");
		     out.write("删除的第n/3="+size/3+"个元素的值：");
		     out.write(String.valueOf(delecet_data[1])+"\r\n");
		     out.close();
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"time2.txt");
		     out = new FileWriter(file);  //文件写入流  
		     out.write("删除的第n/4="+size/4+"个元素的时间：");
		     out.write(String.valueOf(time2[0])+"ns\r\n");
		     out.write("删除的第n/4="+size/3+"个元素的时间：");
		     out.write(String.valueOf(time2[1])+"ns\r\n");
		     out.close();
		  }
			
		catch(IOException e) {
				System.out.println(e.toString());
		}
 }
 
 public void output_txt(RedBlackTree T,int size,int num) {
	  try{ 
		     File file = new File("E:\\PB14210209-project3\\project\\output\\size"+size);
		     if(!file.exists()){ 
		    	 file.mkdirs(); 
		    	}
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"Tree_Graph.txt");
		     FileWriter out = new FileWriter(file,true);  //文件写入流  
		     out.write("删除"+num+"结点后红黑树结构如图:"+"\r\n");
		     System.out.println("删除"+num+"结点后红黑树结构如图:");
		     Print_Like_Tree(T,out);
		     out.close();
		     
		  }
			
		catch(IOException e) {
				System.out.println(e.toString());
		}
}
 
 
 public void output_txt(RedBlackTree T,int size,long[] time1) {
	  try{ 
		     File file = new File("E:\\PB14210209-project3\\project\\output\\size"+size);
		     if(!file.exists()){ 
		    	 file.mkdirs(); 
		    	}
		     System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		     System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		     System.out.println("规模为"+size+"的树");
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"preoreder.txt");
		     FileWriter out = new FileWriter(file);  //文件写入流  
		     out.write("先序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("先序遍历输出，输出格式:data(rank)");
		     pre_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"inoreder.txt");
		     out = new FileWriter(file);  //文件写入流  
		     out.write("中序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("中序遍历输出，输出格式:data(rank)");
		     in_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"postoreder.txt");
		     out = new FileWriter(file);  //文件写入流  
		     out.write("后序遍历输出，输出格式:data(rank)"+"\r\n");
		     System.out.print("后序遍历输出，输出格式:data(rank)");
		     post_print_Tree(T,root,out);
		     out.close();
		     System.out.println("");
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"Tree_Graph.txt");
		     out = new FileWriter(file,false);  //文件写入流  
		     out.write("红黑树结构如图"+"\r\n");
		     System.out.println("红黑树结构如图");
		     Print_Like_Tree(T,out);
		     out.close();
		     
		     file = new File("E:\\PB14210209-project3\\project\\output\\size"+size,"time1.txt");
		     out = new FileWriter(file);  //文件写入流  
		     for(int i=0;i<time1.length;i++) {
		    	 if(i!=time1.length-1) {
		    	 out.write("插入第"+(i+1)+"0个元素所耗费的时间：");
		    	 out.write(String.valueOf(time1[i])+"ns\r\n");
		    	 }
		    	 else {
		         out.write("插入全部元素所耗费的时间：");
			     out.write(String.valueOf(time1[i])+"ns\r\n");
		    	 }
		     }
		     out.close();
		  }
			
		catch(IOException e) {
				System.out.println(e.toString());
		}
}
}
