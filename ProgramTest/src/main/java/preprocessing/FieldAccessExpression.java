package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.SimpleName;
import org.dom4j.Element;

public class FieldAccessExpression {
    String  name;
    Expression expression;

    public FieldAccessExpression(Element element) {
    }

    public FieldAccessExpression() {
    }

    public void transToFieldAccessExpression(FieldAccessExpr e, Element fatherElement) {
        name = e.getNameAsString();
        expression = e.getScope();
        Element childElement = printXML(fatherElement);
        new IdentifyVariable().transToIdentitedVariable(expression,childElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("FieldAccess");
        childElement.addAttribute("name",name);
        return childElement;
    }

    @Override
    public String toString() {
        return "FieldAccessExpression{" +
                "name=" + name +
                ", expression=" + expression +
                '}';
    }
}
