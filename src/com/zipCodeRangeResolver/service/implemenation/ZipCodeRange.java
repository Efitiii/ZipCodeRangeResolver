package com.zipCodeRangeResolver.service.implemenation;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import com.zipCodeRangeResolver.exceptions.IllegalRangeInputException;
import com.zipCodeRangeResolver.exceptions.IllegalRangeMessages;
import com.zipCodeRangeResolver.service.ZipCodeRangeResolver;
import com.zipCodeRangeResolver.utility.RangeTreeMap;
import com.zipCodeRangeResolver.utility.Util;

//*********************** ZipCodeRange handles building of tree with incoming input and printing the results  of the tree **********//

public class ZipCodeRange implements ZipCodeRangeResolver {
	
	static Integer[] conciseRange;
	static RangeTreeMap rangeTreeMap;
	private static Boolean limitReached;
	Util instance;
	static RangeTreeMap currentRoot;
	static RangeTreeMap previousRoot;
	static RangeTreeMap leftMostRoot;
	static RangeTreeMap prevLeftMostRoot;
	static boolean firstReplacement;
	static boolean leftMostReachedAfterIteration;
	
	
	public ZipCodeRange(){
		instance=Util.getInstance();
		rangeTreeMap= new RangeTreeMap(0,0);
		
	}
	

/***getInput() handles the input from the user***/
	@SuppressWarnings("resource")
	public  Map<Integer,Integer>  getInput() throws IllegalRangeInputException{
		
		Scanner scan= new Scanner (System.in);
		Integer number= new Integer(1);
		Boolean rangeComplete=false;
		Boolean wrongInput=false;
		Integer lowerRange=null;
		Integer upperRange=null;
		String tempValue;
		Map<Integer,Integer> rangeMap= new HashMap<Integer,Integer>();
		
		while (!rangeComplete){
			wrongInput=false;
			
			System.out.println("Enter lower range for No."+number+" or type 'done' when finished.");
			tempValue= scan.nextLine().toLowerCase();
			if (!tempValue.equals("done")){
				
			try{	
			lowerRange= Integer.parseInt(tempValue);
			
			if (String.valueOf(lowerRange).length()!=5){
				System.out.println(IllegalRangeMessages.WRONG_ZIPCODE_INPUT.getMsg());
				wrongInput=true;
				
				
			}
			
			
			}
			catch(NumberFormatException e){
				System.out.println(IllegalRangeMessages.WRONG_INTGER_INPUT.getMsg());
				wrongInput=true;
			}
				
			}
			else {
				rangeComplete=true;
			}
			
			
			if (!tempValue.equals("done")){
				if (wrongInput==false){
				System.out.println("Enter upper range for No."+number);
				tempValue= scan.nextLine().toLowerCase();
				
				if (!tempValue.equals("done")){
					try{	
						upperRange= Integer.parseInt(tempValue);
						
						if (String.valueOf(upperRange).length()!=5){
							System.out.println(IllegalRangeMessages.WRONG_ZIPCODE_INPUT.getMsg());
							wrongInput=true;
							
						}
						}
						catch(NumberFormatException e){
							
							System.out.println(IllegalRangeMessages.WRONG_INTGER_INPUT.getMsg());
							
							wrongInput=true;
						}
				if (rangeComplete==false && wrongInput==false){
					if (upperRange>=lowerRange){
				buildOptimumRange(lowerRange,upperRange);
					}
					else {
						System.out.println(IllegalRangeMessages.UPPERRANGE_SMALLER_THAN_LOWERRANGE.getMsg());
						wrongInput=true;
					}
				}
				
				if (wrongInput==false){
				number++;
				}
				}
				else {
					rangeComplete=true;	
					System.out.println(IllegalRangeMessages.NO_OUTER_RANGE_INPUT.getMsg());
				}
				
				}
				
				
			} else {
				rangeComplete=true;
			}
			
			
				
		}
		
		if(rangeComplete==true){
			wrongInput=false;
			printRangesInOrder(rangeTreeMap);
		}
		
		return rangeMap;
		
	}
	

/************ buildOptimumRange() builds the tree by traversing right or left *********/
	
