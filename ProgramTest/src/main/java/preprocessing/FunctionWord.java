package preprocessing;



import com.github.javaparser.ast.expr.Expression;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FunctionWord {
    String functionName;         //"@1"
    List<Para> paraList = new ArrayList<Para>();          //"@2"

    public FunctionWord(){

    }

    public FunctionWord(String functionName) {
        super();
        this.functionName = functionName;
        paraList = null;
    }

    public FunctionWord(String functionName, List<Expression> le) {
        super();
        this.functionName = functionName;
        extractAssign(le);
    }

    @Override
    public String toString() {
        return "FunctionWord [functionName=" + functionName + ", paraList=" + paraList + "]";
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Para> getParaList() {
        return paraList;
    }

    public void setParaList(List<Para> paraList) {
        this.paraList = paraList;
    }

    public void extractAssign(List<Expression> le) {
        for(int i = 0;i < le.size();i++) {
            if(le.get(i).isMethodCallExpr()) {
                paraList.add(new Para(le.get(i).asMethodCallExpr()));
            }else {
                paraList.add(new Para(le.get(i).toString()));
            }
        }
    }

    public Element transFuncWordToXML(Element fatherElement) {

        Element childElement = null;
        childElement = fatherElement.addElement("FunctionWord");
        childElement.addAttribute("functionName",functionName);
        if (paraList !=null){
            for (int i = 0; i< paraList.size(); i++){
                paraList.get(i).transAssignToXML(childElement);
            }
        }

//        String tempStr = "";
//        tempStr = tempStr + "@1\n" + functionName +"\n";
//        if(paraList!=null) {
//            for(int i = 0;i<paraList.size();i++) {
//                tempStr = tempStr + "@2\n";
//                tempStr = tempStr + paraList.get(i).transAssignToString("12");
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
//                Para assign = new Para();
//                assign.getAssign((Element) j.next());
//                paraList.add(assign);
//            }
//        }
        for (Iterator j = fatherElement.elementIterator();j.hasNext();){
            Element element = (Element) j.next();
            Para para = new Para();
            para.getAssign(element);
            paraList.add(para);
        }
    }

}

