package preprocessing;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ToolBox {
    private List<KeyWord> relatedWordList = new ArrayList<>();
    KeyWord list = new KeyWord("list","reduce");
    public ToolBox(){
        relatedWordList.add(list);
    }
    public List<KeyWord> getRelatedWordList() {
        return relatedWordList;
    }
    public void setRelatedWordList(List<KeyWord> relatedWordList) {
        this.relatedWordList = relatedWordList;

    }
    public static class CreateRelatedWordListVisitor extends VoidVisitorAdapter<List<KeyWord>>{




        public void pre_createAtomWords(Expression e,List<KeyWord> atomWords){              //Break down into nodes
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<e.getChildNodes().size();i++){
                stack.add(e.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean")||node.toString().equals("System.out"))){
                            atomWords.add(new KeyWord(node));
                        }

                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
        }

        @Override
        public void visit(ArrayAccessExpr n, List<KeyWord> arg) {
            super.visit(n, arg);
            System.out.println("ArrayAccessExpr: "+n);
            List<KeyWord> atomWords = new ArrayList<>();

            //System.out.println("Node:"+n+" ParentNode: "+n.getParentNode().get());
            Node node = n.getParentNode().get();
            //System.out.println(node.getParentNode().get().getParentNode().get().getParentNode().get().getParentNode().get().getParentNode().get().getBegin());
            pre_createAtomWords(n.getIndex(),atomWords);

            Expression left = n.getName();
            boolean flag;
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
                        boolean flag1 = false;
                        KeyWord keyword = new KeyWord(left);
                        for(int argcount = 0;argcount<arg.size();argcount++){
                            if(arg.get(argcount).equals(keyword)){
                                flag1 = true;
                            }

                        }

                        if(flag&&!flag1){
                            arg.add(new KeyWord(left));
                        }
//                        if(flag&&!arg.contains(new KeyWord(left))){
//                            arg.add(new KeyWord(left));
//                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(MethodCallExpr n, List<KeyWord> arg) {                     //Add MethodCallExpr Expression note to list
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
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

                    boolean flag1 = false;
                    KeyWord keyword = new KeyWord(c);
                    for(int argcount = 0;argcount<arg.size();argcount++){

                        if(arg.get(argcount).equals(keyword)){
                            flag1 = true;
                        }
//                        if (flag&&!arg.get(argcount).equals(keyword)){
//                            if(!(c.toString().equals("Float")||c.toString().equals("Integer")||c.toString().equals("String")||c.toString().equals("Char")||c.toString().equals("Boolean")||c.toString().equals("System.out"))){
//                                arg.add(keyword);
//                                break;
//                            }
//                        }
                    }
                    if(flag&&!flag1){
                        if(!(c.toString().equals("Float")||c.toString().equals("Integer")||c.toString().equals("String")||c.toString().equals("Char")||c.toString().equals("Boolean")||c.toString().equals("System.out"))) {
                            arg.add(keyword);
                            break;
                        }
                    }
                    
//                    if(flag&&!arg.contains(new KeyWord(c))){
//                        if(!(c.toString().equals("Float")||c.toString().equals("Integer")||c.toString().equals("String")||c.toString().equals("Char")||c.toString().equals("Boolean")||c.toString().equals("System.out"))){
//                            arg.add(new KeyWord(c));
//                        }
//
//                    }
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
        public void visit(AssignExpr n, List<KeyWord> arg) {                  //Add AssignExpr Expression note to list
            super.visit(n, arg);
            System.out.println("Para: "+n);
            boolean flag;
            Expression left = n.getTarget();
            Expression right = n.getValue();
            List<KeyWord> atomWords = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<right.getChildNodes().size();i++){

                stack.add(right.getChildNodes().get(i));
                System.out.println("Type: "+n.getOperator());
                System.out.println("AssignNode: "+right.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
                            atomWords.add(new KeyWord(node));
                        }
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

                        boolean flag1 = false;
                        KeyWord keyword = new KeyWord(left);
                        for(int argcount = 0;argcount<arg.size();argcount++){
                            if(arg.get(argcount).equals(keyword)){
                                flag1 = true;
                            }

                        }

                        if(flag&&!flag1){
                            arg.add(new KeyWord(left));
                        }
//                        if(flag&&!arg.contains(new KeyWord(left))){
//                            arg.add(new KeyWord(left));
//                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(FieldDeclaration n, List<KeyWord> arg) {                   //Add FieldDeclaration Expression note to list
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
            System.out.println("Node:"+n);
            //System.out.println("Node:"+n+" ParentNode: "+n.getParentNode().get()+" ParentNodeModel: "+n.getParentNode().get().getMetaModel());
            //Node node1 = n.getParentNode().get();
            //System.out.println(node1);
            boolean flag;
            System.out.println("Field: "+n);
            for(int i = 0;i<n.getVariables().size();i++){
                Expression left = n.getVariable(i).getNameAsExpression();
                System.out.println(n.getVariable(i).getInitializer().toString().equals("Optional.empty"));
                if(n.getVariable(i).getInitializer().toString().equals("Optional.empty")){
                    break;
                }
                Expression right = n.getVariable(i).getInitializer().get();
                Stack<Node> stack = new Stack<>();
                for(int j = 0;j<right.getChildNodes().size();j++){
                    stack.add(right.getChildNodes().get(j));
                }
                while(!stack.isEmpty()){
                    Node node = stack.pop();
                    if(node.getChildNodes().size()==0){
                        if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                            if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
                                atomWords.add(new KeyWord(node));
                            }
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
                        boolean flag1 = false;
                        KeyWord keyword = new KeyWord(n.getVariable(0));
                        for(int argcount = 0;argcount<arg.size();argcount++){
                            if(arg.get(argcount).equals(keyword)){
                                flag1 = true;
                            }
                        }

                        if(flag&&!flag1){
                            arg.add(keyword);
                        }
//                        if(flag&&!arg.contains(new KeyWord(left))){
//                            arg.add(new KeyWord(left));
//                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(ForEachStmt n, List<KeyWord> arg) {                  //Add ForEachStmt Expression note to list
            super.visit(n, arg);
            boolean flag ;
            System.out.println("ForEach: "+n);
            List<KeyWord> atomWords = new ArrayList<>();
            Expression right = n.getIterable();
            Stack<Node> stack = new Stack<>();
            for(int j = 0;j<right.getChildNodes().size();j++){
                stack.add(right.getChildNodes().get(j));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
                            atomWords.add(new KeyWord(node));
                        }
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

                    boolean flag1 = false;
                    KeyWord keyword = new KeyWord(n.getVariable().getChildNodes().get(0));
                    for(int argcount = 0;argcount<arg.size();argcount++){
                        if(arg.get(argcount).equals(keyword)){
                            flag1 = true;
                        }

                    }

                    if(flag&&!flag1){
                        arg.add(keyword);
                    }

//                    for(int argcount = 0;argcount<arg.size();argcount++){
//                        if (flag&&!arg.get(argcount).equals(new KeyWord(n.getVariable().getChildNodes().get(0)))){
//                            arg.add(new KeyWord(n.getVariable().getChildNodes().get(0)));
//                            break;
//                        }
//                    }
//                    if(flag&&!arg.contains(new KeyWord(n.getVariable().getChildNodes().get(0)))){
//                        arg.add(new KeyWord(n.getVariable().getChildNodes().get(0)));
//                    }
                }
            }
            System.out.println("List: "+arg);
        }

        @Override
        public void visit(VariableDeclarationExpr n, List<KeyWord> arg) {            ////Add VariableDeclarationExpr Expression note to list
            super.visit(n, arg);
            Expression right;
            Node left;
            System.out.println("VariableDec: "+n);
            List<KeyWord> atomWords = new ArrayList<>();
            for(int i = 0;i<n.getVariables().size();i++){
                if (!n.getVariable(i).getInitializer().toString().equals("Optional.empty")){
                    boolean flag ;
                    right = n.getVariable(i).getInitializer().get();

                    left = n.getVariable(i).getChildNodes().get(1);
                    Stack<Node> stack = new Stack<>();
                    for(int j = 0;j<right.getChildNodes().size();j++){
                        stack.add(right.getChildNodes().get(j));
                    }
                    while(!stack.isEmpty()){
                        Node node = stack.pop();

                        if(node.getChildNodes().size()==0){
                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                                if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
//                                    System.out.println("node: "+node+" model: "+node.getMetaModel()+"Childside: "+node.getChildNodes().size());
                                    atomWords.add(new KeyWord(node));
                                }
                            }
                        }else{
                            for(int j = 0;j<node.getChildNodes().size();j++){
                                stack.add(node.getChildNodes().get(j));
                            }
                        }
                    }
                    boolean flag1 = false;
                    KeyWord keyword = new KeyWord(left);
                    if(atomWords.size()!=0){
                        System.out.println("run to here!");
                        System.out.println(atomWords);
                        for(int m = 0;m<atomWords.size();m++){
                            flag = false;
                            for(int o = 0;o<arg.size();o++){
                                if(atomWords.get(m).equals(arg.get(o))){
                                    flag = true;
                                    break;
                                }
                            }
                            for(int argcount = 0;argcount<arg.size();argcount++){
                                if(arg.get(argcount).equals(keyword)){
                                    flag1 = true;
                                }

                            }

                            if(flag&&!flag1){
                                //keyword.setDataStructure(n.getVariable(i).getTypeAsString());
                                arg.add(keyword);
                            }
//                            if(flag){
//                                if(!arg.contains(new KeyWord(left))){
//                                    System.out.println(left);
//                                    arg.add(new KeyWord(left));
//                                }
//                            }
                        }
                    }
                }
            }
            System.out.println("List: "+arg);
        }
    }

    public static class ReduceFunctionSorter extends VoidVisitorAdapter<List<KeyWord>>{          //the reduce function is the first type or the second type
        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void visit(VariableDeclarationExpr n, List<KeyWord> arg) {
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
            Expression right;
            for(int i = 0;i<n.getVariables().size();i++){
                if (!n.getVariable(i).getInitializer().toString().equals("Optional.empty")){
                    boolean flag ;
                    right = n.getVariable(i).getInitializer().get();
                    Stack<Node> stack = new Stack<>();
                    for(int j = 0;j<right.getChildNodes().size();j++){
                        stack.add(right.getChildNodes().get(j));
                    }
                    while(!stack.isEmpty()){
                        Node node = stack.pop();

                        if(node.getChildNodes().size()==0){
                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                                if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
//                                    System.out.println("node: "+node+" model: "+node.getMetaModel()+"Childside: "+node.getChildNodes().size());
                                    atomWords.add(new KeyWord(node));
                                }
                            }
                        }else{
                            for(int j = 0;j<node.getChildNodes().size();j++){
                                stack.add(node.getChildNodes().get(j));
                            }
                        }
                    }
                }
            }

