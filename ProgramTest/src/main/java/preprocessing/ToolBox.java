package preprocessing;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ToolBox {
    private List<String> relatedWordList = new ArrayList<>();
    public ToolBox(){
        relatedWordList.add("list");
    }
    public List<String> getRelatedWordList() {
        return relatedWordList;
    }
    public void setRelatedWordList(List<String> relatedWordList) {
        this.relatedWordList = relatedWordList;

    }
    public static class CreateRelatedWordListVisitor extends VoidVisitorAdapter<List<String>>{

        public void pre_createAtomWords(Expression e,List<String> atomWords){
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<e.getChildNodes().size();i++){
                stack.add(e.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        atomWords.add(node.toString());
                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
        }

        @Override
        public void visit(MethodCallExpr n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            System.out.println("MethodCallExpr: "+n);
            boolean flag;
            MethodCallExpr e = n;
            Expression c = new MethodCallExpr();
            while(true){
                for(int i = 0;i<e.getArguments().size();i++){
                    pre_createAtomWords(e,atomWords);
                   // atomWords.add(e.getArgument(i).toString());
                }
                if(!e.getScope().toString().equals("Optional.empty")){
                    if(e.getScope().get().isMethodCallExpr()){
                        e = e.getScope().get().asMethodCallExpr();
                    }else{
                        c = e.getScope().get();
                        break;
                    }
                }else{
                    break;
                }
            }
            if(atomWords.size()!=0&&!c.isMethodCallExpr()){
                System.out.println("atomWords: "+atomWords);
                for(int m = 0;m<atomWords.size();m++){
                    flag = false;
                    for(int o = 0;o<arg.size();o++){
                        if(atomWords.get(m).equals(arg.get(o))){
                            flag = true;
                            break;
                        }
                    }
                    if(flag&&!arg.contains(c.toString())){
                        arg.add(c.toString());
                    }
                }
            }





//            int ArgumentCount = n.getArguments().size();
//            if(ArgumentCount!=0){
//                for(int i = 0;i<ArgumentCount;i++){
//                    flag = false;
//                    for(int j = 0;j<arg.size();j++){
//                        if(n.getArgument(i).toString().equals(arg.get(j))){
//                            flag = true;
//                            break;
//                        }
//                    }
//                    if(flag){
//                        MethodCallExpr e = n;
//                        if (!e.getScope().toString().equals("Optional.empty")){
//                            while(e.getScope().get().isMethodCallExpr()){
//                                e = e.getScope().get().asMethodCallExpr();
//                            }
//                            arg.add(e.getScope().get().toString());
//                        }
//                    }
//                }
//            }
            System.out.println("List: "+arg);
        }
        @Override
        public void visit(AssignExpr n, List<String> arg) {
            super.visit(n, arg);
            System.out.println("Assign: "+n);
            boolean flag;
            Expression left = n.getTarget();
            Expression right = n.getValue();
            List<String> atomWords = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<right.getChildNodes().size();i++){
                stack.add(right.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        atomWords.add(node.toString());
                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            if(left.isNameExpr()){
                System.out.println("atomWords: "+atomWords);
                if(atomWords.size()!=0){
                    for(int m = 0;m<atomWords.size();m++){
                        flag = false;
                        for(int o = 0;o<arg.size();o++){
                            if(atomWords.get(m).equals(arg.get(o))){
                                flag = true;
                                break;
                            }
                        }
                        if(flag&&!arg.contains(left.toString())){
                            arg.add(left.toString());
                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(FieldDeclaration n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            boolean flag;
            System.out.println("Field: "+n);
            for(int i = 0;i<n.getVariables().size();i++){
                Expression left = n.getVariable(i).getNameAsExpression();
                Expression right = n.getVariable(i).getInitializer().get();
                Stack<Node> stack = new Stack<>();
                for(int j = 0;j<right.getChildNodes().size();j++){
                    stack.add(right.getChildNodes().get(j));
                }
                while(!stack.isEmpty()){
                    Node node = stack.pop();
                    if(node.getChildNodes().size()==0){
                        if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                            atomWords.add(node.toString());
                        }
                    }else{
                        for(int j = 0;j<node.getChildNodes().size();j++){
                            stack.add(node.getChildNodes().get(j));
                        }
                    }
                }
                if(atomWords.size()!=0){
                    System.out.println("atomWords: "+atomWords);
                    for(int m = 0;m<atomWords.size();m++){
                        flag = false;
                        for(int o = 0;o<arg.size();o++){
                            if(atomWords.get(m).equals(arg.get(o))){
                                flag = true;
                                break;
                            }
                        }
                        if(flag&&!arg.contains(left.toString())){
                            arg.add(left.toString());
                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(ForEachStmt n, List<String> arg) {
            super.visit(n, arg);
            boolean flag ;
            System.out.println("ForEach: "+n);
            List<String> atomWords = new ArrayList<>();
            Expression right = n.getIterable();
            Stack<Node> stack = new Stack<>();
            for(int j = 0;j<right.getChildNodes().size();j++){
                stack.add(right.getChildNodes().get(j));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        atomWords.add(node.toString());
                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            if(atomWords.size()!=0){
                for(int m = 0;m<atomWords.size();m++){
                    flag = false;
                    for(int o = 0;o<arg.size();o++){
                        if(atomWords.get(m).equals(arg.get(o))){
                            flag = true;
                            break;
                        }
                    }
                    if(flag&&!arg.contains(n.getVariable().getVariable(0).getName().toString())){
                        arg.add(n.getVariable().getVariable(0).getName().toString());
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(VariableDeclarationExpr n, List<String> arg) {
            super.visit(n, arg);
            Expression right;
            Expression left;
            System.out.println("VariableDec: "+n);
            List<String> atomWords = new ArrayList<>();
            for(int i = 0;i<n.getVariables().size();i++){
                if (!n.getVariable(i).getInitializer().toString().equals("Optional.empty")){
                    boolean flag ;
                    right = n.getVariable(i).getInitializer().get();
                    left = n.getVariable(i).getNameAsExpression();
                    Stack<Node> stack = new Stack<>();
                    for(int j = 0;j<right.getChildNodes().size();j++){
                        stack.add(right.getChildNodes().get(j));
                    }
                    while(!stack.isEmpty()){
                        Node node = stack.pop();
                        if(node.getChildNodes().size()==0){
                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                                atomWords.add(node.toString());
                            }
                        }else{
                            for(int j = 0;j<node.getChildNodes().size();j++){
                                stack.add(node.getChildNodes().get(j));
                            }
                        }
                    }
                    if(atomWords.size()!=0){
                        for(int m = 0;m<atomWords.size();m++){
                            flag = false;
                            for(int o = 0;o<arg.size();o++){
                                if(atomWords.get(m).equals(arg.get(o))){
                                    flag = true;
                                    break;
                                }
                            }
                            if(flag){
                                if(!arg.contains(left.toString())){
                                    arg.add(left.toString());
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }
    }

    public static class ReduceFunctionSorter extends VoidVisitorAdapter<List<String>>{
        private boolean flag = true;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void visit(VariableDeclarationExpr n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            for(int i = 0;i<n.getVariables().size();i++){
                if(n.getVariable(i).getChildNodes().size()!=0){
                    Stack<Node> stack = new Stack<>();
                    for(int j = 0;j<n.getVariable(i).getChildNodes().size();j++){
                        stack.add(n.getVariable(i).getChildNodes().get(j));
                    }
                    while(!stack.isEmpty()){
                        Node node = stack.pop();
                        if(node.getChildNodes().size()==0){
                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                                atomWords.add(node.toString());
                            }
                        }else{
                            for(int j = 0;j<node.getChildNodes().size();j++){
                                stack.add(node.getChildNodes().get(j));
                            }
                        }
                    }
                }
            }

            boolean flag1 = false;
            boolean flag2 = false;
            String str = "";
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(flag1==false&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        str = atomWords.get(a);
                    }else if(flag1==true&&atomWords.get(a).equals(arg.get(b))&&flag2==false&&str!=atomWords.get(a)){
                        flag2 = true;
                    }else if(flag1 == true&&flag2 == true){
                        break;
                    }
                }
                if(flag1 == true&&flag2 == true){
                    System.out.println("VariableDeclarationExpr2: "+n);
                    flag = true;
                }
            }
        }

        @Override
        public void visit(AssignExpr n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            Expression right = n.getValue();
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<right.getChildNodes().size();i++){
                stack.add(right.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        atomWords.add(node.toString());
                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            boolean flag1 = false;
            boolean flag2 = false;
            String str = "";
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(flag1==false&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        str = atomWords.get(a);
                    }else if(flag1==true&&atomWords.get(a).equals(arg.get(b))&&flag2==false&&str!=atomWords.get(a)){
                        flag2 = true;
                    }else if(flag1 == true&&flag2 == true){
                        break;
                    }
                }
                if(flag1 == true&&flag2 == true){
                    System.out.println("AssignExpr2: "+n);
                    flag = true;
                }
            }
        }

        @Override
        public void visit(MethodCallExpr n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            MethodCallExpr e = n;
            while(true){
                for(int i = 0;i<e.getArguments().size();i++){
                    atomWords.add(e.getArgument(i).toString());
                }
                if(!e.getScope().toString().equals("Optional.empty")){
                    if(e.getScope().get().isMethodCallExpr()){
                        e = e.getScope().get().asMethodCallExpr();
                        //System.out.println("asMethodCallExpr: "+e);
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }


            boolean flag1 = false;
            boolean flag2 = false;
            String str = "";
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(flag1==false&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        str = atomWords.get(a);
                    }else if(flag1==true&&atomWords.get(a).equals(arg.get(b))&&flag2==false&&str!=atomWords.get(a)){
                        flag2 = true;
                    }else if(flag1 == true&&flag2 == true){
                        break;
                    }
                }
                if(flag1 == true&&flag2 == true){
                    System.out.println("MethodCallExpr2: "+n);
                    flag = true;
                }
            }

        }

        @Override
        public void visit(FieldDeclaration n, List<String> arg) {
            super.visit(n, arg);
            List<String> atomWords = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<n.getVariables().size();i++){
                if(n.getVariable(i).getChildNodes().size()!=0){
                    for(int j = 0;j<n.getVariable(i).getChildNodes().size();j++){
                        stack.add(n.getVariable(i).getChildNodes().get(j));
                    }
                    while(!stack.isEmpty()){
                        Node node = stack.pop();
                        if(node.getChildNodes().size()==0){
                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                                atomWords.add(node.toString());
                            }
                        }else{
                            for(int j = 0;j<node.getChildNodes().size();j++){
                                stack.add(node.getChildNodes().get(j));
                            }
                        }
                    }
                }
            }
            boolean flag1 = false;
            boolean flag2 = false;
            String str = "";
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(flag1==false&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        str = atomWords.get(a);
                    }else if(flag1==true&&atomWords.get(a).equals(arg.get(b))&&flag2==false&&str!=atomWords.get(a)){
                        flag2 = true;
                    }else if(flag1 == true&&flag2 == true){
                        break;
                    }
                }
                if(flag1 == true&&flag2 == true){
                    System.out.println("FieldDeclaration2: "+n);
                    flag = true;
                }
            }
        }
    }
}
