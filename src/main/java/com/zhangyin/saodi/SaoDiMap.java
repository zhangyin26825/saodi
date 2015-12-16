package com.zhangyin.saodi;

public class SaoDiMap {
	
	
	private int level;
	
	private int row;
	
	private int col;
	
	private String map;
	
	

	public SaoDiMap(int level, int row, int col, String map) {
		super();
		this.level = level;
		this.row = row;
		this.col = col;
		this.map = map;
		
		if(map.length()!=col*row){
			throw new IllegalAccessError("不是合法的地图");
		}
	}

	public SaoDiMap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
	
	
	public static void main(String[] args) {
		int level=64;
		int row=24;
		int col=25;
		String map="100010000100000000000000000101010000100000001100000110001010000000000110100011000000000111110010000101100001100000000001010000011111110010001110000010100110001100010111100000011000010001111000010000110011000010001110000010001000000000010000000000010101000001100000010000000000001110000010101000000010100110000100000100011000000011010000000100100001000101101011100010000100111000000101100000000110011110000000000101100000011110000000100110000110000000010000000010001000011010001000000101010001000001010011000000100100001001101100000010001000001000110000001001110000100001000100000001111100000100111000";
		SaoDiMap sdm=new SaoDiMap(level, row, col, map); 
	}
	
	

}
