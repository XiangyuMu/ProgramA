package preprocessing;

import com.github.javaparser.ast.expr.*;
import org.dom4j.Element;

public class IdentifyVariable {
    Expression formerExpression = null;

    public IdentifyVariable() {
    }

    public IdentifyVariable(Expression formerExpression) {
        this.formerExpression = formerExpression;
    }

    public Expression getFormerExpression() {
        return formerExpression;
    }

    public void setFormerExpression(Expression formerExpression) {
        this.formerExpression = formerExpression;
    }

    @Override
    public String toString() {
        return "IdentifyVariable{" +
                "formerExpression=" + formerExpression +
                '}';
    }

    public void transToIdentitedVariable(Expression e, Element fatherElement){
        this.formerExpression = e;
        System.out.println(formerExpression.getMetaModel());
        switch (formerExpression.getMetaModel().toString()){
            case "AssignExpr":{
                AssignExpression ae = new AssignExpression();
                ae.transToAssignExpression((AssignExpr) e,fatherElement);

                System.out.println(ae);
                break;
            }
            case "BinaryExpr":{
                BinaryExpression be = new BinaryExpression();
                be.transToBinaryExpression((BinaryExpr) e,fatherElement);
                System.out.println(be);
                break;
            }
            case "BooleanLiteralExpr":{
                BooleanLiteralExpression boe = new BooleanLiteralExpression();
                boe.transToBooleanLiteralExpression((BooleanLiteralExpr) e,fatherElement);
                System.out.println(boe);
                break;
            }
            case "CharLiteralExpr":{
                CharLiteralExpression cle = new CharLiteralExpression();
                cle.transToCharLiteralExpression((CharLiteralExpr) e,fatherElement);
                System.out.println(cle);
                break;
            }
            case "DoubleLiteralExpr":{
                DoubleLiteralExpression dle = new DoubleLiteralExpression();
                dle.transToDoubleLiteralExpression((DoubleLiteralExpr) e,fatherElement);
                System.out.println(dle);
                break;
            }
            case "FieldAccessExpr":{
                FieldAccessExpression fae = new FieldAccessExpression();
                fae.transToFieldAccessExpression((FieldAccessExpr) e,fatherElement);
                System.out.println(fae);
                break;
            }
            case "IntegerLiteralExpr":{
                IntegerLiteralExpression ile = new IntegerLiteralExpression();
                ile.transToIntegerLiteralExpression((IntegerLiteralExpr) e,fatherElement);
                break;
            }
            case "LongLiteralExpr":{
                LongLiteralExpression lle = new LongLiteralExpression();
                lle.transToLongLiteralExpression((LongLiteralExpr) e,fatherElement);
                System.out.println(lle);
                break;
            }
            case "MethodCallExpr":{
                MethodCallExpression mce = new MethodCallExpression();
                mce.transToMethodCallExpression((MethodCallExpr) e,fatherElement);
                System.out.println(mce);
                break;
            }
            case "NameExpr":{
                NameExpression ne = new NameExpression();
                ne.transToNameExpression((NameExpr) e,fatherElement);
                System.out.println(ne);
                break;
            }
            case "SimpleName":{
                SimpleNameExpression sne = new SimpleNameExpression();
                sne.transToSimpleNameExpression(e,fatherElement);
                System.out.println(sne);
                break;
            }
            case "StringLiteralExpr":{
                StringLiteralExpression sle = new StringLiteralExpression();
                sle.transToStringLiteralExpression((StringLiteralExpr) e,fatherElement);
                System.out.println(sle);
                break;
            }
            case "VariableDeclarationExpr":{
                VariableDeclaratorExpression vde = new VariableDeclaratorExpression();
                vde.transToVariablDeclaratorExpression(e.asVariableDeclarationExpr(),fatherElement);
                System.out.println(vde);
                break;
            }
            case "CastExpr":{
                CastExpression ce = new CastExpression();
                ce.transToCastExpression((CastExpr) e,fatherElement);
            }
            default:{
                System.out.println(formerExpression.getMetaModel().toString());
                System.out.println("can't find this type!!!!!!!!!!!!");
            }
        }
    }
}
