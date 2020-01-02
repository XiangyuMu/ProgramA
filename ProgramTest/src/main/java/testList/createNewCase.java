package testList;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



//create the all different cases according to initial case.
public class createNewCase {
	private ElemwntList elementList ;
	private int element_count = 0;
	private List<ElemwntList> elementListList = new ArrayList<ElemwntList>();
	
	
	public ElemwntList getElementList() {
		return elementList;
	}
	public void setElementList(ElemwntList elementList) {
		this.elementList = elementList;
		element_count = factorial(elementList.getList().size());
	}
	public int getElement_count() {
		return element_count;
	}
	public void setElement_count(int element_count) {
		this.element_count = element_count;
	}
	
	
	public List<ElemwntList> getElementListList() {
		return elementListList;
	}
	public void setElementListList(List<ElemwntList> elementListList) {
		this.elementListList = elementListList;
	}
	
	
	public int factorial(int num) {
		int fact = 1;
		int x = num;
		for(;x>=1;x--) {
			fact = x*fact;
		}
		return fact;
	}
	
	public void createAllCases() {
		ElemwntList ele = new ElemwntList();
		ele.getList().add(elementList.getList().get(0));
		elementListList.add(ele);
		
		for(int n = 0;n<elementListList.size();n++) {
			System.out.println(elementListList.get(n).toString());
			System.out.println("---------------------------");
		}
		boolean flag=true;
		for(int i = 1;i<elementList.getList().size();i++) {       //选取元素
			flag=true;
			while(flag) {
				flag=false;
				for(int j=0;j<elementListList.size();j++) {
					if(elementListList.get(j).getList().size()==i) {
						flag=true;
						ElemwntList ele1 = new ElemwntList();
						for(int k = 0;k<elementListList.get(j).getList().size();k++) {
							ele1.getList().add(elementListList.get(j).getList().get(k));
						}
						elementListList.remove(j);
						for(int l = 0;l<=ele1.getList().size();l++) {
							ElemwntList el = new ElemwntList();
							List<Element> e = el.getList();
							for(int m = 0;m<l;m++) {
								e.add(ele1.getList().get(m));
							}
							e.add(elementList.getList().get(i));
							for(int n = l;n<ele1.getList().size();n++) {
								e.add(ele1.getList().get(n));
							}
							elementListList.add(el);
							
//							for(int n = 0;n<elementListList.size();n++) {
//								System.out.println(elementListList.get(n).toString());
//								
//							}
//							System.out.println("i = "+i+" j= "+j+" l= "+l);
//							System.out.println("---------------------------");
						}
					}
				}
			
			}
			}
		
	}
	

	public static void main(String[] args) {     //所有测试用例放在一个文件中
		System.out.println("请输入测试用例的文件序号");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		
//		int num;
//		for (int i = 36;i<=39;i++) {
//			num = i;
//			TestInput ti = new TestInput();
//			ti.setPath("case");
//			ElemwntList el = ti.createTestCase_Single(num, "String", "String");
//			createNewCase cnc = new createNewCase();
//			cnc.setElementList(el);
//			cnc.createAllCases();
//			for(int k = 0;k<cnc.getElementListList().size();k++) {
//				System.out.println(cnc.getElementListList().get(k).toString());
//			}
//			System.out.println("个数为："+cnc.getElementListList().size());
//			 createDir cd = new createDir();
//			// cd.createFileDir(new File("case4.txt"));
//			 try {
//				cd.createAllCasesInOneFile(cnc.getElementListList(),"case"+num);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		TestInput ti = new TestInput();
		ti.setPath("case");
		ElemwntList el = ti.createTestCase_Single(num, "String", "String");
		createNewCase cnc = new createNewCase();
		cnc.setElementList(el);
		cnc.createAllCases();
		for(int k = 0;k<cnc.getElementListList().size();k++) {
			System.out.println(cnc.getElementListList().get(k).toString());
		}
		System.out.println("个数为："+cnc.getElementListList().size());
		 createDir cd = new createDir();
		// cd.createFileDir(new File("case4.txt"));
		 try {
			cd.createAllCasesInOneFile(cnc.getElementListList(),"case"+num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
