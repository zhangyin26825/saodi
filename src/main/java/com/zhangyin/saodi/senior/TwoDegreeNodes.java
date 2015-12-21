package com.zhangyin.saodi.senior;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;
import com.zhangyin.saodi.base.NodeMatrix;

public class TwoDegreeNodes {
	
	
	public  static Set<Node> filterTwoDegree(Set<Node> set){
		Set<Node> collect = set.stream().filter(n->n.canMoveDirection.keySet().size()==2).collect(Collectors.toSet());
		System.out.println("所有只有2度的节点总数为"+collect.size());
		return collect;
	}


	
	public TwoDegreeNodes(Set<Node> set) {
		 // twoDegree=filterTwoDegree(set);
		 //initGroup(twoDegree);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Set<Node>> initGroup(Set<Node>  twoDegree){
		List<Set<Node>> result=new LinkedList<Set<Node>>();
		while(twoDegree.size()!=0){
			for (Iterator iterator = twoDegree.iterator(); iterator.hasNext();) {
				Set<Node> set=new HashSet<Node>();
				Node node = (Node) iterator.next();
				Queue<Node> queue=new LinkedList<Node>();
				queue.offer(node);
				Node temp;
				while ((temp=queue.poll())!=null) {
					
					set.add(temp);
					twoDegree.remove(temp);
					
					Set<Direction> keySet = temp.canMoveDirection.keySet();
					for (Iterator iterator2 = keySet.iterator(); iterator2
							.hasNext();) {
						Direction direction = (Direction) iterator2.next();
						Node node2 = temp.canMoveDirection.get(direction);
						if(node2.degree==2&&!set.contains(node2)){
							queue.offer(node2);
						}
					}	
				}	
				result.add(set);
				break;
			}
		}
		return result;
	}
	
	
	public static void initTwoDegreeAccessPoint(Set<Node> set){
		Set<Node> filterTwoDegree = filterTwoDegree(set);
		List<Set<Node>> collect = initGroup(filterTwoDegree);
		//List<Set<Node>> collect = initGroup.stream().filter(s->s.size()>=3).collect(Collectors.toList());
		for (Iterator iterator = collect.iterator(); iterator.hasNext();) {
			Set<Node> set2 = (Set<Node>) iterator.next();
			for (Iterator iterator2 = set2.iterator(); iterator2.hasNext();) {
				Node node = (Node) iterator2.next();
				Direction[]  array = (Direction[]) node.canMoveDirection.keySet().toArray();
				Direction   A=array[0];
				Direction   B=array[1];
				if(node.canMoveDirection.get(B).canMoveDirection.keySet().size()==2&&
				   node.canMoveDirection.get(A).canMoveDirection.keySet().size()==2){
					node.isAccessPoint=false;
				}
				if(node.canMoveDirection.get(A).canMoveDirection.keySet().size()==3){
					new VirtualNode(node, node.canMoveDirection.get(A));
				}
				if(node.canMoveDirection.get(B).canMoveDirection.keySet().size()==3){
					new VirtualNode(node, node.canMoveDirection.get(B));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		 int level=64; 
		 int x=24;
		 int y=25;
		 String map="100010000100000000000000000101010000100000001100000110001010000000000110100011000000000111110010000101100001100000000001010000011111110010001110000010100110001100010111100000011000010001111000010000110011000010001110000010001000000000010000000000010101000001100000010000000000001110000010101000000010100110000100000100011000000011010000000100100001000101101011100010000100111000000101100000000110011110000000000101100000011110000000100110000110000000010000000010001000011010001000000101010001000001010011000000100100001001101100000010001000001000110000001001110000100001000100000001111100000100111000";
		 NodeMatrix nm=new NodeMatrix(map, level, x, y);
		 Set<Node> initMoveDirection = nm.initMoveDirection();
		 System.out.println("所有节点总数为"+initMoveDirection.size());
		 long count = initMoveDirection.stream().filter(n->n.isAccessPoint).count();
		 System.out.println("根据节点的isAccessPoint属性判断虚拟节点的数量为"+count);
		 System.out.println("根据VirtualNodeUtil的长度属性判断虚拟节点的数量为"+VirtualNodeUtil.list.size());
		 VirtualNodeUtil.list.stream().forEach(v->{
			 System.out.println(v.A.i+","+v.A.j+"     "+v.B.i+","+v.B.j);
		 });
//		 TwoDegreeNodes tdn=new TwoDegreeNodes(initMoveDirection);
		 //List<Set<Node>> initGroup = tdn.initGroup();
//		 System.out.println("2度分组后的节点集合总数为"+initGroup.size());
//		 initGroup.stream().forEach(s->System.out.println(s.size()));
	}

}
