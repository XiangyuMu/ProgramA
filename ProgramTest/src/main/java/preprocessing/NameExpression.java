package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import org.dom4j.Element;

public class NameExpression {
    String name;

    public NameExpression(Element element) {
        name = element.getText();
    }

    public NameExpression() {
    }

    public void transToNameExpression(NameExpr e, Element fatherElement) {
        name = e.getNameAsString();
        printXML(fatherElement);
        System.out.println(e);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Name");
        childElement.addText(name);
        return childElement;
    }
    @Override
    public String toString() {
        return "NameExpression{" +
                "name=" + name +
                '}';
    }
}
