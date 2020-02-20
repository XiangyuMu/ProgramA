package searchOnInternet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,int>(key,value)
//输出值为累加
//可交换
public class Example26 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	HashSet<Integer> seasons = new HashSet<Integer>();
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();

    	seasons.clear();
		
		int total = 0;
		
		for (Element value : list.getList()) {
			total++;
			seasons.add((Integer)value.getList().get(1).getAtom());
		}
		
		output.add(new TwoTuple(key, new PassWritable(total, seasons.size()).toString()));
    }



    public class PassWritable  {
    	public int passes;
    	public int season;
    	
    	public PassWritable() {
    		
    	}
    	
    	public PassWritable(int passes, int season) {
    		this.passes = passes;
    		this.season = season;
    	}
    	
    	
    	
    	public void write(DataOutput out) throws IOException {
    		out.writeInt(passes);
    		out.writeInt(season);
    	}

    	
    	public void readFields(DataInput in) throws IOException {
    		passes = in.readInt();
    		season = in.readInt();
    	}

    	
    	public String toString() {
    		return passes + "\t" + season;
    	}
    }

}
