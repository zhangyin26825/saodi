package com.zhangyin.saodi.base;

import java.util.HashMap;
import java.util.Map;

public class Node {
	
	
	 public boolean isVirtualNode=false;
	
	 public int i;
	 
	 public int j;
	
	 boolean isBlank;
	
	 public Map<Direction,Node> canMoveDirection;
	 
	 public int degree;
	 
	 public boolean isAccessPoint=false;

	 public Node(boolean isBlank,int i,int j) {
		super();
		this.isBlank = isBlank;
		this.i=i;
		this.j=j;
		canMoveDirection=new HashMap<Direction, Node>(4);
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
