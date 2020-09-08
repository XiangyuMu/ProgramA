package byteman;

import java.util.HashMap;
import java.util.Map;

public class CopyObjectDemo {
  public static void main(String[] args) throws CloneNotSupportedException {
    //


      Map<String,String> map = new HashMap<>();
      Map<String,String> map1 = new HashMap<>();
      map.put("1","a");
      map.put("2","b");

      CopyObjectTest copyObjectTest = new CopyObjectTest(map);
      CopyObjectTest copyObjectTest1 = (CopyObjectTest) copyObjectTest.clone();
      map.put("3","c");
      System.out.println("coptObjectTest: "+ copyObjectTest);
      System.out.println("coptObjectTest1: "+ copyObjectTest1);
  }
}
