package com.zhangyin.saodi.senior;

import java.util.Set;
import java.util.stream.Collectors;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;

/**
 *  只有两度的节点一些操作
 *  
 * @author zhangyin
 *
 */
public class TwoDegreeOperation {
	
	//过滤只有2度的节点
	private  static Set<Node> filterTwoDegree(Set<Node> set){
		Set<Node> collect = set.stream().filter(n->n.canMoveDirection.keySet().size()==2).collect(Collectors.toSet());
		System.out.println("所有只有2度的节点总数为"+collect.size());
		return collect;
	}

	
	public static void isAccessPoint(Node n){
		Set<Direction> keySet = n.canMoveDirection.keySet();
		boolean pair = Direction.isPair(keySet);
		if(pair){
			n.isAccessPoint=true;
		}else{
			//Direction[] array = (Direction[]) keySet.toArray();
			Direction[] array=Direction.toArray(keySet);
			Direction   A=array[0];
			Direction   B=array[1];
			Node Anode = n.canMoveDirection.get(A);
			Node Bnode = n.canMoveDirection.get(B);
			if(Anode instanceof VirtualNode ||Bnode instanceof VirtualNode){
				return;
			}
			
			if(Anode.canMoveDirection.get(B)==null&&Bnode.canMoveDirection.get(A)==null){
				n.isAccessPoint=true;
				return ;
			}else if((Anode.canMoveDirection.get(B)!=null&&Bnode.canMoveDirection.get(A)!=null&&Anode.canMoveDirection.get(B) instanceof Node&&Anode.canMoveDirection.get(B)==Bnode.canMoveDirection.get(A))){
				n.isAccessPoint=false;
				return ;
			}else{
				System.out.println("Anode.canMoveDirection.get(B)="+Anode.canMoveDirection.get(B));
				System.out.println("Bnode.canMoveDirection.get(A)="+Bnode.canMoveDirection.get(A));
			}
			try {
				throw new IllegalAccessError("正常情况下，不会运行到这");
			} catch (IllegalAccessError e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Boolean TwoDegreeNodeisAccessPoint(Node n){
		Set<Direction> keySet = n.canMoveDirection.keySet();
		boolean pair = Direction.isPair(keySet);
		if(pair){
			return true;
		}else{
			//Direction[] array = (Direction[]) keySet.toArray();
			Direction[] array=Direction.toArray(keySet);
			Direction   A=array[0];
			Direction   B=array[1];
			Node Anode = n.canMoveDirection.get(A);
			Node Bnode = n.canMoveDirection.get(B);
//			if(Anode instanceof VirtualNode ||Bnode instanceof VirtualNode){
//				return;
//			}
			
			if(Anode.canMoveDirection.get(B)==null&&Bnode.canMoveDirection.get(A)==null){
				//n.isAccessPoint=true;
				return true;
			}else if((Anode.canMoveDirection.get(B)!=null&&Bnode.canMoveDirection.get(A)!=null&&Anode.canMoveDirection.get(B) instanceof Node&&Anode.canMoveDirection.get(B)==Bnode.canMoveDirection.get(A))){
				n.isAccessPoint=false;
				return false;
			}else{
				System.out.println("Anode.canMoveDirection.get(B)="+Anode.canMoveDirection.get(B));
				System.out.println("Bnode.canMoveDirection.get(A)="+Bnode.canMoveDirection.get(A));
			}
			try {
				throw new IllegalAccessError("正常情况下，不会运行到这");
			} catch (IllegalAccessError e) {
				e.printStackTrace();
			}
		}
		return pair;
	}

}
