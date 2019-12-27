package searchOnInternet;


import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//????<String,int>(key,value)   value<=25
//???§Ú??????
//??????????
public class Example06 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        //????????????,?????????????,????????????????
        //?????
        int nums = 25;
        float[] G = new float[nums];
        //??????????pr?????(1-d)/n?????
        //???????
        float d = 0.85f;
        Arrays.fill(G, (1 - d) / nums);
        //?????????????
        float[] U = new float[nums];
        //?????????????
        int out = 0;
        StringBuilder printSb = new StringBuilder();
        for (Element value : list.getList()) {
            //??value?????????????id
            int targetUserIndex = Integer.parseInt(value.getList().get(1).toString());
            //????????????????????????1,?????0
            U[targetUserIndex - 1] = 1;
            out++;
            printSb.append(",").append(value.getList().get(1).toString());
        }
        //???reducer??????
        System.out.println("AdjacencyReducer input:");
        System.out.println(key.toString() + ":" + printSb.toString().replaceFirst(",", ""));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nums; i++) {
            stringBuilder.append(",").append(G[i] + U[i] * d / out);
        }
        v=(stringBuilder.toString().replaceFirst(",", ""));
        System.out.println("AdjacencyReducer output:");
        System.out.println(key.toString() + ":" + v.toString());
        System.out.println();
        output.add(new TwoTuple(key,v));
        }



}
