package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;
//输入为<String,String>(key,value)     (value由String+“”+float组成)
//生成一串键值对，且(key，value)中value为在总和中的占比
//不可交换（确定）

public class Example08 {
	
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



        System.out.println("FinallyResult input:");
        StringBuilder printStr = new StringBuilder();
        float totalPr = 0f;
        List<String> list_1 = new ArrayList<String>();
        for (Element value : list.getList()) {
            String valueStr = value.getList().get(1).toString();
            list_1.add(valueStr);
            String[] strArr = valueStr.split(" ");
            totalPr += Float.parseFloat(strArr[1]);
            printStr.append(",").append(valueStr);
        }
        System.out.println(printStr.toString().replace(",", ""));

        for (String s : list_1) {
            String[] strArr = s.split("");
            v=(String.valueOf(Float.parseFloat(strArr[1]) / totalPr));
            output.add(new TwoTuple(key,v));
            System.out.println("FinallyResult output:");
           // System.out.println(k.toString() + ":" + v.toString());
            System.out.println();
        }
    
        }



}
