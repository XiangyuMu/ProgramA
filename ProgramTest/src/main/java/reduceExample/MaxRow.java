package reduceExample;

import searchOnInternet.TwoTuple;

import java.util.ArrayList;
import java.util.List;

public class MaxRow {
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	ElemwntList gl = new ElemwntList();
	String str = "12";
	List<String> list1 = new ArrayList<>();
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);
		int max = 0;
		int y = 0;
		//int z = max+y;
		
		for(Element el : list.getList()) {
			int x = Integer.parseInt(el.getList().get(1).toString()) ;
			if(max<x) {
				max = x;
				y = Integer.parseInt(el.getList().get(2).toString()) ;
			}
		}

		for(String e2 :list1){
			doSomething(y);
		}
		output.add(new TwoTuple(key, String.valueOf(y)));
		System.out.println(gl);

	}
	
	public void doSomething(int y) {
		
	}

}
