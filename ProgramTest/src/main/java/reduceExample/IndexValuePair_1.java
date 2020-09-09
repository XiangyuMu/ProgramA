package reduceExample;

import searchOnInternet.TwoTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexValuePair_1 {
	ElemwntList gl = new ElemwntList();
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
		Object x = null;
		Object y = null;
		Element el = new Element();
		Map mp = new HashMap();
		for(int i = 0;i<list.getList().size();i++) {
			el = list.getList().get(i);
			x = el.getList().get(1);
			y = el.getList().get(2);
			mp.put(x, y);
			int temp1 = 1;
		}
		doSomething(mp);
		output.add(new TwoTuple(key, mp.toString()));
        int temp2 = 2;
	}
	
	public void doSomething(Map mp) {
		
	}
}
