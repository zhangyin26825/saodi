package com.zhangyin.saodi;

import java.util.Set;
import java.util.TreeSet;

/**
 * 广义上的一个节点  
 * 从一个地方  进  从一个地方出   
 * 这两个地点可以是同一个节点
 * @author yin
 *
 */
public abstract class Coordinate {
	
	boolean ispassed;
	
	Set<Direction> hasused=new TreeSet<Direction>();
	
	Set<Position> positions=new TreeSet<Position>();

	public Set<Position> getPositions() {
		return positions;
	}
	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}
	public void AddDirection(Direction d){
		hasused.add(d);
	}

	public boolean isIspassed() {
		return ispassed;
	}

	public void setIspassed(boolean ispassed) {
		this.ispassed = ispassed;
	}

	public Set<Direction> getHasused() {
		return hasused;
	}

	public void setHasused(Set<Direction> hasused) {
		this.hasused = hasused;
	}
	
	

}
