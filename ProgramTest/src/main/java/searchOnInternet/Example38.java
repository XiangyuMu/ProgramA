package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//https://github.com/himank/Graph-Algorithm-MapReduce/blob/master/src/DijikstraAlgo.java


public class Example38 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	int maxVisit = 0;
	int minVisit = Integer.MAX_VALUE;
	String maxMinute = null;
	String minMinute = null;
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	String nodes = "UNMODED";
		String word = "";
		int lowest = 125; // In this 125 is considered as infinite distance
		Iterator<Element> values = list.getList().iterator();
		while (values.hasNext()) { // looks like NODES/VALUES 1 0 2:3:, we
									// need to use the first as a key
			String[] sp = values.next().getList().get(1).toString().split(" "); // splits on
																// space
			// look at first value
			if (sp[0].equalsIgnoreCase("NODES")) {
				nodes = null;
				nodes = sp[1];
			} else if (sp[0].equalsIgnoreCase("VALUE")) {
				int distance = Integer.parseInt(sp[1]);
				lowest = Math.min(distance, lowest);
			}
		}
		word=lowest + " " + nodes;
		output.add(new TwoTuple(key, word));
		word="";
		
    }





}
