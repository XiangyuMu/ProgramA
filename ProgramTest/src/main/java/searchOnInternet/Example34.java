package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/shuffleTest/TempSort.java

//输入<String,int>(key,value)
//value最大值
//可交换(可交换)
public class Example34 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();

    	int  maxTemp = Integer.MIN_VALUE;
		for(Element value : list.getList()) {
			System.out.println("年："+key+", 气温："+value);
			if (Integer.parseInt((String) value.getList().get(1).getAtom())>maxTemp) {
				maxTemp = Integer.parseInt((String) value.getList().get(1).getAtom());
			}
		}
		System.out.println("Date:"+key+", MaxTemp:"+maxTemp);
		output.add(new TwoTuple(key, Integer.toString(maxTemp)));
    }

}
