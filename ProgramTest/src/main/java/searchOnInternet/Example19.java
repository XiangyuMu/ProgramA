package searchOnInternet;

import java.util.ArrayList;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,Double>(key,value) (value的第一位为1或2)
//输出将第一个为1的进行累加，作为key，value为第一个字母为2的最后一个。
//不可交换(确定)
public class Example19 {
	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
    public void reduce(ElemwntList list)  {

    	//String key = (String)list.getList().get(0).getList().get(0);

    	//implement here
//		int nameNum=0;
//		int countNum=0;
		int sum = 0;

        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> b = new ArrayList<String>();
		 String name=null;
		 for (Element value : list.getList()) {
		  String record=value.getList().get(1).toString();
		    char type=record.charAt(0);
		    if(type=='1')
		    {   
		       
		       sum += Integer.parseInt(value.getList().get(1).toString().trim());;
		    }
           if(type=='2')
		    {
		       
		        name=record.substring(2);
		    }
        }
        if(name!=null&&String.valueOf(sum)!=null)
        {
		  a.add(String.valueOf(sum));
		  b.add(","+name);
		  output.add(new TwoTuple(a.get(0).toString(),b.get(0).toString()));
        }
	
    }
}
