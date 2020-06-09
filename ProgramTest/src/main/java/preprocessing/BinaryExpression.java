package preprocessing;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import org.dom4j.Element;

public class BinaryExpression {
    Expression left = null;
    Expression right = null;
    String operator = "";

    public BinaryExpression(Element element) {

    }

    public BinaryExpression() {
    }

    public void transToBinaryExpression(BinaryExpr e, Element fatherElement) {
        this.left = e.getLeft();
        this.right = e.getRight();
        this.operator = e.getOperator().toString();
        Element childElement = printXML(fatherElement);
        new IdentifyVariable().transToIdentitedVariable(left,childElement);
        new IdentifyVariable().transToIdentitedVariable(right,childElement);
    }

    public Element printXML(Element fatherElement){

            Element childElement;
            childElement = fatherElement.addElement("Binary");
            childElement.addAttribute("operator",operator);

            return childElement;

    }
    @Override
    public String toString() {
        return "BinaryExpression{" +
                "left=" + left +
                ", right=" + right +
                ", operator='" + operator + '\'' +
                '}';
    }
}
