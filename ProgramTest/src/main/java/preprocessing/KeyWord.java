package preprocessing;

import com.github.javaparser.ast.Node;
import com.github.javaparser.metamodel.NodeMetaModel;
import reduceExample.Atom;
import reduceExample.Element;

import java.util.ArrayList;
import java.util.List;

public class KeyWord {
    String name;
    String methodName;

    List<Element> atomList = new ArrayList<>();
    List<Element> changedAtomList = new ArrayList<>();

    String dataStructure;

    public KeyWord(){
        this.name = "";
        this.methodName = "";
    }

    public List<Element> getChangedAtomList() {
        return changedAtomList;
    }

    public void setChangedAtomList(List<Element> changedAtomList) {
        this.changedAtomList = changedAtomList;
    }

    public KeyWord(String name){
        this.name = name;
        this.methodName = null;
    }

    public KeyWord(Node node){
        this.name = node.toString();
        createThisNodeMethodName(node);
    }

    @Override
    public String toString() {
        return "KeyWord{" +
                "name='" + name + '\'' +
                ", methodName='" + methodName + '\'' +
                ", atomList=" + atomList +
                ", changedAtomList=" + changedAtomList +
                ", dataStructure='" + dataStructure + '\'' +
                '}';
    }

    public KeyWord(String name, String methodName){
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

    public List<Element> getAtomList() {
        return atomList;
    }

    public void setAtomList(List<Element> atomList) {
        this.atomList = atomList;
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

}
