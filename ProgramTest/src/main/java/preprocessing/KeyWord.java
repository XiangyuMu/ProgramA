package preprocessing;

import com.github.javaparser.ast.Node;
import com.github.javaparser.metamodel.NodeMetaModel;

import java.util.ArrayList;
import java.util.List;

public class KeyWord {
    String name;
    String methodName;

    List<Integer> row_list = new ArrayList<>();
    List<Integer> column_list = new ArrayList<>();

    String dataStructure;

    public KeyWord(){
        this.name = "";
        this.methodName = "";
    }
    public KeyWord(String name){
        this.name = name;
        this.methodName = null;
    }

    public KeyWord(Node node){
        this.name = node.toString();
        createThisNodeMethodName(node);
    }
    public KeyWord(String name,String methodName){
        this.name = name;
        this.methodName = methodName;
    }
    public String getName() {
        return name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRow_list() {
        return row_list;
    }

    public void setRow_list(List<Integer> row_list) {
        this.row_list = row_list;
    }

    public List<Integer> getColumn_list() {
        return column_list;
    }

    public void setColumn_list(List<Integer> column_list) {
        this.column_list = column_list;
    }

    public String getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(String dataStructure) {
        this.dataStructure = dataStructure;
    }



    public boolean equals(KeyWord keyword){
        if(this.name.equals(keyword.getName())&&this.methodName.equals(keyword.getMethodName())){
            return true;
        }else{
            return false;
        }
    }

    public void createThisNodeMethodName(Node node){
        Node tempNode = node;

        System.out.println(tempNode);
        if(tempNode.toString().equals("empty()")){
            this.methodName = "null";

        }else{
            while(true){
//            System.out.println("Model: "+tempNode.getMetaModel());
//            System.out.println("Node: "+tempNode);
                if(tempNode.getMetaModel().toString().equals("MethodDeclaration")){
                    for(int i = 0;i<tempNode.getChildNodes().size();i++){
                        if(tempNode.getChildNodes().get(i).getMetaModel().toString().equals("SimpleName")){
                            this.methodName = tempNode.getChildNodes().get(i).toString();
                        }
                    }
                    break;
                }else if(tempNode.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")){
                    this.methodName = "root";
                    break;
                }
                tempNode = tempNode.getParentNode().get();

            }
        }


    }

    @Override
    public String toString() {
        return "KeyWord{" +
                "name='" + name + '\'' +
                ", methodName='" + methodName + '\'' +
                ", row_list=" + row_list +
                ", column_list=" + column_list +
                ", dataStructure='" + dataStructure + '\'' +
                '}';
    }
}
