package com.zhangyin.saodi.base;

import java.util.HashMap;
import java.util.Map;

public class Node {
	
	 boolean isBlank;
	
	 Map<Direction,Node> canMoveDirection;

	public Node(boolean isBlank) {
		super();
		this.isBlank = isBlank;
		canMoveDirection=new HashMap<Direction, Node>(4);
	}

}
