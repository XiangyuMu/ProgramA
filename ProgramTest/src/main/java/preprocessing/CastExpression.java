package preprocessing;

import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.Expression;
import learnJavaParser.Practice;
import org.dom4j.Element;
import reduceExample.Atom;

import java.util.ArrayList;
import java.util.List;

public class CastExpression {
    @Override
    public String toString() {
        return "CastExpression{" +
                "type='" + type + '\'' +
                ", expression=" + expression +
                ", objectList=" + objectList +
                '}';
    }

    String type;
    Expression expression;
    List<Object> objectList = new ArrayList<>();
    public CastExpression(Element element) {
        type = element.attributeValue("type");
        List<Element> elementList = element.elements();
        if (element.elements().size()!=0){
            for(Element ele : elementList){
                objectList.add(new Practice().extractElement(ele))  ;
            }
        }

    }

    public CastExpression() {

    }

    public void transToCastExpression(CastExpr e, Element fatherElement) {
        type = e.getTypeAsString();
        expression = e.getExpression();
        Element childElement = printXML(fatherElement);
        IdentifyVariable iv = new IdentifyVariable();
        iv.transToIdentitedVariable(expression,childElement);
    }

    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("Cast");
        childElement.addAttribute("type",type);
        return childElement;
    }



    public void getKeyWordList(List<KeyWord> keyWordList) {
        for (Object ob:objectList){
            List<KeyWord> list = new ArrayList<>();
            list = new Tool().getKeyWord(ob);
            for (int i = 0;i<list.size();i++){
                if (list.size()>0){
                    keyWordList.add(list.get(i));
                }
            }
        }
    }
}
