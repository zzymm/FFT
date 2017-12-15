package RedBlackTree;

public class RedBlackNode{
public int key;
public int size;
public int rank;
public RedBlackNode left;
public RedBlackNode right;
public RedBlackNode p;
String color;
public RedBlackNode() {
	this.key  = -9;
	this.left= null;
	this.right= null;
	this.p= null;
	this.color = "black";
	this.size=0;
	this.rank=0;
}
public RedBlackNode(int data) {
	this.key  = data;
	this.left= null;
	this.right= null;
	this.p= null;
	this.color = "red";
	this.size=0;
	this.rank=0;
}

}
