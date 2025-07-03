package com.dating.Dating_Recomendation_Engine.util;

import java.util.Comparator;

import com.dating.Dating_Recomendation_Engine.dto.MatchingUser;

public class UserSorting implements Comparator<MatchingUser>{
	
	public int compare(MatchingUser o1,MatchingUser o2) {
		if(o1.getAgeDiff()<o2.getAgeDiff()) {
			return -1;//desc
		}
		else if(o1.getAgeDiff()>o2.getAgeDiff()){
			return 1;//asc
		}else if(o1.getAgeDiff()==o2.getAgeDiff()){
			if(o1.getMic()<o2.getMic()) {
				return 1;//asc
			}else if(o1.getMic()>o2.getMic()){
				return -1;//desc
			}
			
		}
		return 0;
	}
}
