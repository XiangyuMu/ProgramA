
import AnalysisProgress.ParserDemo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import preprocessing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class LearnSomething {
    private static final String FilePath = "ProgramTest/src/main/java/searchOnInternet/Example02.java";

    public static void main(String[] args) throws IOException, DocumentException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FilePath));
        //BroadFirstSearch(cu);

        String filename = "xmlTest.xml";
        // DrawTree dt = new DrawTree();
        //dt.writeImage("JPG",new File("pic.jpg"),cu.getChildNodes(),"CompilationUnit");
        List<MethodCallExpr> last = new ArrayList<MethodCallExpr>();

        MixStruct ms = new MixStruct();

        cu.accept(new ExpressVisitor(),ms);
        List<KeyWord> relatedWordList = new ArrayList<>();
        List<KeyWord> relatedWordList1 = new ArrayList<>();
        KeyWord list = new KeyWord("list","reduce");
        list.setDataStructure("ElemwntList");
        relatedWordList.add(list);





        int formerNum  = 0,CurrentNum = 1;
        while(formerNum!=CurrentNum){
            formerNum = relatedWordList.size();
            cu.accept(new ToolBox.CreateRelatedWordListVisitor(),relatedWordList);
            CurrentNum = relatedWordList.size();
        }
        System.out.println("relatedWordList1: "+relatedWordList);


        ParserDemo.CreateRelatedWordListVisitor pd = new ParserDemo.CreateRelatedWordListVisitor();
        cu.accept(pd,relatedWordList1);
        System.out.println("relatedWordList2: "+relatedWordList1);
        LearnSomething ls = new LearnSomething();
        ls.mergeTwoRelatedList(relatedWordList,relatedWordList1);
        System.out.println("relatedWordList3: "+relatedWordList);

       // ToolBox.ReduceFunctionSorter rfs = new ToolBox.ReduceFunctionSorter();
       // cu.accept(rfs,relatedWordList);
       // System.out.println("IsSecondClass: "+rfs.isFlag());





        DealWithInfoToFile dwitf = new DealWithInfoToFile();
        dwitf.printKeyWordfile(relatedWordList);
        CreateBTMFile cbf = new CreateBTMFile();
        cbf.createBTMFile(relatedWordList,"IndexValuePair_2");

        System.out.println("last: "+ms.getLast());
        System.out.println("ms: "+ms.getKeyList());
        System.out.println("FunctionLine: "+ms.getLf());
        LearnSomething ls1 = new LearnSomething();

        MixStruct nms = new MixStruct();
        System.out.println("forqunce: "+ms.getForList());
        ms.sortForList();
        System.out.println("forqunce: "+ms.getForList());
        ms.completeLF();
        System.out.println("sorted: "+ms.getLf());
        ls1.printToFile(filename, ms.transKeyListToString());
       // ls1.readFromFile(filename, nms);

        //System.out.println("fl: "+nms.getLf());
    }


    public MixStruct readFromFile(String filename,MixStruct nms) throws IOException, DocumentException {
        File file = new File(filename);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(filename);
        Element root = doc.getRootElement();
        nms.getMixStruct(root);
        return nms;
    }

    public void printToFile(String filename, Document doc) throws IOException {
        Writer out = new PrintWriter("xmlTest.xml", "utf-8");
        OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true);
        XMLWriter writer = new XMLWriter(out, format);
        // 把document对象写到out流中。break;
        writer.write(doc);

        out.close();
        writer.close();
    }


