package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/weblog/PVMinMax2.java

//����<String,String>(key,value)
//���ֵΪ���������Сֵ����valueֵ
//���ɽ���
public class Example37 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	int maxVisit = 0;
	int minVisit = Integer.MAX_VALUE;
	String maxMinute = null;
	String minMinute = null;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	for (Element val : list.getList()) {
			String[] strs = val.getList().get(1).toString().split("\t");
			String minute = strs[0];
			int visit = Integer.parseInt(strs[1]);
			if (visit > maxVisit) {
				maxVisit = visit;
				maxMinute = minute;
			}					
			if (visit < minVisit) {
				minVisit = visit;
				minMinute = minute;
			}
		}
		
		
		
		
		
		String value = maxMinute + " " + maxVisit + "\t" + minMinute + " " + minVisit;
		output.add(new TwoTuple(key, value));
		
    }



}
