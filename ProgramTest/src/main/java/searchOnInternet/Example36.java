package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/ssdut/training/mapreduce/topten/TopTenReducer.java

//����<String,String>(key,value)
//���ֵ������ʮ�����
//���ɽ���
public class Example36 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	private TreeMap<Integer, String> visittimesMap = new TreeMap<Integer, String>();
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	for (Element val : list.getList()) {
			String[] strs = val.getList().get(1).toString().split(" ");
			visittimesMap.put(Integer.parseInt(strs[1]), val.getList().get(1).toString());	//�����ʴ�����KEY�����м�¼��VALUE������TreeMap���Զ�����
			if (visittimesMap.size() > 10) {		//���TreeMap��Ԫ�س���N��������һ����KEY��С�ģ�Ԫ��ɾ��
				visittimesMap.remove(visittimesMap.firstKey());
			}
		}
    	output.add(new TwoTuple(key,visittimesMap.toString()));
    }

}
