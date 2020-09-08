package reduceExample;

import searchOnInternet.TwoTuple;

import java.util.ArrayList;
import java.util.List;

public class FirstN {
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	int N = 3;
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	public void reduce(ElemwntList list) {
		ElemwntList elelist = new ElemwntList();
		String key = (String)list.getList().get(0).getList().get(0);
		int count = 0;
		for(Element el : list.getList()) {
			count++;
			if(count>=N) {
				break;
			}
			elelist.getList().add(el);
		}
		doSomething(elelist);
		output.add(new TwoTuple(key, elelist.toString()));
	}
	public void doSomething(ElemwntList list) {

	}

}
