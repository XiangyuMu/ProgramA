package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class MaxRow {
	
	ElemwntList gl = new ElemwntList();
	String str = "12";
	List<String> list1 = new ArrayList<>();
	public void reduce(ElemwntList list) {
		int max = 0;
		int y = 0;
		int z = max+y;
		
		for(Element el : list.getList()) {
			int x = (Integer) el.getList().get(1);
			if(max<x) {
				max = x;
				y = (Integer) el.getList().get(2);
			}
		}

		for(String e2 :list1){
			doSomething(y);
		}

		System.out.println(gl);

	}
	
	public void doSomething(int y) {
		
	}

}
