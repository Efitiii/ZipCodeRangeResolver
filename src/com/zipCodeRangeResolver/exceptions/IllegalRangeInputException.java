package com.zipCodeRangeResolver.exceptions;

//********* Custom created Exception Class to handle illegal range input **************//

public class IllegalRangeInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalRangeInputException (){
		super();
	}
	
	public IllegalRangeInputException (String msg){
		super ("IllegalInputException thrown:" + msg);
	}
	

}
