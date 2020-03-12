package preprocessing;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * It has two type dataStructures: last and keylist
 * last:store the invoked functions to check out which which functions have been invoked in an expression
 * keylast keep the functions which are called for a keyword
 */
public class MixStruct {

	List<MethodCallExpr> last = new ArrayList<MethodCallExpr>();
	
	List<KeyWordFunction> keyList = new ArrayList<KeyWordFunction>();

	public List<MethodCallExpr> getLast() {
		return last;
	}

	public void setLast(List<MethodCallExpr> last) {
		this.last = last;
	}

	public List<KeyWordFunction> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<KeyWordFunction> keyList) {
		this.keyList = keyList;
	}

	@Override
	public String toString() {
		return "MixStruct [last=" + last + ", keyList=" + keyList + "]";
	}
	
	public String transKeyListToString()  {
    	
        String tempString = "";        
        if(keyList.size()!=0) {
        	for(int i =0;i<keyList.size();i++) {
        		tempString += keyList.get(i).transFuncToString();
        	}
        }
        return tempString;
    }

	public void transStringtoFunc(String str) {
		if(str.contains("@ ")) {
			KeyWordFunction kwf = new KeyWordFunction();
			kwf.setName(str.split("@ ")[0]);
			this.keyList.add(kwf);
		}else if(str.contains("# ")) {
			String str1[] = str.split("# ");
			FunctionLine fl = new FunctionLine();
			fl.setIsChecked(Boolean.valueOf(str1[str1.length]));
			for(int k = 0;k<str1.length-1;k++) {
				fl.getFunctionName().add(str1[k]);
			}
			this.keyList.get(keyList.size()-1).getFuncList().add(fl);
		}
	}
	
	public void setKeyWordList(String name,List<String> list) {
		boolean isExist = false;
		int num = 0;
		for(int i = 0;i<this.keyList.size();i++) {
			if(this.keyList.get(i).getName().equals(name)) {
				isExist = true;
				num = i;
				break;
			}
		}
		if(isExist) {
			FunctionLine fl = new FunctionLine();
			fl.setFunctionName(list);
			fl.setIsChecked(false);
			this.keyList.get(num).getFuncList().add(fl);
		}else {
			KeyWordFunction kwf = new KeyWordFunction();
			kwf.setName(name);
			FunctionLine fl = new FunctionLine();
			fl.setFunctionName(list);
			fl.setIsChecked(false);
			kwf.getFuncList().add(fl);
			this.keyList.add(kwf);
		}
	}
}
