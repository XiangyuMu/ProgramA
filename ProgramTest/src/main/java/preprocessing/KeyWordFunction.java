package preprocessing;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.expr.MethodCallExpr;

public class KeyWordFunction {
	List<FunctionLine> funcList = new ArrayList<FunctionLine>();
	String name;
	
	
	public List<FunctionLine> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<FunctionLine> funcList) {
		this.funcList = funcList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	@Override
	public String toString() {
		return "KeyWordFunction [funcList=" + funcList + ", name=" + name + "]";
	}

	public String transFuncToString()  {
    	
        String tempString = "";
        tempString = tempString + name + "@ \n";
        FunctionLine fl = new FunctionLine();
        if(funcList.size()!=0) {
        	for(int i =0;i<funcList.size();i++) {
        		tempString += funcList.get(i).transFuncToString();
        	}
        }
        tempString = tempString  + "! \n";
        return tempString;
    }
	
	public void transStringtoFunc(String str) {
		
	}

}
