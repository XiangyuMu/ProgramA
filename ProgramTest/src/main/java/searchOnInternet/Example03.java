package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//����<String,String>(key,value)
//����key��Ӧ��ip�ĸ���
//�ɽ���
public class Example03 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {

		String key = (String)list.getList().get(0).getList().get(0);
        Set<String> ips = new HashSet<String>();
        for (Element ip : list.getList()) {
            ips.add(ip.getList().get(1).toString());
        }
        int count =ips.size();
        output.add(new TwoTuple(key,""+count));
    }

}
