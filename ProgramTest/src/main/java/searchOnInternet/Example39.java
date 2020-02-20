package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//https://github.com/matt-hicks/MapReduce-KNN/blob/master/KnnPattern.java

//输入<String,DoubleString>(key,value)
//输出值为复杂计算
//不清楚
public class Example39 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	TreeMap<Double, String> KnnMap = new TreeMap<Double, String>();
	int K = 5;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0).getAtom();


    	for (Element val1 : list.getList())
		{
    		DoubleString val = (DoubleString) val1.getList().get(1).getAtom();
			String rModel = val.getModel();
			double tDist = val.getDistance();
			
			// Populate another TreeMap with the distance and model information extracted from the
			// DoubleString objects and trim it to size K as before.
			KnnMap.put(tDist, rModel);
			if (KnnMap.size() > K)
			{
				KnnMap.remove(KnnMap.lastKey());
			}
		}	

			// This section determines which of the K values (models) in the TreeMap occurs most frequently
			// by means of constructing an intermediate ArrayList and HashMap.

			// A List of all the values in the TreeMap.
			List<String> knnList = new ArrayList<String>(KnnMap.values());

			Map<String, Integer> freqMap = new HashMap<String, Integer>();
		    
		    // Add the members of the list to the HashMap as keys and the number of times each occurs
		    // (frequency) as values
		    for(int i=0; i< knnList.size(); i++)
		    {  
		        Integer frequency = freqMap.get(knnList.get(i));
		        if(frequency == null)
		        {
		            freqMap.put(knnList.get(i), 1);
		        } else
		        {
		            freqMap.put(knnList.get(i), frequency+1);
		        }
		    }
		    
		    // Examine the HashMap to determine which key (model) has the highest value (frequency)
		    String mostCommonModel = null;
		    int maxFrequency = -1;
		    for(Map.Entry<String, Integer> entry: freqMap.entrySet())
		    {
		        if(entry.getValue() > maxFrequency)
		        {
		            mostCommonModel = entry.getKey();
		            maxFrequency = entry.getValue();
		        }
		    }
		    
		// Finally write to context another NullWritable as key and the most common model just counted as value.
		output.add(new TwoTuple(key, mostCommonModel)); // Use this line to produce a single classification
//		context.write(NullWritable.get(), new Text(KnnMap.toString()));	// Use this line to see all K nearest neighbours and distances
	}
	
    




public class DoubleString
{
	private Double distance = 0.0;
	private String model = null;

	public void set(Double lhs, String rhs)
	{
		distance = lhs;
		model = rhs;
	}
	
	public Double getDistance()
	{
		return distance;
	}
	
	public String getModel()
	{
		return model;
	}
	
	
	public void readFields(DataInput in) throws IOException
	{
		distance = in.readDouble();
		model = in.readUTF();
	}
	
	
	public void write(DataOutput out) throws IOException
	{
		out.writeDouble(distance);
		out.writeUTF(model);
	}
	
	
	public int compareTo(DoubleString o)
	{
		return (this.model).compareTo(o.model);
	}
}


}
