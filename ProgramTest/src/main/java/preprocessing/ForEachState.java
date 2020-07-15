package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ForEachStmt;
import learnJavaParser.Practice;
import org.dom4j.Element;

import java.util.List;

public class ForEachState {
    Expression iterator;
    VariableDeclarationExpr variableDeclarationExpr;
    int line;
    Object iteratorObject;
    Object variableDeclarationObject;
    int last;


    public ForEachState(){

    }

    public ForEachState(Element element){
        line = Integer.parseInt(element.attribute("line").getValue());
        last = Integer.parseInt(element.attribute("last").getValue());
        List<Element> elementList = element.elements();
        for (Element ele:elementList){
            if (ele.getName().equals("Iterator")){
                iteratorObject = new Practice().extractElement((Element)ele.elements().get(0));
            }else if (ele.getName().equals("VariableDeclaration")){
                variableDeclarationObject = new Practice().extractElement((Element)ele.elements().get(0));
            }
        }


    }

    public void transToForEachState(ForEachStmt stmt, Element fatherElement) {
        iterator = stmt.getIterable();
        variableDeclarationExpr = stmt.getVariable();
        line = stmt.getRange().get().begin.line;
        last = stmt.getRange().get().end.line;
        System.out.println("ForEachStateIterable: " + iterator.getMetaModel());
        System.out.println("iterator: "+iterator);
        System.out.println("variableDeclarationExpr: " + variableDeclarationExpr.getMetaModel());
        System.out.println("iterator: "+variableDeclarationExpr);
        Element childElement = printXML(fatherElement);
        Element iteratorElement = childElement.addElement("Iterator");
        new IdentifyVariable().transToIdentitedVariable(iterator,iteratorElement);
        Element VariableElement = childElement.addElement("VariableDeclaration");
        new IdentifyVariable().transToIdentitedVariable(variableDeclarationExpr,VariableElement);
    }


    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("ForEach");
        childElement.addAttribute("line", String.valueOf(line));
        childElement.addAttribute("last",String.valueOf(last));
        return childElement;
    }


    @Override
    public String toString() {
        return "ForEachState{" +
                "iterator=" + iterator +
                ", variableDeclarationExpr=" + variableDeclarationExpr +
                ", line=" + line +
                ", iteratorObject=" + iteratorObject +
                ", variableDeclarationObject=" + variableDeclarationObject +
                ", last=" + last +
                '}';
    }
}
