package preprocessing;

import java.util.ArrayList;
import java.util.List;

public class MethodWord {
    String name;
    List<Object> parameterList = new ArrayList<>();

    public MethodWord(Object object, List<Object> parameterObjectList){
        System.out.println("name: "+object);
        System.out.println("MateType: "+object.getClass());
        NameExpression nameExpression = (NameExpression) object;
        this.name = nameExpression.name;
        this.parameterList = parameterObjectList;
    }

    public MethodWord() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Object> parameterList) {
        this.parameterList = parameterList;
    }

    @Override
    public String toString() {
        return "MethodWord{" +
                "name='" + name + '\'' +
                ", parameterList=" + parameterList +
                '}';
    }
}
