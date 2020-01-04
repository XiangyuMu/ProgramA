package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import reduceExample.Element;
import reduceExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/gradesAverage/GradesAverage.java

//输入<String,int>(key,value)
//输出值为平均值
//可交换（确定）
public class Example30 {
	
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

    	
    	
    	String key = (String)list.getList().get(0).getList().get(0);

    	int sum = 0;
		int grades = 0;
		for (Element val : list.getList()) {
			sum += 1;
			grades += Integer.parseInt((String) val.getList().get(1));
		}
		System.out.println("Reduce----student is:"+key.toString()+",grades is:"+grades+",sum is:"+sum);
		gradesSum=(float)grades/sum;
		output.add(new TwoTuple(key, Float.toString(gradesSum)));
    }

}
