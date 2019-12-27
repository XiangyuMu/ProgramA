package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;

//����<String,String>(key,value)
//��value��ֵ����
//���ɽ���
public class Example05 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
        StringBuilder totalStr = new StringBuilder();
        for (Element value : list.getList()) {
            totalStr.append(value.getList().get(1).toString());
        }
        String v =totalStr.toString();
        output.add(new TwoTuple(key, v));
    }



}
