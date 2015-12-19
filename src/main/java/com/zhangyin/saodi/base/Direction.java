package com.zhangyin.saodi.base;

import java.util.Set;

public enum Direction {
	Up, Left, Down, Right;

	public String toString() {
		if (this.equals(Up)) {
			return "u";
		}
		if (this.equals(Left)) {
			return "l";
		}
		if (this.equals(Down)) {
			return "d";
		}
		if (this.equals(Right)) {
			return "r";
		}
		throw new IllegalAccessError("不合法的方向类型");
		// return null;
	};

	public static Direction getInverseDirection(Direction d) {
		switch (d) {
		case Up:
				return Down;
		case Down:
				return Up;
		case Left:
				return Right;
		case Right:
				return Left;
		default:
			throw new IllegalAccessError("不合法的方向类型");
		}
	}
	

	 //判断方向 是不是一对  比如   上下是一对  左右是一对
	 public static  boolean isPair(Set<Direction> set){
		 if(set.size()!=2){
			 throw new IllegalAccessError("不合适 集合类型，数量应当为2,才能判断方向是不是相对"); 
		 }
		 if(set.contains(Direction.Down)&&set.contains(Direction.Up)){
			 return true;
		 }
		 if(set.contains(Direction.Left)&&set.contains(Direction.Right)){
			 return true;
		 }
		 return false;
	 }

}
