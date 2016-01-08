package com.zhangyin.saodi.second;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.zhangyin.saodi.base.Node;

public class AreaUtil {
	
	
	public static Set<Area> generateArea(Set<Node> nodes){
		Set<Area> ares=new TreeSet<>();
		while(!nodes.isEmpty()){

			Node one = getOne(nodes);
			Set<Node> set=new HashSet<>();
			Queue<Node> q=new LinkedList<Node>();
			if(!one.isAccessPoint){
				q.add(one);
				set.add(one);
			}else{
				
			}
			
			
		}
		return null;	
	}
	
	private static Node getOne(Set<Node> nodes){
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			return node;
		}
		return null;
	}

}
