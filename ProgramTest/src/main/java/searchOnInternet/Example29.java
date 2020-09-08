package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/InvertedIndex/InvertedReducer.java

//输入<String,String>(key,value)
//输出值为累加，但格式为String
//不可交换
public class Example29 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String fileList = new String();
		for (Element value : list.getList()) {
			fileList += value.getList().get(1).toString() + "; ";
		}
		
		output.add(new TwoTuple(key, fileList));		//输出格式："word	file1:num1; file2:num2;"
    }

}
