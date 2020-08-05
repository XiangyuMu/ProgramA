package byteman;

import reduceExample.*;
import searchOnInternet.*;
import testList.TestInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BytemanTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        System.out.println("please input the name of file: ");
        String filename = scan.next();
        TestInput ti = new TestInput();
        List<ElemwntList> list = new ArrayList<>();
        ElemwntList elist ;
        elist = ti.createTestCase_Single("TestCase/case1.txt", "String", "String");
        IndexValuePair_1 e = new IndexValuePair_1();
        System.out.println("elist: "+elist);
        e.reduce(elist);
        List<TwoTuple> tt1  = e.getOutput();
        System.out.println(tt1);
    }

}
