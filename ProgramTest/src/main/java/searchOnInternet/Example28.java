package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/InputOutputFormatTest/MultiInOutput.java

//输入<String,int>(key,value)
//输出值为累加，再根据key值进行分别输出
//可交换
public class Example28 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();

    	int sum = 0;
		for (Element value : list.getList()) {
			sum += (Integer)value.getList().get(1).getAtom();
		}
		// 使用MultiOutputs对象替代Context对象输出
		// 1. 输出到不同文件（格式、文件名）
		if (key.toString().startsWith("2015"))
			output.add(new TwoTuple( key, "f2015 "+sum));
		else if (key.toString().startsWith("2016"))
			output.add(new TwoTuple( key, "f2016 "+sum));
		else
			output.add(new TwoTuple( key, "f2017 "+sum));
    }

}
