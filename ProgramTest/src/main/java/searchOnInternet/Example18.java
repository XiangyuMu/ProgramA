package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.ElemwntList;

//输入<String,Double>(key,value)
//输出value相加和作为String相加的键值对
//不可交换
public class Example18 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	//String key = (String)list.getList().get(0).getList().get(0);
    	double newCenter;
		double sum = 0;
		int no_elements = 0;
		String points = "";
		int i = 0;
		while (i<list.getList().size()) {
			double d = Double.parseDouble((String) list.getList().get(i).getList().get(1)) ;
		//	System.out.println(d);
			points = points + " " + Double.toString(d);
			sum = sum + d;
			++no_elements;
			i++;
		}

		// We have new center now
		newCenter = sum / no_elements;

		
		// Emit new center and point
		System.out.println("yunxing");
		output.add(new TwoTuple(newCenter+"", points));
    }
}
