package preprocessing;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclaratorExpression {

    int line = 0;
    List<VariableDeclaratorInfo> lvd = new ArrayList<>();

    public VariableDeclaratorExpression(Element element) {
    }

    public VariableDeclaratorExpression() {
    }

    @Override
    public String toString() {
        return "VariableDeclaratorExpression{" +
                "line=" + line +
                ", lvd=" + lvd +
                '}';
    }

    public void transToVariablDeclaratorExpression(VariableDeclarationExpr asVariableDeclarationExpr, Element fatherElement) {
        line = asVariableDeclarationExpr.getRange().get().begin.line;
        for (int i = 0;i<asVariableDeclarationExpr.getVariables().size();i++){
            VariableDeclaratorInfo vdi = new VariableDeclaratorInfo();
            vdi.transToVariablDeclarator(asVariableDeclarationExpr.getVariable(i),fatherElement);
            lvd.add(vdi);
        }

    }
}
