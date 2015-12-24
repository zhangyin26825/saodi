package com.zhangyin.saodi.senior;

import java.util.Iterator;
import java.util.Set;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;

public class ThreeDegreeOperation {
	
	//判断一个3度的节点  满不满足作为isAccessPoint的条件
	public static void isAccessPoint(Node n){
		Set<Direction> keySet = n.canMoveDirection.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Direction direction = (Direction) iterator.next();
			Node node = n.canMoveDirection.get(direction);
			if(node.canMoveDirection.keySet().size()==3&&Direction.isThreeDegreeAccessPoint(n,node,direction)){
				new VirtualNode(n, node);
			}
		}
	}

}
