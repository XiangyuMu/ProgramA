package preprocessing;

import com.github.javaparser.ast.expr.CharLiteralExpr;
import org.dom4j.Element;

public class CharLiteralExpression {
    char c;

    public CharLiteralExpression() {
    }

    public CharLiteralExpression(Element element) {
        c = element.getText().charAt(0);
    }

    public void transToCharLiteralExpression(CharLiteralExpr e,Element fatherElement){
        c = e.asChar();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Char");
        childElement.addText(String.valueOf(c));
        return childElement;
    }
    @Override
    public String toString() {
        return "CharLiteralExpression{" +
                "c=" + c +
                '}';
    }
}
