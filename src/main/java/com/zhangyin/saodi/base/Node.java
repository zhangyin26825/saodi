package com.zhangyin.saodi.base;

import java.util.HashMap;
import java.util.Map;

public class Node {
	
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
	
	void initdegree(){
		degree=canMoveDirection.keySet().size();
	}
	
	
}