//            for(int i = 0;i<n.getVariables().size();i++){
//                if(n.getVariable(i).getChildNodes().size()!=0){
//                    Stack<Node> stack = new Stack<>();
//                    for(int j = 0;j<n.getVariable(i).getChildNodes().size();j++){
//                        stack.add(n.getVariable(i).getChildNodes().get(j));
//                    }
//                    while(!stack.isEmpty()){
//                        Node node = stack.pop();
//
//                        if(node.getChildNodes().size()==0){
//                            if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
//                                if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean"))){
////                                    System.out.println("node: "+node+" model: "+node.getMetaModel()+"Childside: "+node.getChildNodes().size());
//                                    atomWords.add(new KeyWord(node));
//                                }
//                            }
//                        }else{
//                            for(int j = 0;j<node.getChildNodes().size();j++){
//                                stack.add(node.getChildNodes().get(j));
//                            }
//                        }
//                    }
//                }
//            }

            boolean flag1 = false;
            boolean flag2 = false;
            KeyWord str = new KeyWord();
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(flag1==false&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        str = atomWords.get(a);
                    }else if(flag1==true&&atomWords.get(a).equals(arg.get(b))&&flag2==false&&!str.equals(atomWords.get(a))){
                        flag2 = true;
                    }else if(flag1 == true&&flag2 == true){
                        break;
                    }
                }

            }
            if(flag1 == true&&flag2 == true){
                System.out.println("VariableDeclarationExpr2: "+n);
                flag = true;
            }
        }

        @Override
        public void visit(AssignExpr n, List<KeyWord> arg) {
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
            Expression right = n.getValue();
            Stack<Node> stack = new Stack<>();
            boolean isComplicated = false;
            if(n.getOperator()== AssignExpr.Operator.PLUS||n.getOperator()== AssignExpr.Operator.MINUS||n.getOperator()== AssignExpr.Operator.MULTIPLY||n.getOperator()== AssignExpr.Operator.DIVIDE){
                isComplicated = true;
            }
            for(int i = 0;i<right.getChildNodes().size();i++){
                stack.add(right.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        atomWords.add(new KeyWord(node));
                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
            boolean flag1 = false;
            boolean flag2 = false;
            KeyWord str = new KeyWord();
            for(int a = 0;a<atomWords.size();a++){
                for(int b = 0;b<arg.size();b++){
                    if(isComplicated&&atomWords.get(a).equals(arg.get(b))){
                        flag1 = true;
                        flag2 = true;
                        System.out.println("the judgement key is +=");
                    }
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
        public void visit(MethodCallExpr n, List<KeyWord> arg) {
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
            MethodCallExpr e = n;
            pre_createAtomWords(e,atomWords);

            Expression c = new MethodCallExpr();
            while(true){
                if(!e.getScope().toString().equals("Optional.empty")){
                    if(e.getScope().get().isMethodCallExpr()){
                        e = e.getScope().get().asMethodCallExpr();
                    }else{
                        c = e.getScope().get();
                        break;
                    }
                }else {
                    break;
                }
            }
//            while(true){
//                for(int i = 0;i<e.getArguments().size();i++){
//                    pre_createAtomWords(e,atomWords);
//                }
//                if(!e.getScope().toString().equals("Optional.empty")){
//                    if(e.getScope().get().isMethodCallExpr()){
//                        e = e.getScope().get().asMethodCallExpr();
//                        //System.out.println("asMethodCallExpr: "+e);
//                    }else{
//                        break;
//                    }
//                }else{
//                    break;
//                }
//            }
            for(int d = 0;d<atomWords.size();d++){
                if(atomWords.get(d).equals(new KeyWord(c))){
                    atomWords.remove(d);
                }
            }
            if(!c.toString().equals("output")){
                boolean flag1 = false;
                boolean flag2 = false;
                KeyWord str = new KeyWord();
                KeyWord cNode = new KeyWord(c);
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

                }
                if(flag1 == true&&flag2 == true){
                    System.out.println("MethodCallExpr2: "+n);
                    flag = true;
                }
            }


        }

        @Override
        public void visit(FieldDeclaration n, List<KeyWord> arg) {
            super.visit(n, arg);
            List<KeyWord> atomWords = new ArrayList<>();
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
                                atomWords.add(new KeyWord(node));
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
            KeyWord str = new KeyWord();
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
        public void pre_createAtomWords(Expression e,List<KeyWord> atomWords){              //Break down into nodes
            Stack<Node> stack = new Stack<>();
            for(int i = 0;i<e.getChildNodes().size();i++){
                stack.add(e.getChildNodes().get(i));
            }
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(node.getChildNodes().size()==0){
                    if(node.getMetaModel().toString().equals("NameExpr")||node.getMetaModel().toString().equals("SimpleName")){
                        if(!(node.toString().equals("Float")||node.toString().equals("Integer")||node.toString().equals("String")||node.toString().equals("Char")||node.toString().equals("Boolean")||node.toString().equals("System.out"))){
                            atomWords.add(new KeyWord(node));
                        }

                    }
                }else{
                    for(int j = 0;j<node.getChildNodes().size();j++){
                        stack.add(node.getChildNodes().get(j));
                    }
                }
            }
        }
    }
}
