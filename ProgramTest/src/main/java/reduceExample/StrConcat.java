package reduceExample;

import searchOnInternet.TwoTuple;

import java.util.ArrayList;
import java.util.List;

public class StrConcat {
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	ElemwntList gl = new ElemwntList();
	List<String> names = new ArrayList<String>();
	
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
		for(Element el : list.getList()) {
			names.add((String) el.getList().get(1));
		}
		String s = String.join("|", names);
		
		doSomething(s);
		output.add(new TwoTuple(key, s));
	}
	
	
	public void doSomething(String s) {
		
	}
}
