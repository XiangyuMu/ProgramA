package AnalysisProgress;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

public class kaifang {
    public static void main(String[] args) {
        kaifang k = new kaifang();
        k.func();
    }


    public void func(){
        Scanner scan = new Scanner(System.in);
        System.out.println("qingshuru");
        String s = scan.next();
        char[] chars= s.toCharArray();
        Stack stack = new Stack();
        boolean flag = true;
        for (char c:chars){
            if (c == '('){
                stack.push(c);
            }
            else if (c == ')'&&!stack.empty()){
                stack.pop();
            }else if (c == ')'&&stack.empty()){
                flag = false;
                break;
            }
        }
        if (!stack.empty()){
            flag = false;
        }
        if (flag){
            System.out.println("valid");
        }else{
            System.out.println("invalid");
        }
    }
}




