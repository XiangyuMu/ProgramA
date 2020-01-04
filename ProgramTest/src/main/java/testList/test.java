package testList;

import java.util.ArrayList;
import java.util.List;

public class test {
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	
	public boolean addElement(String str) {
		list1.add(str);
		list2.add(str);
		return list1.equals(list2);
	}

	
	public static void main(String[] args) {
		test te = new test();
		System.out.println(te.addElement("123"));
	}
}
