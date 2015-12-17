package com.zhangyin.saodi.base;

public enum Direction {
	 Up,Left,Down,Right;
	 
	 public String toString() {
		 if(this.equals(Up)){
			 return "u";
		 }
		 if(this.equals(Left)){
			 return "l";
		 }
		 if(this.equals(Down)){
			 return "d";
		 }
		 if(this.equals(Right)){
			 return "r";
		 }
		 throw new IllegalAccessError("不合法的方向类型");
		//return null;
	 };
}
