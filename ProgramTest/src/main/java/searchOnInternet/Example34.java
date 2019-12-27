package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/shuffleTest/TempSort.java

//����<String,int>(key,value)
//���ֵΪvalue���ۼ�
//�ɽ���
public class Example34 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	int  maxTemp = Integer.MIN_VALUE;
		for(Element value : list.getList()) {
			System.out.println("�꣺"+key+", ���£�"+value);
			if ((Integer)value.getList().get(1)>maxTemp) {
				maxTemp = (Integer) value.getList().get(1);
			}
		}
		System.out.println("Date:"+key+", MaxTemp:"+maxTemp);
		output.add(new TwoTuple(key, Integer.toString(maxTemp)));
    }

}
