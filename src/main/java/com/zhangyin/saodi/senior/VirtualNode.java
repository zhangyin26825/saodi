package com.zhangyin.saodi.senior;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;

public class VirtualNode  extends Node{
	
	
	boolean isVirtualNode=true;
	
	
	Boolean isNecessary;

	 Node A;
	 Node B;
	 
	public VirtualNode(Node a, Node b) {
		super();
		initAB(a,b);
		if(a.degree+b.degree==5){
			isNecessary=true;  //2度到3度的节点是一定要经过的
			initDegree(a, b);
			VirtualNodeUtil.list.add(this);
			return;
		}
		if(a.degree+b.degree==6){
			isNecessary=false; //3度到3度的节点是不一定要经过的
			initDegree(a, b);
			VirtualNodeUtil.list.add(this);
			return;
		}
		throw new IllegalAccessError("生成一个虚拟节点，只有2度和3度 ，或者3度和3度的才能 结合出一个虚拟节点");
	}
	 //生成AB的节点   A为数据小的节点  B为数据大的节点
	private void initAB(Node a, Node b) {
		//纵向
		if(a.i==b.i){
			if(a.j<b.j){
				A=a;
				B=b;
			}else{
				A=b;
				B=a;
			}
		}//横向
		else if(a.j==b.j){
			if(a.i<b.i){
				A=a;
				B=b;
			}else{
				A=b;
				B=a;
			}
		}

	}
	 
	@SuppressWarnings("rawtypes")
	private void initDegree(Node a, Node b){
		Direction atobDirection=null;
		Set<Direction> keySet = a.canMoveDirection.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Direction direction = (Direction) iterator.next();
			Node node = a.canMoveDirection.get(direction);
			if(node==b){
				atobDirection=direction;
				break;
			}
		}
		Direction btoaDirection=Direction.getInverseDirection(atobDirection);
		if(a.canMoveDirection.get(atobDirection)==b&&b.canMoveDirection.get(btoaDirection)==a){
			
			super.canMoveDirection.put(atobDirection, b);
			super.canMoveDirection.put(btoaDirection, a);
			a.canMoveDirection.put(atobDirection, this);
			b.canMoveDirection.put(btoaDirection, this);
			initdegree();
			return;
		}
		throw new IllegalAccessError("两个相互连接的节点才能生成一个虚拟节点");
	}
	 
	public Set<AccessPoint> toAccessPointSet(){
		Set<AccessPoint> set=new HashSet<AccessPoint>();
		Set<Direction> keySet = this.canMoveDirection.keySet();
		assert keySet.size()==2;
		AccessPoint A = null,B=null;
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Direction direction = (Direction) iterator.next();
			AccessPoint a=new AccessPoint(this, direction, null);
			if(A==null){
				A=a;
				continue;
			}
			if(B==null){
				B=a;
				continue;
			}
		}
		A.setPaired(B);
		B.setPaired(A);
		set.add(A);
		set.add(B);
		return set;
	}

}
