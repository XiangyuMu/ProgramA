package preprocessing;



import com.github.javaparser.ast.expr.Expression;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunctionWord {
    String functionName;         //"@1"
    List<Assign> assignList = new ArrayList<Assign>();          //"@2"

    public FunctionWord(){

    }

    public FunctionWord(String functionName) {
        super();
        this.functionName = functionName;
        assignList = null;
    }

    public FunctionWord(String functionName, List<Expression> le) {
        super();
        this.functionName = functionName;
        extractAssign(le);
    }

    @Override
    public String toString() {
        return "FunctionWord [functionName=" + functionName + ", assignList=" + assignList + "]";
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Assign> getAssignList() {
        return assignList;
    }

    public void setAssignList(List<Assign> assignList) {
        this.assignList = assignList;
    }

    public void extractAssign(List<Expression> le) {
        for(int i = 0;i < le.size();i++) {
            if(le.get(i).isMethodCallExpr()) {
                assignList.add(new Assign(le.get(i).asMethodCallExpr()));
            }else {
                assignList.add(new Assign(le.get(i).toString()));
            }
        }
    }

    public Element transFuncWordToXML(Element fatherElement) {

        Element childElement = null;
        childElement = fatherElement.addElement("FunctionWord");
        childElement.addAttribute("functionName",functionName);
        if (assignList!=null){
            for (int i = 0;i<assignList.size();i++){
                assignList.get(i).transAssignToXML(childElement);
            }
        }

//        String tempStr = "";
//        tempStr = tempStr + "@1\n" + functionName +"\n";
//        if(assignList!=null) {
//            for(int i = 0;i<assignList.size();i++) {
//                tempStr = tempStr + "@2\n";
//                tempStr = tempStr + assignList.get(i).transAssignToString("12");
//            }
//        }




        return fatherElement;

    }

    public void getFunctionWord(Element fatherElement){
        functionName = fatherElement.attributeValue("functionName");
        int count = 0;
//        for (Iterator i = fatherElement.elementIterator();i.hasNext();){
//            count++;
//        }
//        if (count>0){
//            for (Iterator j = fatherElement.elementIterator();j.hasNext();){
//                Assign assign = new Assign();
//                assign.getAssign((Element) j.next());
//                assignList.add(assign);
//            }
//        }
        for (Iterator j = fatherElement.elementIterator();j.hasNext();){
            Element element = (Element) j.next();
            Assign assign = new Assign();
            assign.getAssign(element);
            assignList.add(assign);
        }
    }

}

