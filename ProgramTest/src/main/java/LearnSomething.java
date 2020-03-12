import AnalysisProgress.ParserDemo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import preprocessing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LearnSomething {
    private static final String FilePath = "ProgramTest/src/main/java/reduceExample/IndexValuePair_2.java";
    MethodCallExpr lastMethodCall = null;

    public static void main(String[] args) throws IOException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FilePath));
        //BroadFirstSearch(cu);

        String filename = "KeyWordList.txt";
        // DrawTree dt = new DrawTree();
        //dt.writeImage("JPG",new File("pic.jpg"),cu.getChildNodes(),"CompilationUnit");
        List<MethodCallExpr> last = new ArrayList<MethodCallExpr>();

        MixStruct ms = new MixStruct();

        cu.accept(new ExpressVisitor(),ms);
//        List<KeyWord> relatedWordList = new ArrayList<>();
//        List<KeyWord> relatedWordList1 = new ArrayList<>();
//        KeyWord list = new KeyWord("list","reduce");
//        list.setDataStructure("ElemwntList");
//        relatedWordList.add(list);
//
//        int formerNum  = 0,CurrentNum = 1;
//        while(formerNum!=CurrentNum){
//            formerNum = relatedWordList.size();
//            cu.accept(new ToolBox.CreateRelatedWordListVisitor(),relatedWordList);
//            CurrentNum = relatedWordList.size();
//        }
//        System.out.println(relatedWordList);
//
//
//        ParserDemo.CreateRelatedWordListVisitor pd = new ParserDemo.CreateRelatedWordListVisitor();
//        cu.accept(pd,relatedWordList1);
//        System.out.println("relatedWordList1: "+relatedWordList1);
//        LearnSomething ls = new LearnSomething();
//        ls.mergeTwoRelatedList(relatedWordList,relatedWordList1);
//        System.out.println("relatedWordList: "+relatedWordList);
//
//       // ToolBox.ReduceFunctionSorter rfs = new ToolBox.ReduceFunctionSorter();
//       // cu.accept(rfs,relatedWordList);
//       // System.out.println("IsSecondClass: "+rfs.isFlag());
//        DealWithInfoToFile dwitf = new DealWithInfoToFile();
//        dwitf.printKeyWordfile(relatedWordList);
//        CreateBTMFile cbf = new CreateBTMFile();
//        cbf.createBTMFile(relatedWordList,"IndexValuePair_2");

        System.out.println("ms: "+ms.getKeyList());
        LearnSomething ls = new LearnSomething();
        ls.printToFile(filename, ms.transKeyListToString());
    }

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

    public void printToFile(String filename,String content) throws IOException {
        FileWriter file = new FileWriter(filename);
        BufferedWriter output = new BufferedWriter(file);
        output.write(content);
        output.flush();
        output.close();
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
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(BinaryExpr n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("BinaryExpr: "+n);
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(MethodCallExpr n, MixStruct arg) {
            FunctionLine fl = new FunctionLine();
            super.visit(n, arg);
            System.out.println("MethodCallExpr: "+n);
            if(n.getArguments().size()!=0){
                for(int i = 0;i<n.getChildNodes().size();i++)
                    System.out.println(n.getChildNodes().get(i).getMetaModel());

                System.out.println("Argument: "+n);
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
                        List<String> strlist = new ArrayList<String>();
                        MethodCallExpr mce = arg.getLast().get(arg.getLast().size()-1);
                        Expression c = new MethodCallExpr();
                        while(true) {
                            if(!mce.getScope().toString().equals("Optional.empty")){
                                if(mce.getScope().get().isMethodCallExpr()){
                                    System.out.println("mce: "+mce);
                                    strlist.add(mce.getNameAsString());
                                    mce = mce.getScope().get().asMethodCallExpr();

                                }else{
                                    strlist.add(mce.getNameAsString());
                                    c = mce.getScope().get();
                                    arg.setKeyWordList(c.toString(), strlist);
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






        }

        @Override
        public void visit( VariableDeclarationExpr n, MixStruct arg) {
            super.visit(n, arg);
            System.out.println("VariableDeclarationExpr: "+n);
            for(int i = 0;i<n.getVariables().size();i++){
                    System.out.println("left: "+n.getVariable(i).getChildNodes().get(1).getParentNode().get().getParentNode().get().getParentNode().get().getParentNode());
            }
        }
    }


}




