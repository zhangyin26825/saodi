package com.zhangyin.saodi.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.zhangyin.saodi.ui.MyFrame;

public class Node  extends JPanel{
	
	
	 public boolean isVirtualNode=false;
	
	 public int i;
	 
	 public int j;
	
	public  boolean isBlank;
	
	 public Map<Direction,Node> canMoveDirection;
	 
	 public int degree;
	 
	 public boolean isAccessPoint=false;
	 
	 public boolean ispassed=false;

	 public Node(boolean isBlank,int i,int j) {
		super();
		this.isBlank = isBlank;
		this.i=i;
		this.j=j;
		canMoveDirection=new HashMap<Direction, Node>(4);
		this.setSize(MyFrame.widthandHeight, MyFrame.widthandHeight);
	}
	 @Override
	public void paint(Graphics g) {
		super.paint(g);
		if(isBlank){
			this.setBackground(Color.WHITE);
		}else{
			this.setBackground(Color.BLACK);
		}
		if(ispassed){
			this.setBackground(Color.green);
		}
	}
	 
	 
	
	public Node() {
		super();
		this.isBlank = true;
		canMoveDirection=new HashMap<Direction, Node>(4);
	}



	protected void initdegree(){
		degree=canMoveDirection.keySet().size();
	}
	
	
}
