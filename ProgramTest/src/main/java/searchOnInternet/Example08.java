package searchOnInternet;


import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;
//?????<String,String>(key,value)     (value??String+?? ??+float???)
//???????????????(key??value)??value???????§Ö????
//???????

public class Example08 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);



        System.out.println("FinallyResult input:");
        StringBuilder printStr = new StringBuilder();
        float totalPr = 0f;
        List<String> list_1 = new ArrayList<String>();
        for (Element value : list.getList()) {
            String valueStr = value.getList().get(1).toString();
            list_1.add(valueStr);
            String[] strArr = valueStr.split("");
            totalPr += Float.parseFloat(strArr[1]);
            printStr.append(",").append(valueStr);
        }
        System.out.println(printStr.toString().replace(",", ""));

        for (String s : list_1) {
            String[] strArr = s.split("");
            v=(String.valueOf(Float.parseFloat(strArr[1]) / totalPr));
            output.add(new TwoTuple(key,v));
            System.out.println("FinallyResult output:");
           // System.out.println(k.toString() + ":" + v.toString());
            System.out.println();
        }
    
        }



}
