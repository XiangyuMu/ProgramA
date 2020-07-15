package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import learnJavaParser.Practice;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MethodCallExpression {
    String scope = "";
    Expression scopeEx;
    Object objectScope;     //the information of scope in node
    Expression name;      //functionName
    Object objectName;    //the information of name in node
    List<Expression> parameterList = new ArrayList<>();
    List<Object> parameterObjectList = new ArrayList<>();    //the information of paramrter in node
    KeyWordAtom keyWordAtom = new KeyWordAtom();
    int line;

    @Override
    public String toString() {
        return "MethodCallExpression{" +
                "scope='" + scope + '\'' +
                ", scopeEx=" + scopeEx +
                ", objectScope=" + objectScope +
                ", name=" + name +
                ", objectName=" + objectName +
                ", parameterList=" + parameterList +
                ", parameterObjectList=" + parameterObjectList +
                ", keyWordAtom=" + keyWordAtom +
                ", line=" + line +
                '}';
    }

    public MethodCallExpression(Element element) {
       // System.out.println(element.attribute("line").getValue());
        line = Integer.parseInt(element.attribute("line").getValue());
        List<Element> elementList = element.elements();
        for (Element ele : elementList){
            System.out.println("elementList: "+ele);
            String eleText = ele.getText();
            if(eleText.trim().equals("")){
                eleText = "";
            }
            System.out.println("eleText: "+eleText);
            System.out.println("eleName: "+ele.getName());
            if (ele.getName().equals("scope")){
                String scopeText = ele.getText();
                if (scopeText.trim().equals("")){
                    scopeText = "";
                }
                scope = scopeText;
                System.out.println("ele: "+scopeText);
                System.out.println("size: "+ele.elements().size());
                if(ele.elements().size()!=0){
                    System.out.println(ele);
                   // System.out.println(ele.element("Method").getName());
                    objectScope = new Practice().extractElement((Element)ele.elements().get(0));
                }
            }else if (ele.getName().equals("name")){
                System.out.println("nameEle: "+(Element) ele.elements().get(0));
                objectName = new Practice().extractElement((Element) ele.elements().get(0));
            }else if (ele.getName().equals("Parameter")){
                if (ele.elements().size()!=0){
                    for (Iterator i = ele.elementIterator(); i.hasNext();){
                        Element e = (Element) i.next();
                        Practice p = new Practice();
                        parameterObjectList.add(p.extractElement(e));
                    }
                }
            }else{
                System.out.println("没有运行到此!!!!");
            }
        }
    }


    public void transToMethodCallExpression(MethodCallExpr e, Element fatherElement) {
        line = e.getRange().get().begin.line;
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
        name = e.getNameAsExpression();
        parameterList = e.getArguments();

        Element parameterElement = childElement.addElement("Parameter");
        for (Expression ele : parameterList){
            new IdentifyVariable().transToIdentitedVariable(ele,parameterElement);
        }
        Element nameElement = childElement.addElement("name");

        new IdentifyVariable().transToIdentitedVariable(name,nameElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Method");

        childElement.addAttribute("line",String.valueOf(line));
       // Element scopeElement = childElement.addElement("scope");
        //childElement.addElement("scope1").addText(scope);
        return childElement;
    }
    public MethodCallExpression(String scope) {
        this.scope = scope;
    }

    public MethodCallExpression() {
    }

    public void getKeyWordList(List<KeyWord> keyWordList) {
        String[] str = scope.split("\\.");
        System.out.println("scope: "+scope.split("\\."));
        if(str.length==1){
            keyWordList.add(new KeyWord(str[0]));
        }else{
            keyWordList.add(new KeyWord(str[0]));
        }
    }


    public List<Object> getObjectList(){
        List<Object> objectList = new ArrayList<>();

        return objectList;
    }

    public KeyWordAtom getKeyWordAtom(){
        if (objectScope.getClass().getSimpleName().equals("NameExpression")){
            String name = ((NameExpression)objectScope).name;
            keyWordAtom.setKeyWordName(name);

        }else if (objectScope.getClass().getSimpleName().equals("MethodCallExpression")){
            //System.out.println(objectScope.getClass().getSimpleName());
            keyWordAtom.copyValue(((MethodCallExpression)objectScope).getKeyWordAtom());
        }
        if (parameterObjectList.size()!=0){
            if (parameterObjectList.size()==1){
                Object parameter = parameterObjectList.get(0);
                switch (parameter.getClass().getSimpleName()){
                    case "IntegerLiteralExpression":{
                        IntegerLiteralExpression ile = (IntegerLiteralExpression)parameter;
                        if (keyWordAtom.row.equals("")){
                            keyWordAtom.setRowType("int");
                            keyWordAtom.setRow(String.valueOf(ile.getI()));
                        }else{
                            keyWordAtom.setColumnType("int");
                            keyWordAtom.setColumn(String.valueOf(ile.getI()));
                        }
                        break;
                    }
                    //case ""
                }
            }else{
                System.out.println("parameters are more than one!!");
            }
        }
        return keyWordAtom;
    }
}
