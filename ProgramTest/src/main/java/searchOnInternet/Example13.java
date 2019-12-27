package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;
import java.util.ArrayList;
import java.util.List;


public class Example13 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	int v =0;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        int max = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int count = 0;
        for (Element value : list.getList()) {
            if (max < (Integer)value.getList().get(1)) {
                max = (Integer)value.getList().get(1);
            }
            if (min > (Integer)value.getList().get(1)) {
                min = (Integer)value.getList().get(1);
            }
            sum += (Integer)value.getList().get(1);
            count++;
        }
        v=sum / count;
        v=max;
        v=min;
        output.add(new TwoTuple(key, v+""));
    
		    
	}


}
