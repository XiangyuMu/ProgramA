package AnalysisProgress;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import preprocessing.KeyWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserDemo {
    private List<KeyWord> relatedWordList = new ArrayList<>();
    KeyWord list = new KeyWord("list","reduce");
    public ParserDemo(){
        relatedWordList.add(list);
    }
    public List<KeyWord> getRelatedWordList() {
        return relatedWordList;
    }
    public void setRelatedWordList(List<KeyWord> relatedWordList) {
        this.relatedWordList = relatedWordList;

    }
    public static class CreateRelatedWordListVisitor extends VoidVisitorAdapter<List<KeyWord>>{



            public void pre_createAtomWords (Expression e, List < KeyWord > atomWords)
            {              //Break down into nodes
                Stack<Node> stack = new Stack<>();
                for (int i = 0; i < e.getChildNodes().size(); i++) {
                    stack.add(e.getChildNodes().get(i));
                }
                while (!stack.isEmpty()) {
                    Node node = stack.pop();
                    if (node.getChildNodes().size() == 0) {
                        if (node.getMetaModel().toString().equals("NameExpr") || node.getMetaModel().toString().equals("SimpleName")) {
                            if (!(node.toString().equals("Float") || node.toString().equals("Integer") || node.toString().equals("String") || node.toString().equals("Char") || node.toString().equals("Boolean") || node.toString().equals("System.out"))) {
                                atomWords.add(new KeyWord(node));
                            }

                        }
                    } else {
                        for (int j = 0; j < node.getChildNodes().size(); j++) {
                            stack.add(node.getChildNodes().get(j));
                        }
                    }
                }
            }

            @Override
            public void visit (VariableDeclarationExpr n, List < KeyWord > arg)
            {
                System.out.println("VariableDeclarationExpr: "+n);
                for(int i = 0;i<n.getVariables().size();i++){
                    Node left = n.getVariable(i).getChildNodes().get(1);
                    KeyWord keyWord = new KeyWord(left);
                    keyWord.setDataStructure(n.getVariable(i).getTypeAsString());
                    arg.add(keyWord);
                }

            }

    }

}
