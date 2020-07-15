package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import reduceExample.Element;
import reduceExample.ElemwntList;
//https://github.com/josonle/MapReduce-Demo/blob/master/src/main/java/ssdut/training/mapreduce/topten/TopTenReducer.java

//输入<String,String>(key,value)
//输出值将最后的十个输出
//不可交换
public class Example36 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	private TreeMap<Integer, String> visittimesMap = new TreeMap<Integer, String>();
	float gradesSum;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();

    	for (Element val : list.getList()) {
			String[] strs = val.getList().get(1).toString().split(" ");
			visittimesMap.put(Integer.parseInt(strs[1]), val.getList().get(1).toString());	//将访问次数（KEY）和行记录（VALUE）放入TreeMap中自动排序
			if (visittimesMap.size() > 10) {		//如果TreeMap中元素超过N个，将第一个（KEY最小的）元素删除
				visittimesMap.remove(visittimesMap.firstKey());
			}
		}
    	output.add(new TwoTuple(key,visittimesMap.toString()));
    }

}
