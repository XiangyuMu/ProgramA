package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//����<String,int>(key,value)
//���ֵΪ���ֵ
//�ɽ���
public class Example24 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	//Logger.println(context.getConfiguration(), "Reducing ts=" + timestamp);
		output.add(new TwoTuple(key,MaxValue(list.getList().iterator())));
    }

    public String MaxValue(Iterator<Element> value) {
    	int max = 0;
    	while(value.hasNext()) {
    		int v = (Integer) value.next().getList().get(1);
    		if(v>max) {
    			max = v;
    		}
    	}
    	return max+"";
    }
}
