package AnalysisProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import reduceExample.ElemwntList;

public class CreateReducedCases {           //??????????????????
	
	private List<ElemwntList> list = new ArrayList<ElemwntList>() ;
	
	public static void main(String[] args) {
		int type;
		String filename = "";
		List<List<Integer>> position_list = new ArrayList<List<Integer>>();
		List<List<String>> value_list = new ArrayList<List<String>>();
		Scanner scan = new Scanner(System.in);
		//System.out.println("?????????????????????");
		filename = scan.next();
		while(true) {
			//System.out.println("?????????????(1?????λ??????2?????????):");
			type = scan.nextInt();
			while(type!=1&&type!=2) {
				//System.out.println("???????????????????");
				type = scan.nextInt();
			}
			if(type == 1) {
				List<Integer> position = new ArrayList<Integer>(); 
				int position_num;
				//System.out.println("??????λ?????(????100????)??");
				while(true) {
					position_num = scan.nextInt();
					if(position_num==100) {
						//System.out.println("????????????");
						break;
					}else {
						position.add(position_num);
					}
					
				}
				position_list.add(position);
			}else if(type == 2) {
				List<String> value = new ArrayList<String>(); 
				String value_num;
				//System.out.println("??????λ?????(????done????)??");
				while(true) {
					value_num = scan.next();
					if(value_num=="done") {
						//System.out.println("????????????");
						break;
					}else {
						value.add(value_num);
					}					
				}
				value_list.add(value);
			}
			//System.out.println("????????????y/n??");
			if(scan.next()=="n") {
				break;
			}
		}
		
		
		
		
		
	}
	
	

}
