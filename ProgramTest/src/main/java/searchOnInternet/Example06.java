package searchOnInternet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//输入<String,int>(key,value)   value<=25
//进行复杂计算
//不知是否可交换（可交换）
public class Example06 {

	
	public List<TwoTuple> getOutput() {
		return output;
	}
	public void setOutput(List<TwoTuple> output) {
		this.output = output;
	}
	
	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);

        //初始化概率矩阵,概率矩阵只有一列,函数和总用户数相同
        //用户数
        int nums = 25;
        float[] G = new float[nums];
        //概率矩阵的值为pr公式的(1-d)/n的部分
        //阻尼系数
        float d = 0.85f;
        Arrays.fill(G, (1 - d) / nums);
        //构建用户邻接矩阵
        float[] U = new float[nums];
        //该用户的链出数
        int out = 0;
        StringBuilder printSb = new StringBuilder();
        for (Element value : list.getList()) {
            //从value中拿到目标用户的id
            int targetUserIndex = Integer.parseInt(value.getList().get(1).toString());
            //邻接矩阵中每个目标用户对应的值为1,其余为0
            U[targetUserIndex - 1] = 1;
            out++;
            printSb.append(",").append(value.getList().get(1).toString());
        }
        //打印reducer的输入
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
