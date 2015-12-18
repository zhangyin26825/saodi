package com.zhangyin.saodi.senior;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;

public class Point {
	
	Node n;
	
	Direction d;

	public Point(Node n, Direction d) {
		super();
		if(!n.canMoveDirection.containsKey(d)){
			throw new IllegalStateException("Point 初始化失败，不存在向这个方向的移动");
		}
		this.n = n;
		this.d = d;
	}

	
	
	
	


	



	

}
