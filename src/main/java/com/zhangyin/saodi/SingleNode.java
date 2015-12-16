package com.zhangyin.saodi;

import com.zhangyin.saodi.impl.PositionImpl;

public  class SingleNode extends Coordinate{
	
	int row;
	int col;
	
	boolean isblank;
	
	Position p;

	public SingleNode(int row, int col,Boolean isBlank) {
		super();
		this.row = row;
		this.col = col;
		this.isblank=isBlank;
		p=new PositionImpl(row, col);
		this.positions.add(p);
	}

	


}
