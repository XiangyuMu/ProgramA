package preprocessing;

import java.util.ArrayList;
import java.util.List;

public class FunctionLine {

    List<String > functionName = new ArrayList<>();
    Boolean isChecked = false;
   
    

	public List<String> getFunctionName() {
		return functionName;
	}

	public void setFunctionName(List<String> functionName) {
		this.functionName = functionName;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	

	@Override
	public String toString() {
		return "FunctionLine [functionName=" + functionName + ", isChecked=" + isChecked + "]";
	}

	public String transFuncToString()  {
    	
        String tempString = "";
        if(functionName.size()!=0) {
        	for(int i =0;i<functionName.size();i++) {
        		tempString = tempString + functionName.get(i) + "# ";
        		
        	}
        	tempString = tempString + isChecked + "# ";
        }
        tempString = tempString  + "\n";
        return tempString;
    }
	
}
