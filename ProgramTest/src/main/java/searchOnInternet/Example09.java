package searchOnInternet;


import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;
//输入<String,int>(key,value)     (其中key为名字ID+“:”+名字)     reduce函数中key相同，故只有一个人的
//计算人物对应的分数和总分数，使用了String的加法
//可交换(确定)

public class Example09 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	
	String userId = "";
	String itermScore = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);


		        Double totalScore = 0.0;
		        for (Element value : list.getList()) {
//		        	System.out.println(value.getList().get(1));
		            totalScore +=Integer.parseInt((String) value.getList().get(1)) ;
		        }
		        String[] strArr = key.toString().split(":");
//		        System.out.println(strArr[0]);
		        userId=(strArr[0]);
		        itermScore=(strArr[1] + ":" + totalScore);
		        output.add(new TwoTuple(userId, itermScore));
		    
	}


}
