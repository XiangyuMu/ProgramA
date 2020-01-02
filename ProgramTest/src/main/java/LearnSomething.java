import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import preprocessing.ToolBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LearnSomething {
    private static final String FilePath = "ProgramTest/src/main/java/searchOnInternet/Example03.java";


    public static void main(String[] args) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FilePath));
        //BroadFirstSearch(cu);

       // DrawTree dt = new DrawTree();
        //dt.writeImage("JPG",new File("pic.jpg"),cu.getChildNodes(),"CompilationUnit");


        //cu.accept(new ExpressVisitor(),null);
        List<String> relatedWordList = new ArrayList<>();
        relatedWordList.add("list");
        int formerNum  = 0,CurrentNum = 1;
        while(formerNum!=CurrentNum){
            formerNum = relatedWordList.size();
            cu.accept(new ToolBox.CreateRelatedWordListVisitor(),relatedWordList);
            CurrentNum = relatedWordList.size();
        }
        System.out.println(relatedWordList);
        ToolBox.ReduceFunctionSorter rfs = new ToolBox.ReduceFunctionSorter();
        cu.accept(rfs,relatedWordList);
        System.out.println("IsSecondClass: "+rfs.isFlag());

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

    private static class ExpressVisitor extends VoidVisitorAdapter<Void>{
        @Override
        public void visit(ExpressionStmt e, Void arg) {
            System.out.println("Statement: "+e.toString());
            super.visit(e, arg);
        }


        @Override
        public void visit(FieldDeclaration e, Void arg) {
            super.visit(e, arg);
            System.out.println("FieldDeclaration: "+e.toString());
            for(int i = 0;i<e.getVariables().size();i++){
                System.out.println("Name: "+e.getVariable(i).getName());
                System.out.println("Type: "+e.getVariable(i).getType());
                System.out.println("Init: "+e.getVariable(i).getInitializer().get());

            }
        }

        @Override
        public void visit(AssignExpr n, Void arg) {
            super.visit(n, arg);
            System.out.println("AssignExpr: "+n);
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(BinaryExpr n, Void arg) {
            super.visit(n, arg);
            System.out.println("BinaryExpr: "+n);
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(MethodCallExpr n, Void arg) {
            super.visit(n, arg);
            System.out.println("MethodCallExpr: "+n);
            System.out.println("Argument: "+n.getArguments());
            System.out.println("Name: "+n.getName());
            System.out.println("Scope: "+n.getScope());
            if(n.getScope().toString().equals("Optional.empty")){
                System.out.println("Empty");
            }else{
                System.out.println(n.getScope().get().getMetaModel());
            }
            if(n.getChildNodes().size()!=0){
                System.out.println("Node: "+n.getChildNodes().get(0));
            }
        }

        @Override
        public void visit(ForEachStmt n, Void arg) {
            super.visit(n, arg);
            System.out.println("ForEachStmt: "+n);
            System.out.println("getIterable: "+n.getIterable());
            System.out.println("getVariable: "+n.getVariable());
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
                System.out.println("forBody: "+n.getBody().asBlockStmt().getStatement(0));
            }






        }

        @Override
        public void visit( VariableDeclarationExpr n, Void arg) {
            super.visit(n, arg);
            System.out.println("VariableDeclarationExpr: "+n);
            for(int i = 0;i<n.getVariables().size();i++){
                System.out.println("Va: "+n.getVariable(i).getInitializer());
            }
        }
    }


}




