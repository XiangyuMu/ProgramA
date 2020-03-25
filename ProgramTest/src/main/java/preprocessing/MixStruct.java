package preprocessing;

import java.util.*;

import com.github.javaparser.ast.expr.MethodCallExpr;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * It has two type dataStructures: last and keylist
 * last:store the invoked functions to check out which which functions have been invoked in an expression
 * keylast keep the functions which are called for a keyword
 */
public class MixStruct {

	List<MethodCallExpr> last = new ArrayList<MethodCallExpr>();

	List<FunctionLine> lf = new ArrayList<FunctionLine>();

	List<KeyWordFunction> keyList = new ArrayList<KeyWordFunction>();

	List<ForInfo> forList = new ArrayList<ForInfo>();



	public List<ForInfo> getForList() {
		return forList;
	}

	public void setForList(List<ForInfo> forList) {
		this.forList = forList;
	}

	public List<FunctionLine> getLf() {
		return lf;
	}

	public void setLf(List<FunctionLine> lf) {
		this.lf = lf;
	}

	public List<MethodCallExpr> getLast() {
		return last;
	}

	public void setLast(List<MethodCallExpr> last) {
		this.last = last;
	}

	public List<KeyWordFunction> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<KeyWordFunction> keyList) {
		this.keyList = keyList;
	}

	@Override
	public String toString() {
		return "MixStruct [last=" + last + ",\n lf=" + lf + ",\n keyList=" + keyList + "]\n";
	}

	public Document transKeyListToString()  {

		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("information");
		if(lf.size()!=0){
			for (int i = 0;i<lf.size();i++){
				lf.get(i).transFuncToXML(root);
			}
		}

		return doc;
//		String tempString = "";
//		if(lf.size()!=0) {
//			for(int i =0;i<lf.size();i++) {
//				tempString += lf.get(i).transFuncToString();
//			}
//		}
//		return tempString;
	}

	public void getMixStruct(Element fatherElement){
		for(Iterator i = fatherElement.elementIterator();i.hasNext();){
			Element element = (Element) i.next();
			FunctionLine f = new FunctionLine();
			f.getFunctionLine(element);
			lf.add(f);
		}
	}

	public void sortForList() {
		Collections.sort(forList, new Comparator<ForInfo>() {
			@Override
			public int compare(ForInfo o1, ForInfo o2) {
				// TODO Auto-generated method stub
				int diff = o1.getBegin() - o2.getBegin();
				if(diff>0) {
					return 1;
				}else if(diff<1) {
					return -1;
				}
				return 0;
			}
		});
		System.out.println(forList);
		if(!forList.isEmpty()) {
			Stack<ForInfo> stack = new Stack<ForInfo>();
			ForInfo fi ;
			ForInfo proFi = new ForInfo(0,0);
			proFi.setNumber("");
			boolean flag = false;
			fi = forList.get(0);
			stack.push(forList.get(0));
			fi.setNumber("1");
			String[] tempStr;
			String temp;
			for(int i = 1;i<forList.size();i++) {
				flag = false;
				System.out.println(forList);
				while(true) {
					if(stack.peek().getEnd()<forList.get(i).getBegin()) {
						flag = true;
						if(stack.size()==1) {
							fi = stack.peek();
							forList.get(i).setNumber(Integer.parseInt(fi.getNumber())+1+"");
							fi = forList.get(i);
							stack.pop();
							stack.push(fi);
							break;
						}else {
							proFi = stack.pop();
						}

					}else if(stack.peek().getEnd()>forList.get(i).getBegin()){
						fi = stack.peek();
						int tempInt;

						if(!flag) {
							forList.get(i).setNumber(fi.getNumber()+".1");
						}else {
							tempStr = proFi.getNumber().split(".");

							tempInt = Integer.parseInt(tempStr[tempStr.length-1])+1;
							temp = "";
							for(int j = 0;j<tempStr.length-1;j++) {
								temp = temp + "." + tempStr[j];
							}
							temp = temp + "." + tempInt;
							forList.get(i).setNumber(temp);
						}
//						if(tempStr.length==0) {
//							tempInt = Integer.parseInt(fi.getNumber())+1;
//							temp = String.valueOf(tempInt);
//						}else {
//							tempInt = Integer.parseInt(tempStr[tempStr.length-1])+1;
//							temp = "";
//							for(int j = 0;j<tempStr.length-1;j++) {
//								temp = temp + "." + tempStr[j];
//							}
//							temp = temp + "." + tempInt;
//						}

						fi = forList.get(i);
						stack.push(fi);
						break;
					}
				}

			}
		}


	}

	public void completeLF() {
		for(int i = 0;i<this.lf.size();i++) {
			completeForCount(lf.get(i));
		}
	}

	public void completeForCount(FunctionLine fl) {
		String tempfornum = "";
		for(int i = 0;i<forList.size();i++) {
			if(forList.get(i).getBegin()<fl.getLine()&&forList.get(i).getEnd()>fl.getLine()) {
				if(forList.get(i).getNumber().length()>=tempfornum.length()) {
					tempfornum = forList.get(i).getNumber();
				}
//				fl.setCircumPotion(forList.get(i).getNumber());

			}
		}
		if(tempfornum.equals("")){
			tempfornum = "0";
		}
		fl.setCircumPotion(tempfornum);

	}
//	public void transStringtoFunc(String str) {
//		if(str.contains("@ ")) {
//			KeyWordFunction kwf = new KeyWordFunction();
//			kwf.setName(str.split("@ ")[0]);
//			this.keyList.add(kwf);
//		}else if(str.contains("# ")) {
//			String str1[] = str.split("# ");
//			FunctionLine fl = new FunctionLine();
//			fl.setIsChecked(Boolean.valueOf(str1[str1.length-1]));
//			for(int k = 0;k<str1.length-1;k++) {
//				fl.getFunctionName().add(str1[k]);
//			}
//			this.keyList.get(keyList.size()-1).getFuncList().add(fl);
//		}
//	}

//	public void setKeyWordList(String name,List<String> list) {
//		boolean isExist = false;
//		int num = 0;
//		for(int i = 0;i<this.keyList.size();i++) {
//			if(this.keyList.get(i).getName().equals(name)) {
//				isExist = true;
//				num = i;
//				break;
//			}
//		}
//		if(isExist) {
//			FunctionLine fl = new FunctionLine();
//			fl.setFunctionName(list);
//			fl.setIsChecked(false);
//			this.keyList.get(num).getFuncList().add(fl);
//		}else {
//			KeyWordFunction kwf = new KeyWordFunction();
//			kwf.setName(name);
//			FunctionLine fl = new FunctionLine();
//			fl.setFunctionName(list);
//			fl.setIsChecked(false);
//			kwf.getFuncList().add(fl);
//			this.keyList.add(kwf);
//		}
//	}

}
