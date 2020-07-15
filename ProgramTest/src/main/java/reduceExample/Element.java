package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class Element {
	private List<Atom> list = new ArrayList<Atom>();

	public List<Atom> getList() {
		return list;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	int line;

	public void setList(List<Atom> list) {
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
