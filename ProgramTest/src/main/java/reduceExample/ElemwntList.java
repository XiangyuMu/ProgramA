package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class ElemwntList {
	private List<Element> list = new ArrayList<Element>();

	public List<Element> getList() {
		return list;
	}

	public void setList(List<Element> list) {
		this.list = list;
	}

	public String toString() {
		String str = "";
		for(int i = 0;i<list.size();i++) {
			str = str+list.get(i).toString()+"\n";
		}
		return str;
	}

	
	

}
