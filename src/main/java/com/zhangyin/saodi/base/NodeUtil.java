package com.zhangyin.saodi.base;

import java.util.Set;
import java.util.stream.Collectors;

public class NodeUtil {
	
	
	public Set<Node> filterTwoDegree(Set<Node> set){
		Set<Node> collect = set.stream().filter(n->n.canMoveDirection.keySet().size()==2).collect(Collectors.toSet());
		return collect;
	}

}
