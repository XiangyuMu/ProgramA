package preprocessing;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import org.dom4j.Element;

import java.util.List;

public class AssignExpression {
    Expression value;
    int line  = 0;
    Expression target;
    String operator;

    public int getLine() {
        return line;
    }

    public AssignExpression(){
        super();
    }

    public AssignExpression(Element element){
        super();

    }

    @Override
    public String toString() {
        return "AssignExpression{" +
                "value=" + value +
                ", line=" + line +
                ", target=" + target +
                ", operator='" + operator + '\'' +
                '}';
    }

    public void transToAssignExpression(AssignExpr e,Element fatherElement){
        this.value = e.getValue();
        this.target = e.getTarget();
        this.line = e.getRange().get().begin.line;
        this.operator = e.getOperator().asString();
        Element childElement = printlnToXML(fatherElement);
        new IdentifyVariable().transToIdentitedVariable(target,childElement);
        new IdentifyVariable().transToIdentitedVariable(value,childElement);

    }

    public Element printlnToXML(Element fatherElement){
        Element childElement;
        childElement = fatherElement.addElement("Assign");
        childElement.addAttribute("operator",operator);
        childElement.addAttribute("line", String.valueOf(line));

        return childElement;
    }

    public void getKeyWordList(List<KeyWord> keyWordList) {

    }
}
