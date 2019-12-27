package reduceExample;

public class SingleItem_1 {
	
	ElemwntList gl = new ElemwntList();
	
	public void reduce(ElemwntList list) {
		Object x = null;
		Element el = new Element();
		for(int i = 0;i<list.getList().size();i++) {
			el = list.getList().get(i);
			x = el.getList().get(1);
		}
		doSomething(x);
		
	}
	
	public void doSomething(Object x) {
		
	}
}
