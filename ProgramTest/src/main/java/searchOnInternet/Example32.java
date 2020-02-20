package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;


//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mutualFriend/MergeFriendsReducer.java

//输入<String,String>(key,value)
//value值累加String输出
//不可交换（确定）
public class Example32 {
	
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

    	String friends = "";
		for (Element value : list.getList()) {
			friends += value.getList().get(1).toString()+",";
		}
		System.out.println(key.toString()+" "+friends);
		output.add(new TwoTuple(key, friends));
    }

}
