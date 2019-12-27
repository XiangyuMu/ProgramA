package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;


public class Example23 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	int sumOfTemperatures = 0;
        int nbValues = 0;
        for (Element value : list.getList()) {
          sumOfTemperatures += (Integer)value.getList().get(1);
          nbValues++;
        }
        int average = sumOfTemperatures / nbValues;
        output.add(new TwoTuple(key, average+""));
    }




}
