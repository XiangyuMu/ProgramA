package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,String>(key,value)     (value是“Double Double”形式的类型)
//value为"Double Double"形式，输出为将value分为两个Double，并计算所有相加的平均值作为输出
//可交换（应该是可交换的，但是测试用例由于java的精度问题会测出不可交换）[(1.1400000000000001 3.12,1)]
public class Example16 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();
    	 Double mx = 0d;
         Double my = 0d;
         int counter = 0;

         for (Element value : list.getList()) {
             String[] temp = value.getList().get(1).toString().split(" ");
             mx += Double.parseDouble(temp[0]);
             my += Double.parseDouble(temp[1]);
             counter ++;
         }

         mx = mx / counter;
         my = my / counter;
         String centroid = mx + " " + my;

         output.add(new TwoTuple(centroid, key));
    }
}
