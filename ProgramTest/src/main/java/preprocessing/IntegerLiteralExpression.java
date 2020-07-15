package preprocessing;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import org.dom4j.Element;

import java.util.List;

public class IntegerLiteralExpression {
    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public IntegerLiteralExpression(Element element) {
        i = Integer.parseInt(element.getText());
    }

    public IntegerLiteralExpression() {
    }

    public void transToIntegerLiteralExpression(IntegerLiteralExpr e, Element fatherElement) {
        i = e.asInt();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Integer");
        childElement.addText(String.valueOf(i));
        return childElement;
    }
    @Override
    public String toString() {
        return "IntegerLiteralExpression{" +
                "i=" + i +
                '}';
    }

    public void getKeyWordList(List<KeyWord> keyWordList) {
    }
}
