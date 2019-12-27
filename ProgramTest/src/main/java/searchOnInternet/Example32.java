package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mutualFriend/MergeFriendsReducer.java

//����<String,String>(key,value)
//���ֵΪkey��value�Ļ���
//���ɽ���
public class Example32 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String friends = "";
		for (Element value : list.getList()) {
			friends += value.getList().get(1).toString()+",";
		}
		System.out.println(key.toString()+" "+friends);
		output.add(new TwoTuple(key, friends));
    }

}
