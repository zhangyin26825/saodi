package com.zhangyin.saodi.second;

import java.util.Map;
import java.util.Set;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;

public class Area {
	
	
	//所有的节点   包含三度的出入点
	Set<Node> nodes;
	
	//所有两度的出入点  方向代表出入点的方向
	Map<Direction,Node>  maps;
	

	
	


	public Area(Set<Node> nodes, Map<Direction, Node> maps, Set<Node> three) {
		super();
		this.nodes = nodes;
		this.maps = maps;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Map<Direction, Node> getMaps() {
		return maps;
	}

	public void setMaps(Map<Direction, Node> maps) {
		this.maps = maps;
	}	
}
