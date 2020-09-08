package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.ElemwntList;

//输入<String,String>(key,value)
//输出value为空的键值对
//不可交换
public class Example20 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	int i = 0;
    	while (i<list.getList().size()) {
            String inputDoc = (String) list.getList().get(i).getList().get(1);
            System.out.println(inputDoc);
            boolean keep = inputDoc.isEmpty();
            i++;
            if (!keep) {
                String incrCounter="BehemothReducer";
                continue;
            }
            output.add(new TwoTuple(key, inputDoc));
    	}
    }
}
