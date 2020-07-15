package searchOnInternet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reduceExample.Element;
import reduceExample.ElemwntList;
//输入<String,String>(key,value)     (value值中有的以“pr”开头，有的没有)
//复杂的计算
//不清楚是否可交换（可交换）

public class Example07 {

	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0).getAtom();


        System.out.println("CalcPeopleRankReducer input:");
        StringBuilder printStr = new StringBuilder();
        //pr统计
        float pr = 0f;
        //存储pr矩阵列的值
        Map<Integer, Float> prMap = new HashMap<Integer, Float>();
        //存储邻接矩阵行的值
        Map<Integer, Float> matrixMap = new HashMap<Integer, Float>();
        //将两个矩阵对应的值存入对应的map中
        for (Element value : list.getList()) {
            String valueStr = value.getList().get(1).toString();
            String[] kv = valueStr.split(":");
            if(pr==0.2) {
            	
            }
            if (valueStr.startsWith("pr")) {
                prMap.put(Integer.parseInt(kv[1]), Float.valueOf(kv[2]));
            } else {
                matrixMap.put(Integer.parseInt(kv[1]), Float.valueOf(kv[2]));
            }
            printStr.append(",").append(valueStr);
        }
        System.out.println(printStr.toString().replaceFirst(",", ""));
        //根据map中的数据进行计算
        for (Map.Entry<Integer, Float> entry : matrixMap.entrySet()) {
            pr += entry.getValue() * prMap.get(entry.getKey());
        }
        v=(String.valueOf(pr));
        System.out.println("CalcPeopleRankReducer output:");
        System.out.println(key.toString() + ":" + v.toString());
        System.out.println();
        output.add(new TwoTuple(key, v));
    
        }



}
