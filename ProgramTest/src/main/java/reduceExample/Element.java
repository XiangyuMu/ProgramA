package reduceExample;

import java.util.ArrayList;
import java.util.List;

public class Element {
	private List<Atom> Atomlist = new ArrayList<Atom>();

	private List<Object> list = new ArrayList<>();
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public List<Atom> getAtomlist() {
		return Atomlist;
	}

	public void setAtomlist(List<Atom> atomlist) {
		Atomlist = atomlist;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	int line;

	@Override
	public String toString() {
		return "Element{" +
				"Atomlist=" + Atomlist +
				", list=" + list +
				", line=" + line +
				'}';
	}

	//	public String toString() {
//		String str = "[";
//		for(int i = 0;i<list.size();i++) {
//			str=str+list.get(i).toString()+", ";
//		}
//		str = str+"]";
//		return str;
//	}
}
