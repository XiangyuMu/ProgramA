package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,String>(key,value)
//将value的值连接
//不可交换（不可交换）
public class Example05 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public List<TwoTuple> getOutput() {
		return output;
	}

	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}

	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0).getAtom();
        StringBuilder totalStr = new StringBuilder();
        for (Element value : list.getList()) {
            totalStr.append(value.getList().get(1).toString());
        }
        String v = totalStr.toString();
        output.add(new TwoTuple(key, v));
    }



}
