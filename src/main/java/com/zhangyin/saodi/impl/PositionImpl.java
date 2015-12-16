package com.zhangyin.saodi.impl;

import com.zhangyin.saodi.Position;

public class PositionImpl implements  Position{
	
	
	int row;
	
	int col;
	
	public PositionImpl(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	

}
