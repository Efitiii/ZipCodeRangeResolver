package com.zipCodeRangeResolver.utility;


//********* Custom created TreeMap to store the input zip code ranges ******************//
public class RangeTreeMap {
	
	public  RangeTreeMap left;
	public  RangeTreeMap right;
	private Integer lowerRange;
	private Integer upperRange;
	public  Integer[] root;
	
	public RangeTreeMap(Integer lowerRange, Integer upperRange){
		this.lowerRange=lowerRange;
		this.upperRange=upperRange;
		root = new Integer[]{lowerRange,upperRange};
	}

	public RangeTreeMap getLeft() {
		return left;
	}

	public RangeTreeMap getRight() {
		return right;
	}

	public  Integer[] getRoot() {
		return root;
	}
	
	public Integer getLowerRange() {
		return lowerRange;
	}
	
	public Integer getUpperRange() {
		return upperRange;
	}
	
	

}
