package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/mapreduceProgram/DateSortAsc.java

//����<String,String>(key,value)
//���ֵΪkey��value�Ļ���
//���ɽ���
public class Example31 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	for (Element value : list.getList()) {
			// ������ٴεߵ�k-v����������Ϊkey
			System.out.println(value.getList().get(1).toString()+":"+key);
			output.add(new TwoTuple(value.getList().get(1).toString(), key));
		}
    }

}
