package preprocessing;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import learnJavaParser.Practice;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclaratorInfo {
    SimpleName name;
    Expression init;
    int line = 0;
    String type = "";


    public SimpleName getName() {
        return name;
    }

    public void setName(SimpleName name) {
        this.name = name;
    }

    public Expression getInit() {
        return init;
    }

    public void setInit(Expression init) {
        this.init = init;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    @Override
    public String toString() {
        return "VariableDeclaratorInfo{" +
                "name=" + name +
                ", init=" + init +
                ", line=" + line +
                ", type='" + type + '\'' +
                ", nameStr='" + nameStr + '\'' +
                ", objectList=" + objectList +
                '}';
    }

    String nameStr;
    List<Object> objectList = new ArrayList<>();
    public VariableDeclaratorInfo(Element element) {
        nameStr = element.attributeValue("name");
        line = Integer.parseInt(element.attributeValue("line"));
        type = element.attributeValue("type");
        List<Element> elementList = element.elements();
        if (element.elements().size()!=0){
            for(Element ele : elementList){
                objectList.add(new Practice().extractElement(ele));
            }
        }
    }



    public VariableDeclaratorInfo() {
    }

    public void transToVariablDeclarator(VariableDeclarator e, Element fatherElement){
        line = e.getRange().get().begin.line;
        type = e.getTypeAsString();
        this.name = e.getName();
        Element childElement = printXML(fatherElement);
        if(e.getInitializer().toString().equals("Optional.empty")){
            this.init = null;
        }else{
            this.init = e.getInitializer().get();
            System.out.println("init: "+init);
            new IdentifyVariable().transToIdentitedVariable(init,childElement);
        }


    }
    public Element printXML(Element fatherElement){
        Element childElement = fatherElement.addElement("VariableDeclaratorInfo");
        childElement.addAttribute("name",name.asString());
        childElement.addAttribute("line", String.valueOf(line));
        childElement.addAttribute("type",type);
        return childElement;
    }

    public void getKeyWordList(List<KeyWord> keyWordList) {
        keyWordList.add(new KeyWord(nameStr));
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
