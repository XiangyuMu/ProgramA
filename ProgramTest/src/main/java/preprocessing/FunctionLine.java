package preprocessing;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 public class FunctionLine {

	List<FunctionWord> functionName = new ArrayList<>();    //"!6"
	Boolean isChecked = false;             //"!2"
	int number = 0;                //"!3"
	String circumPotion = "0";     //"!4"
	String name;                   //"!1"
	int line = 0;                  //"!5"



	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Boolean getChecked() {
		return isChecked;
	}

	public void setChecked(Boolean checked) {
		isChecked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FunctionWord> getFunctionName() {
		return functionName;
	}

	public void setFunctionName(List<FunctionWord> functionName) {
		this.functionName = functionName;
	}


	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean checked) {
		isChecked = checked;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCircumPotion() {
		return circumPotion;
	}

	public void setCircumPotion(String circumPotion) {
		this.circumPotion = circumPotion;
	}



	@Override
	public String toString() {
		return "FunctionLine [name="+name+", functionName=" + functionName + ",isChecked=" + isChecked + ", number=" + number
				+ ", circumPotion=" + circumPotion + ", line=" + line + "]\n";
	}

	public Element transFuncToXML(Element fatherElement)  {

		Element childElement = null;
		childElement = fatherElement.addElement("FunctionLine");
		childElement.addAttribute("name",name);
		childElement.addAttribute("isChecked",isChecked.toString());
		childElement.addAttribute("number",String.valueOf(number));
		childElement.addAttribute("circumPotion",circumPotion);
		childElement.addAttribute("line", String.valueOf(line));

		if(functionName.size()!=0){
			for (int i = 0;i<functionName.size();i++){
				functionName.get(i).transFuncWordToXML(childElement);
			}
		}
//		String tempString = "";
//		tempString = tempString +"!1\n" + name + "\n";
//		tempString = tempString +"!2\n" + isChecked + "\n";
//		tempString = tempString +"!3\n" + number + "\n";
//		tempString = tempString +"!4\n" + circumPotion + "\n";
//		tempString = tempString +"!5\n" + line + "\n";
//
//		if(functionName.size()!=0) {
//			for(int i =0;i<functionName.size();i++) {
//				tempString = tempString + functionName.get(i).transFuncWordToString();
//			}
//
//		}
////        tempString = tempString  + "\n";
//		return tempString;
		return fatherElement;
	}

	public void getFunctionLine(Element fatherElement){
		name = fatherElement.attributeValue("name");
		isChecked =  fatherElement.attributeValue("isChecked").equals("true");
		number = Integer.parseInt(fatherElement.attributeValue("number")) ;
		circumPotion = fatherElement.attributeValue("circumPotion");
		line = Integer.parseInt(fatherElement.attributeValue("line"));
		for(Iterator i = fatherElement.elementIterator();i.hasNext();){
			Element element = (Element) i.next();
			FunctionWord f = new FunctionWord();
			f.getFunctionWord(element);
			functionName.add(f);
			//FunctionWord
		}
	}

}
