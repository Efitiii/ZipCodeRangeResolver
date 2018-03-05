package com.zipCodeRangeResolver.test;

import org.testng.annotations.Test;

import com.zipCodeRangeResolver.exceptions.IllegalRangeInputException;
import com.zipCodeRangeResolver.service.ZipCodeRangeResolver;
import com.zipCodeRangeResolver.service.implemenation.ZipCodeRange;

import junit.framework.TestCase;

public class ZipCodeRangeTest extends TestCase {
	
	@Test
	public void testzipCodeRange(){
		
		ZipCodeRangeResolver zipCodeRange = new ZipCodeRange();
		
		try{
		     zipCodeRange.getInput();		
			
			}
			catch(IllegalRangeInputException e){
				System.out.println(e.getMessage());
			}
			
		
	}
	

}
