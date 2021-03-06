package learnJavaParser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import preprocessing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Practice {

    private static final String FilePath = "ProgramTest/src/main/java/reduceExample/IndexValuePair_1.java";

    public static void main(String[] args) throws IOException, DocumentException {

        List<Object> l  = new ArrayList<>();


        String filename = "xmlTest123.xml";
        Writer out = new PrintWriter("xmlTest123.xml", "utf-8");
        OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true);
        XMLWriter writer = new XMLWriter(out, format);
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("information");
        CompilationUnit cu = StaticJavaParser.parse(new File(FilePath));
        cu.accept(new ExpressVisitor(),root);
        writer.write(doc);
        out.close();
        writer.close();
        l = new Practice().extractTromXML("xmlTest123.xml");
        System.out.println("l: "+l);
    }

    private static class ExpressVisitor extends VoidVisitorAdapter<Element>{
        List<Integer> lineList = new ArrayList<>();
        @Override
        public void visit(VariableDeclarationExpr n, Element arg) {
            System.out.println("VariableDeclaratorExpr: "+n);
            IdentifyVariable iv = new IdentifyVariable();
            iv.transToIdentitedVariable(n,arg);
            System.out.println("iv: "+iv);
            super.visit(n, arg);
        }

        public void visit(AssignExpr n,Element arg){
            System.out.println("AssignExpr: "+n);
            IdentifyVariable iv = new IdentifyVariable();
            iv.transToIdentitedVariable(n,arg);
            System.out.println("iv: "+iv);
            super.visit(n, arg);

        }

        public void visit(ForEachStmt n,Element arg){
            System.out.println("ForEachStmt: "+n);

            IdentifyVariable iv = new IdentifyVariable();
            iv.transToIdentitedVariable(n,arg);
            System.out.println("iv: "+iv);
            super.visit(n, arg);
        }

        public void visit(MethodCallExpr n,Element arg){
            System.out.println("MethodCallExpr: "+n);
            boolean flag = false;
            System.out.println("DemoParamter: "+n.getArguments());
            for (Expression expression:n.getArguments()){
                System.out.println("Paramter: "+expression.getMetaModel());
            }

            for(Integer line : lineList){
                if(line.intValue()==n.getRange().get().begin.line){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                lineList.add(n.getRange().get().begin.line);
                IdentifyVariable iv = new IdentifyVariable();
                iv.transToIdentitedVariable(n,arg);
                System.out.println("iv: "+iv);
                super.visit(n,arg);
            }
        }
//        public void visit(ObjectCreationExpr n,Element arg){
//            System.out.println("ObjectCreationExpr: "+n);
//            IdentifyVariable iv = new IdentifyVariable();
//            iv.transToIdentitedVariable(n,arg);
//            System.out.println("iv: "+iv);
//            super.visit(n, arg);
//        }


    }

    public List<Object> extractTromXML(String fileName) throws FileNotFoundException, DocumentException {
        List<Object> objectList = new ArrayList<>();
        InputStream in = null;
        List<Element> elementList = null;
        SAXReader reader = new SAXReader();
        in = new FileInputStream(new File(fileName));
        Document doc = reader.read(in);
        Element root = doc.getRootElement();
        elementList = root.elements();
        for(Element ele : elementList){
            objectList.add(extractElement(ele));
        }
        return  objectList;
    }

    public Object extractElement(Element element){
        String name = element.getName();
        System.out.println("extractElement: "+name);
        System.out.println("element: "+element);
        switch(name){
            case "AssignExpr":{
                AssignExpression ae = new AssignExpression(element);
                System.out.println("AssignExpr: ");
                System.out.println(ae);
                return ae;
            }
            case "BinaryExpr":{
                BinaryExpression be = new BinaryExpression(element);
                System.out.println("BinaryExpr: ");
                System.out.println(be);
                return be;
            }
            case "Boolean":{
                BooleanLiteralExpression boe = new BooleanLiteralExpression(element);
                System.out.println("Boolean: ");
                System.out.println(boe);
                return boe;
            }
            case "Char":{
                CharLiteralExpression cle = new CharLiteralExpression(element);
                System.out.println("Char: ");
                System.out.println(cle);
                return cle;
            }
            case "Double":{
                DoubleLiteralExpression dle = new DoubleLiteralExpression(element);
                System.out.println("Double: ");
                System.out.println(dle);
                return dle;
            }
            case "FieldAccess":{
                FieldAccessExpression fae = new FieldAccessExpression(element);
                System.out.println("FieldAccessExpr: ");
                System.out.println(fae);
                return fae;
            }
            case "Integer":{
                IntegerLiteralExpression ile = new IntegerLiteralExpression(element);
                System.out.println("Integer: ");
                System.out.println(ile);
                return ile;
            }
            case "Long":{
                LongLiteralExpression lle = new LongLiteralExpression(element);
                System.out.println("Long: ");
                System.out.println(lle);
                return lle;
            }
            case "Method":{
                MethodCallExpression mce = new MethodCallExpression(element);
                System.out.println("Method: ");
                System.out.println(mce);
                return mce;
            }
            case "Name":{
                NameExpression ne = new NameExpression(element);
                System.out.println("Name: ");
                System.out.println(ne);
                return ne;
            }
            case "SimpleName":{
                SimpleNameExpression sne = new SimpleNameExpression(element);
                System.out.println("SimpleName: ");
                System.out.println(sne);

                return sne;
            }
            case "VariableDeclarationExpr":{
                VariableDeclaratorExpression vde = new VariableDeclaratorExpression(element);
                System.out.println("VariableDeclarationExpr: ");
                System.out.println(vde);
                return vde;
            }

            case "StringLiteralExpr":{
                StringLiteralExpression sle = new StringLiteralExpression(element);
                System.out.println("StringLiteralExpr: ");
                System.out.println(sle);
                return sle;
            }
            case "Cast":{
                CastExpression ce = new CastExpression(element);
                System.out.println("Cast: ");
                System.out.println(ce);
                return ce;
            }
            case "VariableDeclaratorInfo":{
                VariableDeclaratorInfo variableDeclaratorInfo = new VariableDeclaratorInfo(element);
                System.out.println("VariableDeclaratorInfo: ");
                System.out.println(variableDeclaratorInfo);
                return variableDeclaratorInfo;
            }
            case "ThisExpressipn":{
                ThisExpression thisExpression = new ThisExpression(element);
                System.out.println("ThisExpressipn: ");
                System.out.println(thisExpression);
                return thisExpression;
            }
            case "ObjectCreation":{
                ObjectCreation objectCreation = new ObjectCreation(element);
                System.out.println("ObjectCreation: ");
                System.out.println(objectCreation);
                return objectCreation;
            }
            default:{
                System.out.println("can't find this type!!!!!!!!!!!!");
                return null;
            }

        }
    }
}
