package preprocessing;

import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import org.dom4j.Element;

import java.util.List;

public class BooleanLiteralExpression {

    boolean value ;

    public BooleanLiteralExpression(Element element) {
        value = Boolean.parseBoolean(element.getText());
    }

    public BooleanLiteralExpression() {
    }

    @Override
    public String toString() {
        return "BooleanLiteralExpression{" +
                "value=" + value +
                '}';
    }

    public void transToBooleanLiteralExpression(BooleanLiteralExpr e, Element fatherElement) {
        value = e.getValue();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Boolean");
        childElement.addText(String.valueOf(value));
        return childElement;
    }

    public void getKeyWordList(List<KeyWord> keyWordList) {
    }
}
