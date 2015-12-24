package com.zhangyin.saodi.second;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.zhangyin.saodi.base.Node;

public class PairAccessPoint {
	
	 Node A;
	 Node B;
	public PairAccessPoint(Node a, Node b) {
		super();
		initAB(a,b);
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
	 
	
	public Boolean hasCloseTwoAccessPoint(){
		List<Node> list=new LinkedList<Node>();
		Collection<Node> avalues = A.canMoveDirection.values();
		Collection<Node> bvalues = B.canMoveDirection.values();
		list.addAll(avalues);
		list.addAll(bvalues);
		list.remove(A);
		list.remove(B);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			if(node.degree==2&&node.isAccessPoint){
				return true;
			}
		}
		return false;
	}
	public void close() {
		A.isAccessPoint=false;
		A.accesspointtype=null;
		B.isAccessPoint=false;
		B.accesspointtype=null;
	}
}
