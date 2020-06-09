package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import org.dom4j.Element;

public class SimpleNameExpression {
    String name;

    public SimpleNameExpression(Element element) {

    }

    public SimpleNameExpression() {
    }

    public void transToSimpleNameExpression(Expression e, Element fatherElement) {
        name = e.toString();
        printXML(fatherElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("SimpleName");
        childElement.addText(name);
        return childElement;
    }
    @Override
    public String toString() {
        return "SimpleNameExpression{" +
                "name='" + name + '\'' +
                '}';
    }
}