//    public void readFromFile(String filename,MixStruct ms) throws IOException {
//        FileReader file = new FileReader(filename);
//        BufferedReader input = new BufferedReader(file);
//        String tempString = null;
////    	while ((tempString = input.readLine()) != null) {
////    		ms.transStringtoFunc(tempString);
////    	}
//        System.out.println("newMS: "+ms);
//    }

    public void mergeTwoRelatedList(List<KeyWord> list1,List<KeyWord> list2){
        for(int i = 0;i<list1.size();i++){
            for(int j = 0;j<list2.size();j++){
                if(list1.get(i).equals(list2.get(j))){
                    list1.get(i).setDataStructure(list2.get(j).getDataStructure());
                    break;
                }
            }
        }
    }
    public static void BroadFirstSearch(Node nodeHead){
        if(nodeHead==null) {
            return;
        }
        Stack<Node> myStack=new Stack<>();
        myStack.add(nodeHead);
        while(!myStack.isEmpty()){
            Node node = myStack.pop();
            System.out.print("Node Style is :"+node.getMetaModel());
            if(node.getChildNodes().size()!=0){
                System.out.print("  ChildNode Number is: "+node.getChildNodes().size());
            }else{
                System.out.print("  ChildNode Number is: "+0);
            }
            System.out.print("  The ChildNode's type is: ");
            for(int i  = 0;i<node.getChildNodes().size();i++){
                System.out.print(node.getChildNodes().get(i).getMetaModel()+" ; ");
            }
            System.out.println();
            for(int len = 0;len<node.getChildNodes().size();len++){
                myStack.add(node.getChildNodes().get(len));
            }
        }

    }

    private static class ExpressVisitor extends VoidVisitorAdapter<MixStruct>{
        @Override
        public void visit(ExpressionStmt e, MixStruct arg) {
            System.out.println("Statement: "+e.toString());
            super.visit(e, arg);
        }


//        @Override
//        public void visit(FieldDeclaration e, Void arg) {
//            super.visit(e, arg);
//            System.out.println("FieldDeclaration: "+e.toString());
//            for(int i = 0;i<e.getVariables().size();i++){
//                System.out.println("Name: "+e.getVariable(i).getName());
//                System.out.println("Type: "+e.getVariable(i).getType());
//                System.out.println("Init: "+e.getVariable(i).getInitializer().get());
//
//            }
//        }

        @Override
        public void visit(AssignExpr n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("AssignExpr: "+n);
            IdentifyVariable iv = new IdentifyVariable();
 //           iv.transToIdentitedVariable(n,);
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0).getMetaModel());
            }
            System.out.println("iv: "+iv);
        }

        @Override
        public void visit(BinaryExpr n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("BinaryExpr: "+n);
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        /**
         * 读取函数调用中的整个函数
         * @param n
         * @param arg
         */

        @Override
        public void visit(MethodCallExpr n, MixStruct arg) {
            //System.out.println("Range: "+n.getRange());
            FunctionLine fl = new FunctionLine();
            super.visit(n, arg);
            System.out.println("MethodCallExpr: "+n);
            if(n.getArguments().size()!=0){
                for(int i = 0;i<n.getChildNodes().size();i++)
                    System.out.println(n.getChildNodes().get(i).getMetaModel());

                System.out.println("Argument: "+n);
                System.out.println("ArgumentType: "+n.getMetaModel());
            }

            System.out.println("Name: "+n.getName());
            System.out.println("Scope: "+n.getScope());
            if(n.getScope().toString().equals("Optional.empty")){
                System.out.println("Empty");
            }else{

                System.out.println("scopeMeta: "+n.getScope().get().getMetaModel());
                System.out.println(arg);
                System.out.println(n);
                if(arg.getLast().size()==0) {
                    arg.getLast().add(n);
                }else {
                    if(n.getScope().get().getMetaModel().toString().equals("NameExpr")) {
                        List<FunctionWord> fwlist = new ArrayList<FunctionWord>();
                        MethodCallExpr mce = arg.getLast().get(arg.getLast().size()-1);
                        Expression c = new MethodCallExpr();
                        while(true) {
                            if(!mce.getScope().toString().equals("Optional.empty")){
                                if(mce.getScope().get().isMethodCallExpr()){

                                    if(mce.getArguments().size()==0) {
                                        fwlist.add(new FunctionWord(mce.getNameAsString()));
                                    }else {
                                        fwlist.add(new FunctionWord(mce.getNameAsString(), mce.getArguments()));
                                    }

                                    mce = mce.getScope().get().asMethodCallExpr();

                                }else{
                                    if(mce.getArguments().size()==0) {
                                        fwlist.add(new FunctionWord(mce.getNameAsString()));
                                    }else {
                                        fwlist.add(new FunctionWord(mce.getNameAsString(), mce.getArguments()));
                                    }
                                    c = mce.getScope().get();
                                    fl.setName(c.toString());
                                    fl.setFunctionName(fwlist);
                                    fl.setLine(mce.getRange().get().begin.line);
                                    System.out.println("mce: "+mce);
                                    System.out.println("the range line: "+ mce.getRange());
                                    System.out.println("funcLine: "+fl);
                                    arg.getLf().add(fl);
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        System.out.println("childNode: "+arg.getLast().get(arg.getLast().size()-1).getChildNodes());
                        System.out.println("final Method: "+arg.getLast().get(arg.getLast().size()-1));
                        System.out.println("fl: "+fl);
                        arg.getLast().add(n);
                        System.out.println("arg: "+arg);
                    }else {
                        arg.getLast().add(n);
                    }
                }


            }
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(ForEachStmt n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("Range: "+n.getRange().get().begin);
            System.out.println("ForEachStmt: "+n);

            System.out.println("getIterable: "+n.getIterable());
            System.out.println("getVariable: "+n.getVariable().getChildNodes());
            System.out.println("count: "+n.getVariable().getVariables().size());
            Stack<Node> stack = new Stack<>();
            Expression right = n.getIterable();
            for(int j = 0;j<right.getChildNodes().size();j++){
                stack.add(right.getChildNodes().get(j));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    System.out.println("ChildNode:"+node);
                    System.out.println("ChildType: "+node.getMetaModel());
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            for(int a = 0;a<n.getBody().getChildNodes().size();a++){
                System.out.println("forBody: "+n.getBody().getChildNodes().get(a).getChildNodes().get(0).getMetaModel());
            }

            arg.getForList().add(new ForInfo(n.getRange().get().begin.line, n.getRange().get().end.line));

        }

        @Override
        public void visit( VariableDeclarationExpr n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("VariableDeclarationExpr: "+n);
            for(int i = 0;i<n.getVariables().size();i++){
                System.out.println("left: "+n.getVariable(i).getChildNodes().get(1).getParentNode().get().getParentNode().get().getParentNode().get().getParentNode());
            }
        }

        @Override
        public void visit(ForStmt n, MixStruct arg) {
            // TODO Auto-generated method stub
            super.visit(n, arg);
            arg.getForList().add(new ForInfo(n.getRange().get().begin.line, n.getRange().get().end.line));
        }

        @Override
        public void visit(WhileStmt n, MixStruct arg) {
            // TODO Auto-generated method stub
            super.visit(n, arg);
            arg.getForList().add(new ForInfo(n.getRange().get().begin.line, n.getRange().get().end.line));
        }
    }


}




