package RedBlackTree;

public class Array_Select {
public static int Partition(int[] A,int p,int r){
int i;
int temp;
i=p-1;
for(int j=p;j<r;j++) {
	if(A[j]<=A[r]) {
		i++;
		temp=A[i];
		A[i]=A[j];
		A[j]=temp;
	}
}
temp=A[i+1];
A[i+1]=A[r];
A[r]=temp;
return i+1;
}//partition函数取末尾元素将数组中的元素重新分配，左边的小于选中元素，右边的大于元素，返回中间值的角标


public static int Select(int[] A,int p,int r,int i) {
	if(p==r) {
		return A[p];
	}
	else {
		int q=Partition(A,p,r);
		int k=q-p+1;
		if(i==k) {
			return A[q];
		}
		else if(i<k) {
			return Select(A,p,q-1,i);
		}
		else {
			return Select(A,q+1,r,i-k);
		}
	}
}//选择算法通过递归计算出数组中第i大元素
}
