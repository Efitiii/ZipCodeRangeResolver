package com.zipCodeRangeResolver.exceptions;

//********* This class is a central repository for warning messages **************//

public enum IllegalRangeMessages {
	
	WRONG_INTGER_INPUT ("Illegal input. Please change your input to integer values only and input again."),
	NO_OUTER_RANGE_INPUT("Illegal range, you provide lower range but not upper range. Please enter your input again"),
	WRONG_ZIPCODE_INPUT("Zip code should be five digits. Please enter your input again"),
	NO_DATA_IN_TREE("The input has no range entered, please input a range. Please enter your input again"),
	UPPERRANGE_SMALLER_THAN_LOWERRANGE("Upper range is smaller than lower range. Please enter your input again");
	
	private final String msg;
	
	private IllegalRangeMessages(String msg){
		this.msg=msg;
	}
	
	public String getMsg(){
		return msg;
	}

}
