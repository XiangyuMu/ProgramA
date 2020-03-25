package byteman;


//import org.jboss.byteman.rule.Rule;
//import org.jboss.byteman.rule.helper.Helper;
import preprocessing.DealWithInfoToFile;
import preprocessing.KeyWord;
import preprocessing.SameLineInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *the intrinsic linkmap in  byteman
 * map("LineAndVariableMap") key:the number of each line   value: the variables' information in this line.
 * map("table") key:the name of variable   value: the value of variable in this time.
 * printLinkMaps(Object) print the detail informantion of Map
 * changeVaribles(Object,Object)
 * addTOReadList(Object,int)
 */


//public class LinkMapsHelper extends Helper {
////    protected LinkMapsHelper(Rule rule) {
////        super(rule);
////    }
////
////    public void printLinkMaps(Object name){
////        Object maps =  linked(name);
////        traceln("the map: "+maps.toString());
////
////    }
////
////    /**
////     *detect the variable if have been changed
////     * @param name
////     * @param currentValue
////     * @throws IOException
////     */
////    public void changeVaribles(Object name,Object currentValue) throws IOException {
////        traceln("currentValue: "+name+" "+currentValue.toString());
////        traceln("type: "+currentValue.getClass().getSimpleName());
////        String type = currentValue.getClass().getSimpleName();
////        DealWithInfoToFile dwitf = new DealWithInfoToFile();
////        List<KeyWord> list = new ArrayList<>();
////        list = dwitf.readKeyWordfile();                        //read the keyWord information from "KeyWordFile.txt" and store in the list
////        if(createLinkMap("table")){
////            link("table",name,currentValue);
////            traceln("table has been founded");
////        }else{
////            List<Object> namelist = linkNames("table");
////            List<Object> valuelist = linkValues("table");
////            boolean flag = false;
////            for(int i = 0;i<namelist.size();i++){
////                if(namelist.get(i).equals(name)){
////                    flag = true;
////                    break;
////                }
////            }
////            if(!flag){
////                link("table",name,currentValue);
////            }else{
////                Object maps = linked("table",name);
////                traceln("the former: "+maps.toString());
////                traceln("the current: "+currentValue);
////                if(!maps.equals(currentValue)){
////                link("table",name,currentValue);
////                traceln("had been changed");
////                }
////            }                                                                             //detect the change of variable value
////            traceln("List: "+namelist);
////            traceln("Value: "+valuelist);
////
//////            Object maps = linked("table",name);
//////            traceln("the former: "+maps.toString());
//////            traceln("the current: "+currentValue);
//////            if(!maps.equals(currentValue)){
//////                link("table",name,currentValue);
//////                traceln("had been changed");
//////            }
////        }
////
////
////    }
////
////    /**
////     *extract the readKeyWord from each line
////     * @param keywordName
////     * @param num
////     * @throws IOException
////     */
////    public void addTOReadList(Object keywordName,int num) throws IOException {
////        DealWithInfoToFile dwitf = new DealWithInfoToFile();
////        List<KeyWord> list = new ArrayList<>();
////        list = dwitf.readKeyWordfile();
////        KeyWord keyword = new KeyWord();
////        for(int k = 0;k<list.size();k++){
////            if(list.get(k).getName().equals(keywordName)){
////                keyword = list.get(k);
////            }else{
////                traceln("read not find the keyword!");
////            }
////        }
////        SameLineInfo sli = new SameLineInfo();
////        if(createLinkMap("LineAndVariableMap")){
////            sli.getReadlist().add((KeyWord) keyword);
////            link("LineAndVariableMap",num,sli);
////        }else{
////            List<Object> lineList =  linkNames("LineAndVariableMap");
////            boolean flag = false;
////            for(int i = 0;i<lineList.size();i++){
////                traceln("yunxing !");
////                if(lineList.get(i).toString().equals(Integer.toString(num))){
////                    sli = (SameLineInfo)linked("LineAndVariableMap",num);
////                    sli.getReadlist().add((KeyWord) keyword);
////                    link("LineAndVariableMap",num,sli);
////                    flag = true;
////                    break;
////                }
////            }
////            if(!flag){
////                sli.getReadlist().add((KeyWord) keyword);
////                link("LineAndVariableMap",num,sli);
////            }
////        }
////
////    }
////
////    /**
////     * extract the writeKeyWord from each line
////     * @param keywordName
////     * @param num
////     * @throws IOException
////     */
////    public void addToWriteList(Object keywordName,int num) throws IOException {
////        DealWithInfoToFile dwitf = new DealWithInfoToFile();
////        List<KeyWord> list = new ArrayList<>();
////        list = dwitf.readKeyWordfile();
////        KeyWord keyword = new KeyWord();
////        for(int k = 0;k<list.size();k++){
////            if(list.get(k).getName().equals(keywordName)){
////                keyword = list.get(k);
////            }else{
////                traceln(" write not find the keyword!");
////            }
////        }
////        SameLineInfo sli = new SameLineInfo();
////        if(createLinkMap("LineAndVariableMap")){
////            sli.getWritelist().add((KeyWord) keyword);
////            link("LineAndVariableMap",num,sli);
////        }else{
////            List<Object> lineList =  linkNames("LineAndVariableMap");
////            boolean flag = false;
////            for(int i = 0;i<lineList.size();i++){
////
////                if(lineList.get(i).toString().equals(Integer.toString(num))){
////
////                    sli = (SameLineInfo)linked("LineAndVariableMap",num);
////                    sli.getWritelist().add((KeyWord) keyword);
////                    link("LineAndVariableMap",num,sli);
////                    flag = true;
////                    break;
////                }
////            }
////            if(!flag){
////                sli.getWritelist().add((KeyWord) keyword);
////                link("LineAndVariableMap",num,sli);
////            }
////        }
////
////    }
//
//
//}
