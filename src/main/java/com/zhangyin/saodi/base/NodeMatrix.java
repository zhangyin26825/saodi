package com.zhangyin.saodi.base;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.zhangyin.saodi.second.NodeMatrixAnalysis;
import com.zhangyin.saodi.senior.ThreeDegreeOperation;
import com.zhangyin.saodi.senior.TwoDegreeOperation;
import com.zhangyin.saodi.ui.MyFrame;


public class NodeMatrix  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		initALLMoveDirection();
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
	
	
	public Set<Node> getTwoDegreeNodes() {
		Set<Node> set=new HashSet<Node>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { 
				Node n=nodes[i][j];
				if(n.isBlank&&n.degree==2){
					set.add(n);
				}
			}
		}
		return set;
	}
	
	public Set<Node> getThreeDegreeNodes(){
		Set<Node> set=new HashSet<Node>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { 
				Node n=nodes[i][j];
				if(n.isBlank&&n.degree==3){
					set.add(n);
				}
			}
		}
		return set;
	}
	
	public void initALLMoveDirection(){
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
				}
			}
		}
		//initAccessPoint();
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
	
	public void clear(){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) { 
				 Node n=nodes[i][j];
				 if(n.isBlank){
					n.ispassed=false;
			}
		}
	}
		repaint();
	}
	
	public static void main(String[] args) {		
		 int level=70; 
		 int x=26;
		 int y=27;
		 String map="110000001100000111000000000000100001001110111010001110010000011000000000010100110000011000110001000000000110111001000000101000011000000110001001000000011010001111100100000000010000000100000001100010100010110001110000000000000101000110100000100111000100000100110000001000000110001010000100000100000000001001000000100000010001001000001100001100001000000001000101111000001001100010000010000010011111011000000000000010000100011011000001111111000100001000011100000000010000001000011111101010000010000000010000000001000001111111000100101110000100001100100011101001000110001000100001111101000000011000010001111000100000001000010010011000010100110000000000011000010010100010110000000000010010010001000110000000";
		 NodeMatrix nm=new NodeMatrix(map, level, x, y);
		 NodeMatrixAnalysis nma=new NodeMatrixAnalysis(nm);
//		 Set<Node> initMoveDirection = nm.initMoveDirection();
//		 initMoveDirection.stream().forEach(m->System.out.println(m.canMoveDirection.keySet().size())); 
		 MyFrame my=new MyFrame(nm);
		 
	}
}
