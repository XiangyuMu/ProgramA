package preprocessing;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import org.dom4j.Element;

import java.util.Iterator;

public class Para {
    @Override
    public String toString() {
        return "Para [strAssign=" + para + ", fl=" + fl + "]";
    }

    String para;   //"#1"
    FunctionLine fl = new FunctionLine() ;      //"#2"

    public String getStrAssign() {
        return para;
    }

    public void setStrAssign(String strAssign) {
        this.para = strAssign;
    }

    public FunctionLine getFl() {
        return fl;
    }

    public void setFl(FunctionLine fl) {
        this.fl = fl;
    }

    public Para(){

    }

    public Para(String str) {
        para = str;
        fl = null;
    }

    public Para(MethodCallExpr e) {
        if(e.getScope().toString().equals("Optional.empty")) {
            if(e.getArguments().size()!=0) {
                fl.getFunctionName().add(new FunctionWord(e.getNameAsString(),e.getArguments()));
            }else {
                fl.getFunctionName().add(new FunctionWord(e.getNameAsString()));
            }

        }else {
            MethodCallExpr tempE ;
            tempE = e;
            Expression c;
            while(true){
                if(!tempE.getScope().toString().equals("Optional.empty")){
                    if(tempE.getScope().get().isMethodCallExpr()){
                        if(tempE.getArguments().size()!=0) {
                            fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString(),tempE.getArguments()));
                        }else {
                            fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString()));
                        }

                        tempE = tempE.getScope().get().asMethodCallExpr();
                    }else{
                        if(tempE.getArguments().size()!=0) {
                            fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString(),tempE.getArguments()));
                        }else {
                            fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString()));
                        }
                        c = tempE.getScope().get();
                        fl.setName(c.toString());
                        break;
                    }
                }else{
                    if(tempE.getArguments().size()!=0) {
                        fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString(),tempE.getArguments()));
                    }else {
                        fl.getFunctionName().add(new FunctionWord(tempE.getNameAsString()));
                    }
                    break;
                }
            }
        }
    }

    public Element transAssignToXML(Element fatherElement) {
        Element childElement = null;
        childElement = fatherElement.addElement("Para");
        if(para!=null) {
            childElement.addElement("strPara").addText(para);
        }
        if(fl!=null) {

            fl.transFuncToXML(childElement);
        }

        return childElement;
    }

    public void getAssign(Element fatherElement){
        for(Iterator i = fatherElement.elementIterator();i.hasNext();){
            Element element = (Element)i.next();
            if (element.getName().equals("strAssign")){
                para = element.getText();
            }else{
                fl.getFunctionLine(element);
            }
        }
    }
}
