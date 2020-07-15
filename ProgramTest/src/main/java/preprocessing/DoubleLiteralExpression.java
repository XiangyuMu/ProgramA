package preprocessing;

import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import org.dom4j.Element;

public class DoubleLiteralExpression {
    Double d;

    public DoubleLiteralExpression(Element element) {
        d = Double.valueOf(element.getText());
    }

    public DoubleLiteralExpression() {
    }

    public void transToDoubleLiteralExpression(DoubleLiteralExpr e, Element fatherElement) {
        d = e.asDouble();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Double");
        childElement.addText(String.valueOf(d));
        return childElement;
    }
    @Override
    public String toString() {
        return "DoubleLiteralExpression{" +
                "d=" + d +
                '}';
    }
}
