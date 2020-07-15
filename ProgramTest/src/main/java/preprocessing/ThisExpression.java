package preprocessing;

import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import org.dom4j.Element;


public class ThisExpression {

    String name;

    public ThisExpression(){

    }

    public ThisExpression(Element element){
        name = element.getText();
    }

    public void transToThisExpression(ThisExpr e, org.dom4j.Element fatherElement) {
        name = e.toString();
        printXML(fatherElement);
        System.out.println(e);
    }

    @Override
    public String toString() {
        return "ThisExpression{" +
                "name='" + name + '\'' +
                '}';
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("ThisExpressipn");
        childElement.addText(name);
        return childElement;
    }
}
