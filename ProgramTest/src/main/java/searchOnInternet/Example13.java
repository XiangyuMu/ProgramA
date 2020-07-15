package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<int,int>(key,value)
//输出为最小值
//可交换（确定）
public class Example13 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	
	
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	int v =0;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0).getAtom();

        int max = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int count = 0;
        for (Element value : list.getList()) {
        	//System.out.println(value.getList().get(1));
            if (max < Integer.parseInt((String) value.getList().get(1).getAtom())) {
                max = Integer.parseInt((String) value.getList().get(1).getAtom());
            }
            if (min > Integer.parseInt((String) value.getList().get(1).getAtom())) {
                min = Integer.parseInt((String) value.getList().get(1).getAtom());
            }
            sum += Integer.parseInt((String) value.getList().get(1).getAtom());
            count++;
        }
        v=sum / count;
        v=max;
        v=min;
        output.add(new TwoTuple(key, v+""));
    
		    
	}


}
