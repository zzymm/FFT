package RedBlackTree;
//import java.math.*;

public class Exp3_RBTree {
	
	public static void main(String args[]) {
		int[] n= {48,60};
		Creatrandomtxt.Creat(70);
		RedBlackTree.nil=new RedBlackNode();
		RedBlackNode delect_1_Node=new RedBlackNode();
		RedBlackNode delect_2_Node=new RedBlackNode();
		int[] delect_data=new int[2];
		long[] time2 = new long[2];

		/*以上是创建随即数组文件并读入数据
		 */
		for(int index=0;index<n.length;index++) {
			int[] data = new int[n[index]];
			int time_array_size=(int)(Math.ceil(n[index]/10.0));
			int i=0;
			long[] time1 = new long[time_array_size];
			long startTime;
			long endTime;
			readrandomtxt.read(n[index],data);
			RedBlackTree T = new RedBlackTree();
			startTime=System.nanoTime(); 
			for(int number=0;number<n[index];number++) {
				RedBlackNode z = new RedBlackNode(data[number]);
				T.Insert(T, z);
				endTime=System.nanoTime();
				if((number+1)%10==0||number==n[index]-1) {
				time1[i++]=endTime-startTime;
				}
		}//for循环构建完成红黑树
			/*以上是插入操作*/
			T.Reset_RANK();
			T.Set_Rank(T,RedBlackTree.root);
			T.SetandGet_Size(T,RedBlackTree.root);
			T.output_txt(T,n[index],time1);

			if(T.OS_SELECT(RedBlackTree.root,n[index]/3).key==Array_Select.Select(data,0,data.length-1,n[index]/3)) {
				System.out.println("使用中位数选择算法Select检查 OS_Select算法的正确");
				System.out.println("检查n/3的结果");
				System.out.println("检查结果正确");
			}
			else {
				System.out.println("!!!!!!!!!!错误");
			}
			if(T.OS_SELECT(RedBlackTree.root,2*n[index]/3).key==Array_Select.Select(data,0,data.length-1,2*n[index]/3)) {
				System.out.println("检查2*n/3的结果");
				System.out.println("检查结果正确");
			}
			else {
				System.out.println("!!!!!!!!!!错误");
			}
			delect_1_Node=T.OS_SELECT(RedBlackTree.root,n[index]/4);
			delect_2_Node=T.OS_SELECT(RedBlackTree.root,n[index]/3);
			delect_data[0]=delect_1_Node.key;
			delect_data[1]=delect_2_Node.key;
			
			startTime=System.nanoTime(); 
			T.Delect(T,delect_1_Node);
			endTime=System.nanoTime();
			time2[0]=endTime-startTime;
			T.output_txt(T,n[index],delect_1_Node.key);
			
			startTime=System.nanoTime(); 
			T.Delect(T,delect_2_Node);
			endTime=System.nanoTime();
			time2[1]=endTime-startTime;
			T.output_txt(T,n[index],delect_2_Node.key);
	
			 
	 		T.output_txt(T,n[index],delect_data,time2);
			//System.out.println("");
			//System.out.println("");
			/*
			System.out.println("规模为"+n[index]+"的树");
			T.Print_Like_Tree(T);
			T.in_print_Tree(T, RedBlackTree.root);
			System.out.println("");
			T.Delect(T,T.OS_SELECT(RedBlackTree.root,6));
			T.Print_Like_Tree(T);
			T.in_print_Tree(T, RedBlackTree.root);
			System.out.println("");
			T.Delect(T,T.OS_SELECT(RedBlackTree.root,6));
			T.Print_Like_Tree(T);
			T.in_print_Tree(T, RedBlackTree.root);
			System.out.println("");
			*/
			//T.Print_Like_Tree_txt(T,n[index]);
		}//for index 
		
		
		
	}//main

}//class exp_3
