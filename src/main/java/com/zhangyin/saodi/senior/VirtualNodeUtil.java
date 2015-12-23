package com.zhangyin.saodi.senior;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;
import com.zhangyin.saodi.temp.TempNode;

public class VirtualNodeUtil {
	
	public static List<VirtualNode> list=new LinkedList<VirtualNode>(); 
	
	
	public static Set<AccessPoint> set=new HashSet<AccessPoint>();
	
	
	public static void clear(){
		list.clear();
		set.clear();
	}
	
	
	private static void initAccessPoint(){
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			VirtualNode virtualNode = (VirtualNode) iterator.next();
			set.addAll(virtualNode.toAccessPointSet()) ;	
		}
	}
	
	public static List<Region>  generateRegion(){
		  List<Region> regionList=new LinkedList<Region>();
		  initAccessPoint();
		  while(set.size()!=0){
			  for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				  
				  AccessPoint accessPoint = (AccessPoint) iterator.next();
				  Set<AccessPoint> points=new HashSet<AccessPoint>();
				  Set<Node> nodes=new HashSet<Node>();
				  
				  TempNode temp=null;
				  Queue<TempNode> queue=new LinkedList<TempNode>();
				  TempNode s=new TempNode(accessPoint.n, accessPoint.d);
				  queue.offer(s);
				  
				  while((temp=queue.poll())!=null){
					  final TempNode tn=temp;
					  if(tn.n instanceof VirtualNode){
						  Optional<AccessPoint> findFirst = set.stream().filter(a->a.n==tn.n&&a.d==tn.d).findFirst();
						  if(findFirst.isPresent()){
							  AccessPoint accessPoint2 = findFirst.get();
							  points.add(accessPoint2);
							  nodes.add(accessPoint2.n);
							  set.remove(accessPoint2);
							  Node node = accessPoint2.n.canMoveDirection.get(accessPoint2.d);
							  if(!nodes.contains(node)){
								  queue.offer(new TempNode(node, Direction.getInverseDirection(accessPoint2.d)));
							  }
						  } 
					  }else if(tn.n instanceof Node){
						  nodes.add(tn.n);
						  Set<Direction> keySet = tn.n.canMoveDirection.keySet();
						  for (Iterator iterator2 = keySet.iterator(); iterator2
								.hasNext();) {
							Direction direction = (Direction) iterator2.next();
							Node node = tn.n.canMoveDirection.get(direction);
							  if(!nodes.contains(node)){
								  queue.offer(new TempNode(node, Direction.getInverseDirection(direction)));
							  }  
						}
					 
					  
					  }
				  }  
				  Region r=new Region(points, nodes);
				  regionList.add(r);
				  break;
			  }
		  }
		  return regionList;
	}
	
}
