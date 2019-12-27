package searchOnInternet;

import reduceExample.Element;
import reduceExample.ElemwntList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//����<String,String>(key,value)     (valueֵ���е��ԡ�pr����ͷ���е�û��)
//���ӵļ���
//������Ƿ�ɽ���

public class Example07 {

	List<TwoTuple> output = new ArrayList<TwoTuple>() ;
	String v = "";
	public void reduce(ElemwntList list) {
		String key = (String)list.getList().get(0).getList().get(0);


        System.out.println("CalcPeopleRankReducer input:");
        StringBuilder printStr = new StringBuilder();
        //prͳ��
        float pr = 0f;
        //�洢pr�����е�ֵ
        Map<Integer, Float> prMap = new HashMap<Integer, Float>();
        //�洢�ڽӾ����е�ֵ
        Map<Integer, Float> matrixMap = new HashMap<Integer, Float>();
        //�����������Ӧ��ֵ�����Ӧ��map��
        for (Element value : list.getList()) {
            String valueStr = value.getList().get(1).toString();
            String[] kv = valueStr.split(":");
            if (valueStr.startsWith("pr")) {
                prMap.put(Integer.parseInt(kv[0]), Float.valueOf(kv[1]));
            } else {
                matrixMap.put(Integer.parseInt(kv[0]), Float.valueOf(kv[1]));
            }
            printStr.append(",").append(valueStr);
        }
        System.out.println(printStr.toString().replaceFirst(",", ""));
        //����map�е����ݽ��м���
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
