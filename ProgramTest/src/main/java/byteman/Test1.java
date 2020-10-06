package byteman;

import java.util.Scanner;

public class Test1 {
    static void maxRepeating(String str)
    {
        int k = 0;
        String s = "";
        int n=100;
        for (int i = 1;i<n/2;i++){
            if (s.substring(0,i).equals(s.substring(i,2*i))){
                if (i>k){
                    k=i;
                }
            }
        }
    }

    // Driver code
    public static void main(String args[])
    {
        String str = "ssstrtyweweqqqqqookkpppdddddddsaw";
        maxRepeating(str);
    }

}
