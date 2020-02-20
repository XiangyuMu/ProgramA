package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,int>(key,value)
//将value的值进行累加
//可交换(确定)
public class Example02 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {

		String key = (String)list.getList().get(0).getList().get(0).getAtom();
        Integer sum = 0;
        for (Element i : list.getList()) {
        	System.out.println(i.getList().get(1));
            sum += Integer.parseInt(i.getList().get(1).toString());
        }
        
        output.add(new TwoTuple(key, sum.toString()));
    
	}

	
	
	public boolean equal(List<TwoTuple> list) {
		boolean flag = true;
		if(output.size() != list.size()) {
			System.out.println("输出个数不同！");
			flag = false;
			return flag;
		}else {
			for(int i = 0;i<output.size();i++) {
				if(!list.get(i).equal(output.get(i))) {
					flag = false;
					System.out.println("结果不同！");
					return flag;
				}
			}
			return flag;
		}
	}
}
