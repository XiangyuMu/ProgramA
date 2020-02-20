package preprocessing;

import java.io.*;
import java.util.List;

public class CreateBTMFile {
    public void createBTMFile(List<KeyWord> keyWordsList,String filename) throws IOException {
        FileWriter file = new FileWriter("ProgramTest/src/main/java/byteman/"+filename+".btm");
        BufferedWriter output = new BufferedWriter(file);
        String str1,str2,str3,str4;
        for(int i = 0;i<keyWordsList.size();i++){
            str1 = "RULE "+keyWordsList.get(i).getName()+"1\n" +
                    "CLASS searchOnInternet."+filename+"\n" +
                    "METHOD reduce(ElemwntList)\n" +
                   // "HELPER byteman.HalperTest\n"+
                    "AT WRITE $"+keyWordsList.get(i).getName()+"\n" +
                    "IF true\n" +
                    "DO traceln(\""+keyWordsList.get(i).getName()+"list before write: \"+$"+keyWordsList.get(i).getName()+")\n" +
                    "ENDRULE\n";

            str2 = "RULE "+keyWordsList.get(i).getName()+"2\n" +
                    "CLASS searchOnInternet."+filename+"\n" +
                    "METHOD reduce(ElemwntList)\n" +
                   // "HELPER byteman.HalperTest\n"+
                    "AFTER WRITE $"+keyWordsList.get(i).getName()+"\n" +
                    "IF true\n" +
                    "DO traceln(\""+keyWordsList.get(i).getName()+"list after write: \"+$"+keyWordsList.get(i).getName()+")\n" +
                    "ENDRULE\n";

            str3 = "RULE "+keyWordsList.get(i).getName()+"3\n" +
                    "CLASS searchOnInternet."+filename+"\n" +
                    "METHOD reduce(ElemwntList)\n" +
                  //  "HELPER byteman.HalperTest\n"+
                    "AFTER READ $"+keyWordsList.get(i).getName()+"\n" +
                    "IF true\n" +
                    "DO traceln(\""+keyWordsList.get(i).getName()+"list before read: \"+$"+keyWordsList.get(i).getName()+")\n" +
                    "ENDRULE\n";

            str4 = "RULE "+keyWordsList.get(i).getName()+"4\n" +
                    "CLASS searchOnInternet."+filename+"\n" +
                    "METHOD reduce(ElemwntList)\n" +
                //    "HELPER byteman.HalperTest\n"+
                    "AFTER READ $"+keyWordsList.get(i).getName()+"\n" +
                    "IF true\n" +
                    "DO traceln(\""+keyWordsList.get(i).getName()+"list after read: \"+$"+keyWordsList.get(i).getName()+")\n" +
                    "ENDRULE\n";
            output.write(str1);
//            output.write(str2);
//            output.write(str3);
//            output.write(str4);
            output.flush();
        }
        output.close();
    }
}
