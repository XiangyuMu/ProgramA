package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.List;


public class Example21 {
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v ="";
	
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	 int massMessages = 0;

         // Remember, PageRank mass is stored as a log prob.
         float mass = Float.NEGATIVE_INFINITY;
         for (Element value : list.getList()) {
           // Accumulate PageRank mass contributions
           mass = sumLogProbs(mass, (Float) value.getList().get(1));

           massMessages++;
         }

         // emit aggregated results
         if (massMessages > 0) {
           output.add(new TwoTuple(key, mass+""));
         
    	}
    }
    private static float sumLogProbs(float a, float b) {
        if (a == Float.NEGATIVE_INFINITY)
          return b;

        if (b == Float.NEGATIVE_INFINITY)
          return a;

        if (a < b) {
          return (float) (b + StrictMath.log1p(StrictMath.exp(a - b)));
        }

        return (float) (a + StrictMath.log1p(StrictMath.exp(b - a)));
      }
}
