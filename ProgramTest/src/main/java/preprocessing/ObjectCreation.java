package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ObjectCreation {

    String type;
    String scope = "";
    Expression scopeEx;
    Object objectScope;     //the information of scope in node
    Expression name;      //functionName
    Object objectName;    //the information of name in node
    List<Expression> parameterList = new ArrayList<>();
    List<Object> parameterObjectList = new ArrayList<>();    //the information of paramrter in node
    KeyWordAtom keyWordAtom = new KeyWordAtom();
    int line;


    public ObjectCreation(){

    }

    public ObjectCreation(Element element){
        line = Integer.parseInt(element.attribute("line").getValue());
        type = element.attribute("type").getValue();
        List<Element> elementList = element.elements();
        for (Element ele : elementList){
            System.out.println("elementList: "+ele);
        }
    }

    public void transToMethodCallExpression(ObjectCreationExpr e, Element fatherElement){
        line = e.getRange().get().begin.line;
        type = e.getTypeAsString();
        Element childElement = printXML(fatherElement);

        if(!e.getScope().toString().equals("Optional.empty")){
            scope = e.getScope().get().toString();
            scopeEx = e.getScope().get();
            Element scopeElement = childElement.addElement("scope");
            System.out.println("ScopeMate: "+scopeEx.getMetaModel());
            new IdentifyVariable().transToIdentitedVariable(scopeEx,scopeElement);
        }else {
            scope = "null";
            scopeEx = null;
        }


        parameterList = e.getArguments();
        Element parameterElement = childElement.addElement("Parameter");
        for (Expression ele : parameterList){
            new IdentifyVariable().transToIdentitedVariable(ele,parameterElement);
        }
    }

    public Element printXML(Element fatherElement) {
        Element childElement = fatherElement.addElement("ObjectCreation");
        childElement.addAttribute("line",String.valueOf(line));
        childElement.addAttribute("Type",type);
        return childElement;
    }
}
