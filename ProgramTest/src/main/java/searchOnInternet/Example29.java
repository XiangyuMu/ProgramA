package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/InvertedIndex/InvertedReducer.java


public class Example29 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String fileList = new String();
		for (Element value : list.getList()) {
			fileList += value.getList().get(1).toString() + "; ";
		}
		
		output.add(new TwoTuple(key, fileList));
    }

}
