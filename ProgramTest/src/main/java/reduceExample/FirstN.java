package reduceExample;

public class FirstN {
	ElemwntList gl = new ElemwntList();
	int N = 5;
	
	public void reduce(ElemwntList list) {
		ElemwntList elelist = new ElemwntList();
		int count = 0;
		for(Element el : list.getList()) {
			count++;
			if(count>=N) {
				break;
				
			}
			elelist.getList().add(el);
		}
		doSomething(elelist);
	}
	public void doSomething(ElemwntList list) {
		
	}

}
