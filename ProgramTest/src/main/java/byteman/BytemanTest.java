package byteman;

import reduceExample.ElemwntList;
import searchOnInternet.*;
import testList.TestInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BytemanTest {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("please input the name of file: ");
        String filename = scan.next();
        TestInput ti = new TestInput();
        List<ElemwntList> list = new ArrayList<>();
        ElemwntList elist ;
        elist = ti.createTestCase_Single("TestCase/case2.txt", "String", "String");
        Example02 e = new Example02();
        e.reduce(elist);
        List<TwoTuple> tt1  = e.getOutput();
        System.out.println(tt1);
    }

}
