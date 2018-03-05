package com.zipCodeRangeResolver.utility;


//********** This Util class is created if there is a need to use a singleton to access to access the RangeTreeMap ***************//

public class Util {

	private static Util instance;
	
	private RangeTreeMap rangeTreeMap= new RangeTreeMap(null,null);

	private Util() {

	}

	public static Util getInstance() {
		if (instance == null) {
			return instance = new Util();
		}
		return instance;
	}
	
	public RangeTreeMap getLeft() {
		return rangeTreeMap.getLeft();
	}

	public RangeTreeMap getRight() {
		return rangeTreeMap.getRight();
	}

	public  Integer[] getRoot() {
		return rangeTreeMap.getRoot();
	}
	
	public Integer getLowerRange() {
		return rangeTreeMap.getLowerRange();
	}
	
	public Integer getUpperRange() {
		return rangeTreeMap.getUpperRange();
	}
	

}
