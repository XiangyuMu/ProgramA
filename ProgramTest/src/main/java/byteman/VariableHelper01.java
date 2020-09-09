//package byteman;
//
//import org.jboss.byteman.rule.Rule;
//import org.jboss.byteman.rule.helper.Helper;
//import preprocessing.DealWithInfoToFile;
//import preprocessing.KeyWord;
//import reduceExample.Atom;
//import reduceExample.Element;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class VariableHelper01 extends Helper {
//
//    protected VariableHelper01(Rule rule) {
//        super(rule);
//    }
//    public List<Object> objectList = new ArrayList<>();
//    public void initFunc() throws IOException {
//        createLinkMap("KeyWordMap");
//        createLinkMap("ValueTable");
//        createLinkMap("ChangeMap");
//        createLinkMap("test");
//        List<KeyWord> lk = new ArrayList<>();
//        lk = new DealWithInfoToFile().readKeyWordfile();
//        traceln("lk: "+lk);
//        for(int i = 0;i<lk.size();i++){
//            traceln("lk.get: "+lk.get(i).getName());
//            link("KeyWordMap",lk.get(i).getName(),lk.get(i));
//            traceln("keyWordMap.get: "+linked("KeyWordMap",lk.get(i).getName()));
//            link("ValueTable",lk.get(i).getName(),null);
//            link("ChangeMap",lk.get(i).getName(),"");
//        }
//        List<Object> objects = new ArrayList<>();
//        link("test","1",objects);
//    }
//
//    public void addAtomToKeyWord(String mapNmae, String keyWordName, Atom atom){
//        KeyWord keyWord = (KeyWord) linked(mapNmae,keyWordName);
//        int formerLine;
//        if (keyWord.getAtomList().size()==0){
//            reduceExample.Element ele = new reduceExample.Element();
//            ele.getAtomlist().add(atom);
//            ele.getList().add(atom.getAtom());
//            keyWord.getAtomList().add(ele);
//        }else{
//            formerLine = keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getAtomlist().get(0).getRow();
//            if(formerLine==atom.getRow()){
//                keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getAtomlist().add(atom);
//                keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().add(atom.getAtom());
//            }else{
//                reduceExample.Element element = new reduceExample.Element();
//                element.getAtomlist().add(atom);
//                element.getList().add(atom.getAtom());
//                keyWord.getAtomList().add(element);
//            }
//
//        }
//        link(mapNmae,keyWordName,keyWord);
//        traceln("updatedMap: "+linked(mapNmae,keyWordName));
//        traceln(linkValues(mapNmae).get(0).getClass().getName());
//    }
//
//
//    public void dealwithKeyWord(String dadNode,String row,String column,String rowType,String columnType,String operation,Object value) throws Exception {
//        traceln("Start: "+linkValues("ChangeMap"));
//        traceln("beforeChangeMap: "+value);
//        CopyObjectTest copyObjectTest = new CopyObjectTest(value);
//        CopyObjectTest copyObjectTest1 = (CopyObjectTest) copyObjectTest.clone();
//        KeyWord keyWord = (KeyWord) linked("KeyWordMap",dadNode);
//        String dataStructure = value.getClass().getSimpleName();
//        int rowNum = -1;
//        int columnNum = -1;
//        if (rowType.equals("Int")&&columnType.equals("Int")){
//            rowNum = Integer.parseInt(row);
//            columnNum = Integer.parseInt(column);
//        }else  if (rowType.equals("Int")&&columnType.equals("")){
//            rowNum = Integer.parseInt(row);
//        }
//        List<Element> atomList = keyWord.getAtomList();
//        if (operation=="add"){
//            if (rowNum!=-1&&columnNum!=-1){
//                if (atomList.size()>rowNum){
//                    if (atomList.get(rowNum).getList().size()>columnNum){
//                        Atom atom = atomList.get(rowNum).getAtomlist().get(columnNum);
//                        Element element = new Element();
//                        element.getAtomlist().add(atom);
//                        element.getList().add(atom.getAtom());
//                        keyWord.getAtomList().add(element);
//                        keyWord.getChangedAtomList().add(element);
//                        if (isCovered(copyObjectTest1.getO(),dadNode)){
//                            keyWord.getCoverAtomList().add(element);
//                        }
//                        link("KeyWordMap",dadNode,keyWord);
//                    }else {
//                        traceln("the column < size! ");
//                    }
//                }else{
//                    traceln("the row < size! ");
//                }
//            }else if (rowNum!=-1&&columnNum==-1){
//                if (atomList.size()>rowNum){
//                    Element element = atomList.get(rowNum);
//                    keyWord.getAtomList().add(element);
//                    keyWord.getChangedAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        keyWord.getCoverAtomList().add(element);
//                    }
//                    link("KeyWordMap",dadNode,keyWord);
//                }else {
//                    traceln("the row < size! ");
//                }
//            }
//        }else if (operation == "delete"){
//            if (rowNum!=-1&&columnNum!=-1){
//                if (atomList.size()>rowNum){
//                    if (atomList.get(rowNum).getList().size()>columnNum){
//                        keyWord.getAtomList().get(rowNum).getList().remove(columnNum);
//                        keyWord.getAtomList().get(rowNum).getAtomlist().remove(columnNum);
//
//                        link("KeyWordMap",dadNode,keyWord);
//                    }else {
//                        traceln("the column < size! ");
//                    }
//                }else{
//                    traceln("the row < size! ");
//                }
//            }else if (rowNum!=-1&&columnNum==-1){
//                if (atomList.size()>rowNum){
//                    keyWord.getAtomList().remove(rowNum);
//                    link("KeyWordMap",dadNode,keyWord);
//                }else {
//                    traceln("the row < size! ");
//                }
//            }
//        }else if (operation == "change"){
//            if (rowNum!=-1&&columnNum!=-1){
//                if (atomList.size()>rowNum){
//                    if (atomList.get(rowNum).getList().size()>columnNum){
//                        Atom atom = atomList.get(rowNum).getAtomlist().get(columnNum);
//                        Element element = new Element();
//                        element.getList().add(atom.getAtom());
//                        element.getAtomlist().add(atom);
//                        keyWord.getAtomList().add(element);
//                        keyWord.getChangedAtomList().add(element);
//                        if (isCovered(copyObjectTest1.getO(),dadNode)){
//                            keyWord.getCoverAtomList().add(element);
//                        }
//                        link("KeyWordMap",dadNode,keyWord);
//                    }else {
//                        traceln("the column < size! ");
//                    }
//                }else{
//                    traceln("the row < size! ");
//                }
//            }else if (rowNum!=-1&&columnNum==-1){
//                if (atomList.size()>rowNum){
//                    Element element = atomList.get(rowNum);
//                    keyWord.getAtomList().add(element);
//                    keyWord.getChangedAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        keyWord.getCoverAtomList().add(element);
//                    }
//                    link("KeyWordMap",dadNode,keyWord);
//                }else {
//                    traceln("the row < size! ");
//                }
//            }
//        }
//
//        link("ChangeMap",dadNode,copyObjectTest1.getO());
//        traceln("End: "+linkValues("ChangeMap"));
//        traceln("ChangeMap: "+value);
//    }
//
//    public void dealwithKeyWord(String sonNode,String dadNode,String row,String column,String rowType,String columnType,String operation,Object value) throws Exception {
//        //traceln("before update: "+linkValues("KeyWordMap"));
//        traceln("Start: "+linkValues("ChangeMap"));
//        traceln("beforeChangeMap: "+value);
//        CopyObjectTest copyObjectTest = new CopyObjectTest(value);
//        CopyObjectTest copyObjectTest1 = (CopyObjectTest) copyObjectTest.clone();
//        KeyWord dadkeyWord = (KeyWord) linked("KeyWordMap",dadNode);
//        KeyWord sondkeyWord = (KeyWord) linked("KeyWordMap",sonNode);
//        int rowNum = -1;
//        int columnNum = -1;
//        if (rowType.equals("Int")&&columnType.equals("Int")){
//            rowNum = Integer.parseInt(row);
//            columnNum = Integer.parseInt(column);
//        }else  if (rowType.equals("Int")&&columnType.equals("")){
//            rowNum = Integer.parseInt(row);
//        }else if (rowType.equals("")&&columnType.equals("Int")){
//            columnNum = Integer.parseInt(column);
//        }else if (rowType.equals("Variable")){
//            rowNum = (int)linked("count",row);
//        }else if (columnType.equals("Variable")){
//            columnNum = (int)linked("count",column);
//        }
//
//        List<Element> atomList = sondkeyWord.getAtomList();
//       // traceln("row and column : "+rowNum+ " "+columnNum);
//       // traceln("atomList : "+atomList);
//        if (rowNum!=-1&&columnNum!=-1){
//            if (operation.equals("add")){
//                if (atomList.size()>rowNum){
//                    if (atomList.get(rowNum).getAtomlist().size()>columnNum){
//                        Atom atom = atomList.get(rowNum).getAtomlist().get(columnNum);
//                        Element element = new Element();
//                        element.getList().add(atom.getAtom());
//                        element.getAtomlist().add(atom);
//                        dadkeyWord.getAtomList().add(element);
//                        dadkeyWord.getChangedAtomList().add(element);
//                        if (isCovered(copyObjectTest1.getO(),dadNode)){
//                            dadkeyWord.getCoverAtomList().add(element);
//                        }
//                        link("KeyWordMap",dadNode,dadkeyWord);
//                    }else {
//                        traceln("the column < size! ");
//                    }
//                }else{
//                    traceln("the row < size! ");
//                }
//            }else if (operation.equals("change")){
//                {
//                    if (atomList.size()>rowNum){
//                        if (atomList.get(rowNum).getAtomlist().size()>columnNum){
//                            Atom atom = atomList.get(rowNum).getAtomlist().get(columnNum);
//                            Element element = new Element();
//                            element.getList().add(atom.getAtom());
//                            element.getAtomlist().add(atom);
//                            dadkeyWord.getAtomList().clear();
//                            dadkeyWord.getAtomList().add(element);
//                            dadkeyWord.getChangedAtomList().add(element);
//                            if (isCovered(copyObjectTest1.getO(),dadNode)){
//                                dadkeyWord.getCoverAtomList().add(element);
//                            }
//                            link("KeyWordMap",dadNode,dadkeyWord);
//                        }else {
//                            traceln("the column < size! ");
//                        }
//                    }else{
//                        traceln("the row < size! ");
//                    }
//                }
//            }
//
//        }else if (rowNum!=-1&&columnNum==-1){
//            if (operation.equals("add")){
//                if (atomList.size()>rowNum){
//                    Element element = atomList.get(rowNum);
//                    dadkeyWord.getAtomList().add(element);
//                    dadkeyWord.getChangedAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        dadkeyWord.getCoverAtomList().add(element);
//                    }
//                    link("KeyWordMap",dadNode,dadkeyWord);
//                }else {
//                    traceln("the row < size! ");
//                }
//            }else if (operation.equals("change")){
//                if (atomList.size()>rowNum){
//                    Element element = atomList.get(rowNum);
//                    dadkeyWord.getAtomList().clear();
//                    dadkeyWord.getAtomList().add(element);
//                    dadkeyWord.getChangedAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        dadkeyWord.getCoverAtomList().add(element);
//                    }
//                    link("KeyWordMap",dadNode,dadkeyWord);
//                }else {
//                    traceln("the row < size! ");
//                }
//            }
//
//        }else if (rowNum==-1&&columnNum!=-1){
//            if (operation.equals("add")){
//                if (atomList.get(0).getAtomlist().size()>columnNum){
//                    Atom atom = atomList.get(0).getAtomlist().get(columnNum);
//                    Element element = new Element();
//                    element.getList().add(atom.getAtom());
//                    element.getAtomlist().add(atom);
//                    dadkeyWord.getChangedAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        dadkeyWord.getCoverAtomList().add(element);
//                    }
//                    dadkeyWord.getAtomList().add(element);
//                    link("KeyWordMap",dadNode,dadkeyWord);
//                }else {
//                    traceln("the column < size! ");
//                }
//            }else if (operation.equals("change")){
//                if (atomList.get(0).getList().size()>columnNum){
//                    Atom atom = atomList.get(0).getAtomlist().get(columnNum);
//                    Element element = new Element();
//                    element.getList().add(atom.getAtom());
//                    element.getAtomlist().add(atom);
//                    dadkeyWord.getAtomList().clear();
//                    dadkeyWord.getAtomList().add(element);
//                    if (isCovered(copyObjectTest1.getO(),dadNode)){
//                        dadkeyWord.getCoverAtomList().add(element);
//                    }
//                    dadkeyWord.getChangedAtomList().add(element);
//                    link("KeyWordMap",dadNode,dadkeyWord);
//                }else {
//                    traceln("the column < size! ");
//                }
//            }
//        }
//
//       // traceln("after update: "+linkValues("KeyWordMap"));
//
//        link("ChangeMap",dadNode,copyObjectTest1.getO());
//        traceln("End: "+linkValues("ChangeMap"));
//        traceln("ChangeMap: "+value);
//    }
//
//
//    public void dealwithKeyWord(String sonname, String fathername,String operation,Object value) throws Exception {
//        CopyObjectTest copyObjectTest = new CopyObjectTest(value);
//        CopyObjectTest copyObjectTest1 = (CopyObjectTest) copyObjectTest.clone();
//        traceln("Start: "+linkValues("ChangeMap"));
//        traceln("beforeChangeMap: "+copyObjectTest);
//        KeyWord dadkeyWord = (KeyWord)linked("KeyWordMap", fathername);
//        KeyWord sondkeyWord = (KeyWord)linked("KeyWordMap", sonname);
//        List<Element> atomList = sondkeyWord.getAtomList();
//
//
//        if (operation.equals("change")){
//            dadkeyWord.getAtomList().clear();
//            for (Element element:atomList){
//                dadkeyWord.getChangedAtomList().add(element);
//                if (isCovered(copyObjectTest1.getO(),fathername)){
//                    dadkeyWord.getCoverAtomList().add(element);
//                }
//                dadkeyWord.getAtomList().add(element);
//            }
//        }else if (operation.equals("add")){
//            for (Element element:atomList){
//                dadkeyWord.getChangedAtomList().add(element);
//                if (isCovered(copyObjectTest1.getO(),fathername)){
//                    dadkeyWord.getCoverAtomList().add(element);
//                }
//                dadkeyWord.getAtomList().add(element);
//            }
//        }else if (operation.equals("delete")){
//            for (Element element:atomList){
//                dadkeyWord.getAtomList().remove(element);
//            }
//        }
//
//        link("ChangeMap",fathername,copyObjectTest1.getO());
//        traceln("End: "+linkValues("ChangeMap"));
//        traceln("afterChangeMap: "+copyObjectTest);
//    }
//
//    public void forAtom(String sonname, String fathername) {
//      //  traceln("KeyWordMap: " + linkValues("KeyWordMap"));
//        KeyWord dadkeyWord = (KeyWord)linked("KeyWordMap", fathername);
//        KeyWord sondkeyWord = (KeyWord)linked("KeyWordMap", sonname);
//       // traceln("forAtom: " + readCounter(fathername) + " " +fathername);
//        int rowNum = readCounter(fathername);
//        int columnNum = -1;
//        List<Element> atomList = sondkeyWord.getAtomList();
//        if (rowNum != -1 && columnNum == -1 && atomList.size() > rowNum) {
//            Element element = (Element)atomList.get(rowNum);
//         //   traceln("dadkeyWord.getAtomList: " + dadkeyWord);
//        //    traceln("ElementWord.getAtomList: " + element);
//            dadkeyWord.getAtomList().clear();
//            dadkeyWord.getAtomList().add(element);
//        }
//
//    }
//
//
////    public boolean ischanged(String name,Object value){
////        boolean flag = true;
////        Object formerValue = linked("ChangeMap",name);
////        traceln(name + "  changedValue: "+value);
////        if (formerValue.equals(value)){
////            flag = false;
////        }else {
////            link("ChangeMap",name,value);
////        }
////        return flag;
////    }
//
//
//    public boolean isCovered(Object value,String name) throws Exception {
//
//        boolean iscover = false;
//        String dataStructure = value.getClass().getSimpleName();
//        traceln("Structure: "+dataStructure);
//        if (dataStructure.equals("HashMap")) {
//            HashMap map = (HashMap) objectToMap(value);
//            Object formerValue = linked("ChangeMap", name);
//            HashMap formerMap = (HashMap) objectToMap(formerValue);
//            traceln("map: "+ value);
//            traceln("formerMap: "+ formerValue);
//            if (map.size() == formerMap.size() && !map.equals(formerMap)) {
//                traceln("EQU!!!!");
//                iscover = true;
//            }
//         }
//
//        return iscover;
//    }
//
//    public static Map<Object, Object> objectToMap(Object obj) throws Exception {
//        if(obj == null){
//            return null;
//        }
//
//        Map<Object, Object> map = new HashMap<Object, Object>();
//
//        Field[] declaredFields = obj.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            field.setAccessible(true);
//            map.put(field.getName(), field.get(obj));
//        }
//
//        return map;
//    }
//
//
//    public String printMap(Map map){
//        String str = "[ ";
//        String inStr = "";
//        for (Object ob: map.keySet()){
//            inStr = "[ ";
//            map.get(ob);
//            inStr = inStr + ob+" , "+map.get(ob)+ " ]";
//            str = str + inStr+" ";
//        }
//        str = str + "]\n";
//        return  str;
//    }
//
//}
