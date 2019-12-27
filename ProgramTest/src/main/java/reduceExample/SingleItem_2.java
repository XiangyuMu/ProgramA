package reduceExample;

public class SingleItem_2 {

	
	ElemwntList gl = new ElemwntList();
	
	public void reduce(ElemwntList list) {
		Object x = null;
		Element el = new Element();
		boolean flag = true;
		for(int i = 0;i<list.getList().size();i++) {
			if(flag) {
				flag = false;
				el = list.getList().get(i);
				x = el.getList().get(1);
			}
			doSomething(x);
		}
		
		
	}
	
	public void doSomething(Object x) {
		
	}


}
