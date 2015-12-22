package com.zhangyin.saodi.base;

import java.util.HashSet;
import java.util.Set;

import com.zhangyin.saodi.senior.ThreeDegreeOperation;
import com.zhangyin.saodi.senior.TwoDegreeOperation;
import com.zhangyin.saodi.senior.VirtualNodeUtil;


public class NodeMatrix {
	
	String map;
	Integer level;
	Integer row;
	Integer col;
	
	Node[][] nodes;
	
	public NodeMatrix(String map, Integer level, Integer row, Integer col) {
		super();
		this.map = map;
		this.level = level;
		this.row = row;
		this.col = col;
		assert map.length()==row*col;
		init();
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
		 int level=10; 
		 int x=6;
		 int y=7;
		 String map="000000000000000001100100110000010000001000";
		 NodeMatrix nm=new NodeMatrix(map, level, x, y);
		 Set<Node> initMoveDirection = nm.initMoveDirection();
		 initMoveDirection.stream().forEach(m->System.out.println(m.canMoveDirection.keySet().size()));
	}
}
