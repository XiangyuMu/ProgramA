package searchOnInternet;

import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;


public class Example18 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	//String key = (String)list.getList().get(0).getList().get(0);
    	double newCenter;
		double sum = 0;
		int no_elements = 0;
		String points = "";
		int i = 0;
		while (list.getList().get(i)!=null) {
			double d = (Double) list.getList().get(i).getList().get(1);
			points = points + " " + Double.toString(d);
			sum = sum + d;
			++no_elements;
		}

		// We have new center now
		newCenter = sum / no_elements;

		
		// Emit new center and point
		output.add(new TwoTuple(newCenter+"", points));
    }
}
