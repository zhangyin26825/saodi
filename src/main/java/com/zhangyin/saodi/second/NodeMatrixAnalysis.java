package com.zhangyin.saodi.second;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;
import com.zhangyin.saodi.base.NodeMatrix;
import com.zhangyin.saodi.senior.AccessPointType;
import com.zhangyin.saodi.senior.TwoDegreeOperation;

public class NodeMatrixAnalysis {

	NodeMatrix nm;

	Set<Node> two;

	Set<Node> three;
	
	Set<Node> TwoAccessPoint;
	
	Set<PairAccessPoint>  pairAccessPoint;
	
	

	public NodeMatrixAnalysis(NodeMatrix nm) {
		super();
		this.nm = nm;
		two = nm.getTwoDegreeNodes();
		three = nm.getThreeDegreeNodes();
		System.out.println("只有两度的节点数量为" + two.size());
		System.out.println("只有三度的节点数量为" + three.size());
		initTwoDegreeisAccessPoint();
		initThreeDegreeisAccessPoint();
		System.out.println("两度的出入节点的数量为"+TwoAccessPoint.size());
		System.out.println("三度的出入节点的数量为"+pairAccessPoint.size());
	}

	public void initTwoDegreeisAccessPoint() {
		TwoAccessPoint=new HashSet<Node>();
		for (Iterator iterator = two.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			if (AroundHasNode(node)) {
				continue;
			}
			boolean istwoMutilnode = false;
			Direction[] array = Direction.toArray(node.canMoveDirection
					.keySet());
			Direction A = array[0];
			Direction B = array[1];
			if (node.canMoveDirection.get(B).degree == 2
					&& node.canMoveDirection.get(A).degree == 2) {
				continue;
			}
			if (node.canMoveDirection.get(A).canMoveDirection.keySet().size() > 2) {
				istwoMutilnode = true;
			}
			if (node.canMoveDirection.get(B).canMoveDirection.keySet().size() > 2) {
				istwoMutilnode = true;
			}
			boolean accessPoint = TwoDegreeOperation
					.TwoDegreeNodeisAccessPoint(node);
			
			if (accessPoint && istwoMutilnode) {
				node.isAccessPoint = true;
				node.accesspointtype=AccessPointType.TWO;
				TwoAccessPoint.add(node);
			}
		}

	}

	public void initThreeDegreeisAccessPoint() {
		pairAccessPoint=new HashSet<PairAccessPoint>();
		for (Iterator iterator = three.iterator(); iterator.hasNext();) {
			Node n = (Node) iterator.next();
			if (AroundHasNode(n)) {
				continue;
			}
			Set<Direction> keySet = n.canMoveDirection.keySet();
			for (Iterator iterator2 = keySet.iterator(); iterator2.hasNext();) {
				Direction direction = (Direction) iterator2.next();
				Node node = n.canMoveDirection.get(direction);
				if (node.canMoveDirection.keySet().size() == 3
						&& Direction.isThreeDegreeAccessPoint(n, node,direction)) {
					n.isAccessPoint = true;
					n.accesspointtype=AccessPointType.ThreetoThree;
					node.isAccessPoint=true;
					node.accesspointtype=AccessPointType.ThreetoThree;
					PairAccessPoint p=new PairAccessPoint(n, node);
					pairAccessPoint.add(p);
				}
			}
		}
		removePair();
	}
	
	public void removePair(){
		for (Iterator iterator = pairAccessPoint.iterator(); iterator.hasNext();) {
			PairAccessPoint pair = (PairAccessPoint) iterator.next();
			if(pair.hasCloseTwoAccessPoint()){
				pair.close();
				iterator.remove();
			}
		}
	}

	public boolean AroundHasNode(Node n) {
		if(n.isAccessPoint){
			return true;
		}
		Set<Direction> keySet = n.canMoveDirection.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Direction direction = (Direction) iterator.next();
			if (n.canMoveDirection.get(direction).isAccessPoint) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int level = 64;
		int x = 24;
		int y = 25;
		String map = "100010000100000000000000000101010000100000001100000110001010000000000110100011000000000111110010000101100001100000000001010000011111110010001110000010100110001100010111100000011000010001111000010000110011000010001110000010001000000000010000000000010101000001100000010000000000001110000010101000000010100110000100000100011000000011010000000100100001000101101011100010000100111000000101100000000110011110000000000101100000011110000000100110000110000000010000000010001000011010001000000101010001000001010011000000100100001001101100000010001000001000110000001001110000100001000100000001111100000100111000";
		NodeMatrix nm = new NodeMatrix(map, level, x, y);
		NodeMatrixAnalysis nma = new NodeMatrixAnalysis(nm);
	}
}
