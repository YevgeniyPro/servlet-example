package ch.ingredientmatching.matcher;

import java.util.Comparator;

/**
 * orderes by descending quality, second priority ascending graphDistance, third priority first occurrence of in the original input
 * @author nicu
 *
 */
public class MatchWordComparator implements Comparator<MatchWord>{
	@Override
	public int compare(MatchWord arg0, MatchWord arg1) {
		double res = 0;
		res = arg1.getQuality() - arg0.getQuality();

		if(res > 0){
			return 1;
		}else if(res < 0){
			return -1;
		}else{
			return 1;
		}
	}
}
