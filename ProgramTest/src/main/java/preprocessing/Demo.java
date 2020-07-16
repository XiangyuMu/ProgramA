package preprocessing;

import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException, DocumentException {
        CreateBTMFileAgain c = new CreateBTMFileAgain();
        c.readKeyWordFromFile();
        c.textractFromXML();
        System.out.println("Demo.Map: "+c.VariableDeclararion);
        c.parserVariableDeclararion(24);
        c.printIntoBtmFile();
    }
}
