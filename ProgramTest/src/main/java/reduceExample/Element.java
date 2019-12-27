package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class Element {
	private List<Object> list = new ArrayList<Object>();

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	

	public String toString() {
		String str = "[";
		for(int i = 0;i<list.size();i++) {
			str=str+list.get(i).toString()+", ";
		}
		str = str+"]";
		return str;
	}
}
