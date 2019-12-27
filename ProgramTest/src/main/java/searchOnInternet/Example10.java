package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;

//����<String,String>(key,value)
//��value��ΪString�����ۼ�
//���ɽ���
public class Example10 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	
	String userId = "";
	String itermScore = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        //map���������keyΪ1,valueΪ101:5.0
        String itermPers = "";
        for (Element value : list.getList()) {
            itermPers += "," + value.getList().get(1).toString();
        }
        v=(itermPers.replaceFirst(",", ""));
        output.add(new TwoTuple(key, v));
    
		    
	}


}
