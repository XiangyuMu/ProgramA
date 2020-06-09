package preprocessing;

import com.github.javaparser.ast.expr.StringLiteralExpr;
import org.dom4j.Element;

public class StringLiteralExpression {
    String s;

    public StringLiteralExpression(Element element) {
    }

    public StringLiteralExpression() {
    }

    public void transToStringLiteralExpression(StringLiteralExpr e, Element fatherElement) {
        s = e.asString();
        printXML(fatherElement);
    }
    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("String");
        childElement.addText(s);
        return childElement;
    }
    @Override
    public String toString() {
        return "StringLiteralExpression{" +
                "s='" + s + '\'' +
                '}';
    }
}
