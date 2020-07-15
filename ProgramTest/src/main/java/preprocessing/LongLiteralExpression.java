package preprocessing;

import com.github.javaparser.ast.expr.LongLiteralExpr;
import org.dom4j.Element;

public class LongLiteralExpression {
    long l;

    public LongLiteralExpression() {
    }

    public LongLiteralExpression(Element element) {
        l = Long.parseLong(element.getText());
    }

    public void transToLongLiteralExpression(LongLiteralExpr e, Element fatherElement) {
        l = e.asLong();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Long");
        childElement.addText(String.valueOf(l));
        return childElement;
    }

    @Override
    public String toString() {
        return "LongLiteralExpression{" +
                "l=" + l +
                '}';
    }
}
