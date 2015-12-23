package com.zhangyin.saodi.senior;

import com.zhangyin.saodi.base.Direction;

//一个区域的 出入点
public class AccessPoint {
	
	VirtualNode n;
	
	Direction d;
	
	
	//结成一对的AccessPoint
	AccessPoint paired;

	public AccessPoint(VirtualNode n, Direction d,AccessPoint paired) {
		super();
		if(!n.canMoveDirection.containsKey(d)){
			throw new IllegalStateException("Point 初始化失败，不存在向这个方向的移动");
		}
		this.n = n;
		this.d = d;
		this.paired=paired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		//result = prime * result + ((paired == null) ? 0 : paired.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessPoint other = (AccessPoint) obj;
		if (d != other.d)
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		if (paired == null) {
			if (other.paired != null)
				return false;
		} else if (!paired.equals(other.paired))
			return false;
		return true;
	}

	public AccessPoint getPaired() {
		return paired;
	}

	public void setPaired(AccessPoint paired) {
		this.paired = paired;
	}
	
	
	
	

	
	
	
	


	



	

}
