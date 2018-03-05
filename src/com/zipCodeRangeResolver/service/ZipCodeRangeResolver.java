package com.zipCodeRangeResolver.service;

import java.util.Map;

import com.zipCodeRangeResolver.exceptions.IllegalRangeInputException;

public interface ZipCodeRangeResolver {
	
	public  Map<Integer,Integer>  getInput() throws IllegalRangeInputException;
	
	}
