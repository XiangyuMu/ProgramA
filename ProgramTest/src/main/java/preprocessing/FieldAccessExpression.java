package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.SimpleName;
import learnJavaParser.Practice;
import org.dom4j.Element;

public class FieldAccessExpression {
    String  name;
    Expression expression;
    Object exprObject;

    @Override
    public String toString() {
        return "FieldAccessExpression{" +
                "name='" + name + '\'' +
                ", expression=" + expression +
                ", exprObject=" + exprObject +
                '}';
    }

    public FieldAccessExpression(Element element) {
        name = element.attribute("name").getValue();
        exprObject = new Practice().extractElement((Element) element.elements().get(0));
    }

    public FieldAccessExpression() {
    }

    public void transToFieldAccessExpression(FieldAccessExpr e, Element fatherElement) {
        name = e.getNameAsString();
        expression = e.getScope();
        System.out.println("FieldAccessExpression: " + expression.getMetaModel());
        Element childElement = printXML(fatherElement);
        new IdentifyVariable().transToIdentitedVariable(expression,childElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("FieldAccess");
        childElement.addAttribute("name",name);
        return childElement;
    }

}