	public static Boolean buildOptimumRange(Integer lowerRange, Integer upperRange){
	
		conciseRange= new Integer[2];
		limitReached=false;
		
		
		if (rangeTreeMap.getRoot()[0]==0 && rangeTreeMap.getRoot()[1]==0){
			rangeTreeMap= new RangeTreeMap(lowerRange,upperRange);
		} 

		else  if (rangeTreeMap.getLowerRange()>=lowerRange){
			limitReached = traverseLeft(lowerRange,upperRange,rangeTreeMap,rangeTreeMap);
		}
		else  limitReached = traverseRight(lowerRange,upperRange,rangeTreeMap, rangeTreeMap);
	
        return limitReached;
		 
	}
	
 public static Boolean traverseLeft(Integer lowerRange,Integer upperRange,RangeTreeMap currentRoot2,RangeTreeMap previousRoot2){
		
		currentRoot=currentRoot2;
		previousRoot=previousRoot2;
		leftMostRoot= rangeTreeMap;
		prevLeftMostRoot=rangeTreeMap;
		firstReplacement= false;
		limitReached=false;
		leftMostReachedAfterIteration=false;
		
		while (currentRoot.getLowerRange() >=lowerRange && currentRoot.getLowerRange()<=upperRange && limitReached==false ){
			
			if (currentRoot.getUpperRange()<=upperRange && firstReplacement==false){
				currentRoot.getRoot()[0]=lowerRange;
				currentRoot.getRoot()[1]=upperRange;
				firstReplacement=true;
				if (currentRoot.getLeft()==null){
					limitReached=true;	
				}
				 
			}
			else if (currentRoot.getUpperRange()<=upperRange && firstReplacement==true){
				prevLeftMostRoot=previousRoot;
				leftMostRoot=currentRoot;
				leftMostReachedAfterIteration=false;
				while(leftMostRoot.getLeft()!=null){
					prevLeftMostRoot=leftMostRoot;
					leftMostRoot=leftMostRoot.getLeft();
					leftMostReachedAfterIteration=true;
					
				}
				if (leftMostRoot.getRight()!=null && leftMostReachedAfterIteration==true){
					currentRoot=leftMostRoot;
					prevLeftMostRoot.left=leftMostRoot.getRight();
				}
				else if (leftMostRoot.getRight()==null && leftMostReachedAfterIteration==true){
				currentRoot=leftMostRoot;
				leftMostRoot=null;
				firstReplacement=false;
				}
				else if (leftMostReachedAfterIteration==false){
					if (leftMostRoot.getRight()!=null){
						previousRoot.left=leftMostRoot.getRight();
						limitReached=true;
					}
					else {
						previousRoot.left=null;
						limitReached=true;
					}
					
				}
				
			}
			
			
			currentRoot.getRoot()[0]=lowerRange;
			upperRange=currentRoot.getUpperRange();
			
			

			if (currentRoot.getLeft()!=null && limitReached==false){
				previousRoot=currentRoot;
				currentRoot=currentRoot.getLeft();
				}
				else if (limitReached==false && firstReplacement==false){
					//currentRoot.left=new RangeTreeMap(lowerRange,upperRange);
					limitReached=true;
					
				}
			
			
		}
		
		
		if (limitReached==false && firstReplacement==false){
			limitReached=insertOutOfRangeZip(lowerRange,upperRange,currentRoot,previousRoot);
		}
		
		
		return limitReached;
		
	}
 
