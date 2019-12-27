package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/ssdut/training/mapreduce/peoplerank/PeopleRank2.java


public class Example35 {
	public static enum Mycounter {
		my
	}
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	float gradesSum;
    public void reduce(ElemwntList list) throws IOException {

    	String key = (String)list.getList().get(0).getList().get(0);

    	double sum = 0.0;
		People sourcePeople = null;
		for (Element v : list.getList()) {
			People people = People.fromMR(v.getList().get(1).toString());
			if (people.containsAttentionPeoples()) {
				sourcePeople = people;
			} else {
				sum += people.getPeopleRank();				//?????????????PR????
			}
		}
		double newPR = (1 - 0.85) / 4 + (0.85 * sum);		//???????PR? = (1-d)/N + d* sum????????????PR?/????????????????  (????????0.85)
		//double d = newPR - sourcePeople.getPeopleRank();	//???????PR???
		//int j = Math.abs( (int)(d*100.0) );					//???????????100?????????
		//context.getCounter(Mycounter.my).increment(j);		//?????????
		sourcePeople.setPeopleRank(newPR);					//????PR?			
		output.add(new TwoTuple(key, sourcePeople.toString()));	//????????"userid  ??PR?  userlist"
    }
    public static class People {
    	private double peopleRank = 1.0;		//?›¥ PR?????????1.0
    	private String[] attentionPeoples;		//???????
    	public static final char fieldSeparator = '\t';	//????¡Â????\t???????????

    	public double getPeopleRank() {
    		return peopleRank;
    	}

    	public People setPeopleRank(double pageRank) {
    		this.peopleRank = pageRank;
    		return this;
    	}

    	public String[] getAttentionPeoples() {
    		return attentionPeoples;
    	}

    	public People setAttentionPeoples(String[] attentionPeoples) {
    		this.attentionPeoples = attentionPeoples;
    		return this;
    	}

    	//?§Ø?????????????
    	public boolean containsAttentionPeoples() {
    		return attentionPeoples != null && attentionPeoples.length > 0;
    	}

    	@Override
    	//People????????????
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		sb.append(peopleRank);
    		if (attentionPeoples != null) {
    			sb.append(fieldSeparator).append(StringUtils.join(attentionPeoples, fieldSeparator));
    		}
    		return sb.toString();	//????String?????"PeopleRand?	u1	u2..."
    	}
    	
    	//????????People????
    	public static People fromMR(String str) throws IOException {	//????String?????"PeopleRand?	u1	u2..."
    		People people = new People();
    		String[] strs = StringUtils.splitPreserveAllTokens(str, fieldSeparator);	//???????????????????????????
    		people.setPeopleRank(Double.valueOf(strs[0]));	//???????????
    		if (strs.length > 1) {// ???¨´?????????strs?¡À??1??¦Ë???????????????????"1.0 b c d"?????????
    			people.setAttentionPeoples(Arrays.copyOfRange(strs, 1, strs.length));	//???????????
    		}
    		return people;	//????People????
    	}
    }
}
