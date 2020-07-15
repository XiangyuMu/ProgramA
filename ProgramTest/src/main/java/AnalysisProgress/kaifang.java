package AnalysisProgress;

import java.math.BigDecimal;

public class kaifang {


    public static void main(String[] args) {
        kaifang k = new kaifang();
        System.out.println(k.kaifangFunction(15));
    }

    public float kaifangFunction(int num){
        float min = 0;
        float max = num;
        int i;
        float formerNum = 0;
        while(true){
            float interNum = (min+max)/2;
            if (interNum*interNum>num){
                if (quzheng(interNum) == quzheng(formerNum)){
//
                    return quzheng(interNum);
                }
                formerNum = interNum;
                max = interNum;
            }else if (interNum*interNum<num){
                if (quzheng(interNum) == quzheng(formerNum)){
//
                    return quzheng(interNum);
                }
                formerNum = interNum;
                min = interNum;
            }else{
//
                return quzheng(interNum);
            }
        }



    }

    /**
     * 取整，保留两位
     * @param num1
     * @return
     */
    public float quzheng(float num1){
        BigDecimal b = new BigDecimal(num1);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }


}
