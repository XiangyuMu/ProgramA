package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class StrConcat {

	ElemwntList gl = new ElemwntList();
	List<String> names = new ArrayList<String>();
	
	public void reduce(ElemwntList list) {
		for(Element el : list.getList()) {
			names.add((String) el.getList().get(1));
		}
		String s = String.join("|", names);
		
		doSomething(s);
	}
	
	
	public void doSomething(String s) {
		
	}
}
