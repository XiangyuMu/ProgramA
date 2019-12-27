package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mutualFriend/DecomposeFriendsReducer.java

//����<String,String>(key,value)
//���ֵvalue���ۼ�String
//���ɽ���
public class Example33 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String friendList = "";
		for (Element value : list.getList()) {
			friendList += value.getList().get(1).toString()+",";
		}
		// ����������к��ѣ�A	I,K,C,B,G,F,H,O,D
		output.add(new TwoTuple(key, friendList.substring(0,friendList.length()-1)));
    }

}
