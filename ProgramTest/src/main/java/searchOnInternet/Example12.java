package searchOnInternet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,String>(key,value)        (value中格式为int-int，其中第一个int为0或1，且只有一个为0)
//将int-int中若前一个int为0，则第二个int为key，形成多个键值对。
//可交换（确定）
public class Example12 {

	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0).getAtom();

        String customerName = "";
        Set<Integer> orders = new HashSet<Integer>();
        //提取customer和order表的信息
        //这里可以使用更为简便的方式,如map时使用多类型的value来区分,这里重点在于map和reduce中间连接的key部分
        for (Element value : list.getList()) {
            if (value.getList().get(1).toString().startsWith("0")) {
                customerName = value.getList().get(1).toString().split("-")[1];
            }
            if (value.getList().get(1).toString().startsWith("1")) {
                orders.add(Integer.parseInt(value.getList().get(1).toString().split("-")[1]));
            }
        }
        key = (customerName);
        //left join 以左表Customer为基础
        for (Integer order : orders) {
            v=(order).toString();
            output.add(new TwoTuple(key, v));
        }
    
    
    
		    
	}


}
