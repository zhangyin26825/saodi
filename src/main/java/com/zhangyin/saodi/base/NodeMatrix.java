package com.zhangyin.saodi.base;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.zhangyin.saodi.senior.ThreeDegreeOperation;
import com.zhangyin.saodi.senior.TwoDegreeOperation;
import com.zhangyin.saodi.ui.MyFrame;


public class NodeMatrix  extends JPanel{
	
	String map;
	Integer level;
	public Integer row;
	public Integer col;
	
	public Node[][] nodes;
	
	public NodeMatrix(String map, Integer level, Integer row, Integer col) {
		super();
		this.map = map;
		this.level = level;
		this.row = row;
		this.col = col;
		assert map.length()==row*col;
		init();
		SwingInit();	
	}
	
	public void SwingInit(){
		GridLayout gridLayout = new GridLayout(row, col,1,1);
		this.setLayout(gridLayout);
		//this.setSize(col*MyFrame.widthandHeight, row*MyFrame.widthandHeight);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { 
				add(nodes[i][j]);
			}
		}
		
	}
	
	//节点数组初始化
	private void init(){
		nodes=new Node[row][col];
		assert map.length()==row*col;
		int count=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(map.charAt(count)=='0'){
					Node n=new Node(true,i,j);
					nodes[i][j]=n;
				}else if(map.charAt(count)=='1'){
					Node n=new Node(false,i,j);	
					nodes[i][j]=n;
				}
				count++;
			}	
		}	
		initMoveDirection();
	}
	//初始化节点之间的关系
	public Set<Node> initMoveDirection(){
		Set<Node> set=new HashSet<Node>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Node n=nodes[i][j];
				if(n.isBlank){
					 Node up = getUp(i, j);
					 if(up!=null&&up.isBlank){
						 n.canMoveDirection.put(Direction.Up, up);
					 }
					 
					 Node down = getDown(i, j);
					 if(down!=null&&down.isBlank){
						 n.canMoveDirection.put(Direction.Down, down);
					 }
					 
					 Node left = getLeft(i, j);
					 if(left!=null&&left.isBlank){
						 n.canMoveDirection.put(Direction.Left, left);
					 }
					 
					 Node right = getRight(i, j);
					 if(right!=null&&right.isBlank){
						 n.canMoveDirection.put(Direction.Right, right);
					 }
					 n.initdegree();
					 set.add(n);
				}
			}
		}
		initAccessPoint();
		return set;
	}
	//初始化节点是不是作为一个区域的出入点
	public void initAccessPoint(){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Node n=nodes[i][j];
				if(n.isBlank&&n.degree==2){
					TwoDegreeOperation.isAccessPoint(n);
					continue;
				}
				if(n.isBlank&&n.degree==3){
					ThreeDegreeOperation.isAccessPoint(n);
					continue;
				}	
			}
		}
	}
	
	private boolean isOutOfRange(int i,int j){
		if(i>=0&&i<row&&j>=0&&j<col){
			return false;
		}
		return true;
	}
	
	private  Node getUp(int i,int j){
		if(isOutOfRange(i-1, j)){
			return null;
		}else{
			return nodes[i-1][j];
		}	
	}
	
	private  Node getDown(int i,int j){
		if(isOutOfRange(i+1, j)){
			return null;
		}else{
			return nodes[i+1][j];
		}	
	}
	
	private  Node getLeft(int i,int j){
		if(isOutOfRange(i, j-1)){
			return null;
		}else{
			return nodes[i][j-1];
		}	
	}
	
	private  Node getRight(int i,int j){
		if(isOutOfRange(i, j+1)){
			return null;
		}else{
			return nodes[i][j+1];
		}	
	}
	
	public static void main(String[] args) {		
		 int level=64; 
		 int x=24;
		 int y=25;
		 String map="100010000100000000000000000101010000100000001100000110001010000000000110100011000000000111110010000101100001100000000001010000011111110010001110000010100110001100010111100000011000010001111000010000110011000010001110000010001000000000010000000000010101000001100000010000000000001110000010101000000010100110000100000100011000000011010000000100100001000101101011100010000100111000000101100000000110011110000000000101100000011110000000100110000110000000010000000010001000011010001000000101010001000001010011000000100100001001101100000010001000001000110000001001110000100001000100000001111100000100111000";
		 NodeMatrix nm=new NodeMatrix(map, level, x, y);
//		 Set<Node> initMoveDirection = nm.initMoveDirection();
//		 initMoveDirection.stream().forEach(m->System.out.println(m.canMoveDirection.keySet().size())); 
		 MyFrame my=new MyFrame(nm);
		 
	}
}