 public static Boolean traverseRight (Integer lowerRange,Integer upperRange,RangeTreeMap currentRoot,RangeTreeMap previousRoot){
	 boolean limitReached=false;
	 boolean firstTimeReplacement=false;
	 RangeTreeMap currentRoot2=currentRoot;
	 RangeTreeMap previousRoot2=previousRoot;
	 
	 
	 
	 while(currentRoot.getLowerRange()<=lowerRange && currentRoot.getUpperRange()>=lowerRange && limitReached==false){
		 
		 if (currentRoot.getUpperRange()<upperRange){

	        	if (currentRoot.right==null){
	        		currentRoot.getRoot()[1]=upperRange;
	        		limitReached=true;
	        	}
	        	else if (firstTimeReplacement==false){
	        		currentRoot.getRoot()[1]=upperRange;
	        		lowerRange=currentRoot.getRoot()[0];
	        		previousRoot=currentRoot;
	        		currentRoot=currentRoot.getRight();
	        		firstTimeReplacement=true;
	        	}	
	        	else{
	        		if (currentRoot.getRight()!=null){
	        		
	        		currentRoot2=currentRoot;
	        		previousRoot2=previousRoot;
	        		
	        		while (currentRoot2.getRight()!=null){
	        			currentRoot2=currentRoot.getRight();
		        		previousRoot2=currentRoot;
	        			
	        		}
	        		
	        		if (currentRoot2.getLeft()!=null){
	        			previousRoot2.right=currentRoot2.getLeft();
	        		}
	        		else {
	        			currentRoot=currentRoot2;
	        		}
	        		
	        	}
	        		
	        		else {
	        			currentRoot.right=null;
	        			limitReached=true;
	        		}
	        	}
	        	
			}
		 else {
			 limitReached=true;
					 }
		 
     	}
	 
	   

	   /************** Insertion for zipcode greater than current upper range ***************/
	   if (limitReached==false && firstTimeReplacement==false){
		   
		limitReached=insertOutOfRangeZip(lowerRange,upperRange,currentRoot,previousRoot);
	   }
	 
	 return limitReached;
 }
 
 
 public static Boolean insertOutOfRangeZip(Integer lowerRange,Integer upperRange,RangeTreeMap currentRoot,RangeTreeMap previousRoot){
	 
	 if (currentRoot.getLowerRange()<=lowerRange){
	 if (currentRoot.getRight()!=null){
		   previousRoot=currentRoot;
		   currentRoot=currentRoot.getRight();
		   
		   if (currentRoot.getLowerRange()<=lowerRange){
			   limitReached=traverseRight(lowerRange,upperRange,currentRoot,previousRoot);
		   }
		   else limitReached=traverseLeft(lowerRange,upperRange,currentRoot,previousRoot);
	   }
	   else {
		   currentRoot.right=new RangeTreeMap(lowerRange,upperRange);
		   limitReached=true;
	   }
	 }
	 
	 else{
		 if (currentRoot.getLeft()!=null){
			 previousRoot=currentRoot;
			 currentRoot=currentRoot.getLeft();
			 
			 if (currentRoot.getLowerRange()<=lowerRange){
				  limitReached=traverseRight(lowerRange,upperRange,currentRoot,previousRoot);
			   }
			   else limitReached=traverseLeft(lowerRange,upperRange,currentRoot,previousRoot);
		   }
		 else {
			 currentRoot.left=new RangeTreeMap(lowerRange,upperRange);
			 limitReached=true;
		 }
		 }
	 
	   
	   return limitReached;
 }
 
	
	public static Integer[] getConciseRange(){
		
		return conciseRange;
	}
	
	public static void printRangesInOrder(RangeTreeMap rangeTreeMap) throws IllegalRangeInputException{
		if (rangeTreeMap==null){
			throw new IllegalRangeInputException(IllegalRangeMessages.NO_DATA_IN_TREE.getMsg());
		}
		
		System.out.println("["+rangeTreeMap.getRoot()[0]+","+rangeTreeMap.getRoot()[1]+"]");
		
		if (rangeTreeMap.getLeft()==null && rangeTreeMap.getRight()==null){
			return ;
		}
		
		
		
		if (rangeTreeMap.getLeft()!=null){
			printRangesInOrder(rangeTreeMap.getLeft());
		}
		
		if (rangeTreeMap.getRight()!=null){
			printRangesInOrder(rangeTreeMap.getRight());
		}
		
	}
	
}
