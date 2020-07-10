package preprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealWithInfoToFile {

    /**
     * printKeyWordTothefile,The name of KeyWord,dataStructure,reducefunction(field)
     * @param list
     * @throws IOException
     */
    public void printKeyWordfile(List<KeyWord> list) throws IOException {
        FileWriter file = new FileWriter("KeyWordFile.txt");
        BufferedWriter output = new BufferedWriter(file);

        String str = "";
        output.write("");
        for(int i = 0;i<list.size();i++){
            str = "";
            str = str + list.get(i).getName();
            str = str+"# ";
            str = str + list.get(i).getDataStructure();
            str = str+"# ";
            str = str + list.get(i).getMethodName();
            str = str +"# ";
            output.write(str);
            output.write("\n");
        }
        output.flush();
        output.close();

    }

    /**
     * read information of KeyWord from file
     * @return
     * @throws IOException
     */
    public List<KeyWord> readKeyWordfile() throws IOException {
        FileReader file = new FileReader("KeyWordFile.txt");
        BufferedReader reader = new BufferedReader(file);
        String tempString = null;
        List<KeyWord> listK = new ArrayList<>();
        while ((tempString = reader.readLine()) != null) {
            String str[] = tempString.split("# ");
            KeyWord keyword = new KeyWord();
            keyword.setName(str[0]);
            keyword.setDataStructure(str[1]);
            keyword.setMethodName(str[2]);
            listK.add(keyword);
        }
//        System.out.println("listK: "+listK);
        return listK;
    }


}
