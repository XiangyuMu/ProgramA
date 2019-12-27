package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//����<String,String>(key,value)
//���ֵΪvalue���ۼ�
//�ɽ���
public class Example22 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	 int sum = 0;
         Iterator<Element> iterator = list.getList().iterator();

         while (iterator.hasNext())
             sum += (Integer)iterator.next().getList().get(1);
         output.add(new TwoTuple(key, sum+""));
    }


}
