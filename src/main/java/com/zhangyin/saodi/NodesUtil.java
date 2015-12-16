package com.zhangyin.saodi;

import java.util.Set;
import java.util.TreeSet;

public class NodesUtil {
	
	 int level;
	
	 int row;
	
	 int col;
	
	 String map;
	 
	 SingleNode [][] nodes;

	public NodesUtil(int level, int row, int col, String map) {
		super();
		this.level = level;
		this.row = row;
		this.col = col;
		this.map = map;
		if(map.length()!=col*row){
			throw new IllegalAccessError("不是合法的地图");
		}
		nodes=new SingleNode[row][col];
	}
	
	public Set<SingleNode> initNodes(){
		Set<SingleNode> set=new TreeSet<SingleNode>();
		int index=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				char charAt = map.charAt(index);
				SingleNode s=new SingleNode(i, j, charAt=='0'?true:false);
				nodes[i][j]=s;
				set.add(s);
				index++;
			}
		}	
		return set;
	}
	


}
