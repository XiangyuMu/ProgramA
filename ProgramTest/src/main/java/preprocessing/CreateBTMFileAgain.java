package preprocessing;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import searchOnInternet.Example02;

import java.io.*;
import java.util.*;

public class CreateBTMFileAgain {
    private List<KeyWordAtom> KeyWordsList = new ArrayList<>();
    Map VariableDeclararion = new HashMap();
    Map VariableDeclarationOnLine = new HashMap();
    List<KeyWord> keyWordList = new ArrayList<>();
    List<ForEachState> forEachStateList = new ArrayList<>();
    public List<Object> textractFromXML() throws FileNotFoundException, DocumentException {
        System.out.println("the start of function textractFromXML");
        List<Object> list = new ArrayList<>();
        list = extractTromXML("xmlTest123.xml");
        System.out.println("list: "+list);

        for (int i = 0;i<list.size();i++){
            VariableDeclararion.put(String.valueOf(i),list.get(i));

        }
        System.out.println("linkNames: "+VariableDeclararion.keySet());
        System.out.println("linkValues: "+VariableDeclararion.values());
        System.out.println("the end of function textractFromXML");
        return list;
    }

    public List<Object> extractTromXML(String fileName) throws FileNotFoundException, DocumentException {
        System.out.println("the start of function extractTromXML");
        List<Object> objectList = new ArrayList<>();
        InputStream in = null;
        List<Element> elementList = null;
        SAXReader reader = new SAXReader();
        in = new FileInputStream(new File(fileName));
        Document doc = reader.read(in);
        Element root = doc.getRootElement();
        elementList = root.elements();
        for(Element ele : elementList){
            objectList.add(extractElement(ele));
        }
        System.out.println("the end of function extractTromXML");
        return  objectList;
    }

    public Object extractElement(Element element){
        System.out.println("the strat of function extractElement");
        String name = element.getName();
        switch(name){
            case "Assign":{
                AssignExpression ae = new AssignExpression(element);
                System.out.println(ae);
                return ae;
            }
            case "BinaryExpr":{
                BinaryExpression be = new BinaryExpression(element);

                System.out.println(be);
                return be;
            }
            case "Boolean":{
                BooleanLiteralExpression boe = new BooleanLiteralExpression(element);

                System.out.println(boe);
                return boe;
            }
            case "Char":{
                CharLiteralExpression cle = new CharLiteralExpression(element);

                System.out.println(cle);
                return cle;
            }
            case "Double":{
                DoubleLiteralExpression dle = new DoubleLiteralExpression(element);

                System.out.println(dle);
                return dle;
            }
            case "FieldAccessExpr":{
                FieldAccessExpression fae = new FieldAccessExpression(element);

                System.out.println(fae);
                return fae;
            }
            case "Integer":{
                IntegerLiteralExpression ile = new IntegerLiteralExpression(element);

                System.out.println(ile);
                return ile;
            }
            case "Long":{
                LongLiteralExpression lle = new LongLiteralExpression(element);

                System.out.println(lle);
                return lle;
            }
            case "Method":{
                MethodCallExpression mce = new MethodCallExpression(element);

                System.out.println(mce);
                return mce;
            }
            case "Name":{
                NameExpression ne = new NameExpression(element);

                System.out.println(ne);
                return ne;
            }
            case "SimpleName":{
                SimpleNameExpression sne = new SimpleNameExpression(element);

                System.out.println(sne);

                return sne;
            }
            case "VariableDeclarationExpr":{
                VariableDeclaratorExpression vde = new VariableDeclaratorExpression(element);

                System.out.println(vde);
                return vde;
            }

            case "StringLiteralExpr":{
                StringLiteralExpression sle = new StringLiteralExpression(element);

                System.out.println(sle);
                return sle;
            }
            case "Cast":{
                CastExpression ce = new CastExpression(element);
                System.out.println(ce);
                return ce;
            }
            case "VariableDeclaratorInfo":{
                VariableDeclaratorInfo variableDeclaratorInfo = new VariableDeclaratorInfo(element);
                System.out.println(variableDeclaratorInfo);
                return variableDeclaratorInfo;
            }

            case "ForEach": {
                ForEachState forEachState = new ForEachState(element);
                System.out.println(forEachState);
                forEachStateList.add(forEachState);
                return forEachState;
            }

            case "ObjectCreation": {
                System.out.println("read ObjectCreate");
                ObjectCreation objectCreation = new ObjectCreation(element);
                System.out.println(objectCreation);
                return  objectCreation;
            }

            default:{
                System.out.println("can't find this type!!!!!!!!!!!!");
                return null;
            }

        }
    }

    public void parserVariableDeclararion(int line){
        Object object = null;
        KeyWordAtom kwa = new KeyWordAtom();
        for (int i = 0;i<VariableDeclararion.size();i++){
            if (line == getTheLine(VariableDeclararion.get(String.valueOf(i)))){
                object = VariableDeclararion.get(String.valueOf(i));
                break;
            }
        }
        if (object == null){
            System.out.println("there is no statement in this line !!");
        }
        System.out.println("Object: "+object);
        kwa.copyValue(dealWithClassOfStatement(object));
        System.out.println("keyWordAtom: "+kwa);
        KeyWordsList.add(kwa);
    }


    public int getTheLine(Object object){
        int line;
        String type = object.getClass().getSimpleName();
        System.out.println("typeGEtTHELINE: "+type);
        switch (type){
            case "VariableDeclaratorInfo":{
                VariableDeclaratorInfo vdi = (VariableDeclaratorInfo)object;
                line = vdi.line;
                return line;
            }
            case "AssignExpression": {
                AssignExpression ae = (AssignExpression)object;
                line = ae.line;
                return line;
            }

            case "MethodCallExpression": {
                MethodCallExpression mce = (MethodCallExpression)object;
                line = mce.line;
                System.out.println(line);
                return  line;
            }
            default:{
                return -1;
            }
        }

    }


    public boolean isKeyWord(String name){
        boolean flag = false;
        for (KeyWord keyWord : keyWordList){
            if (keyWord.getName().equals(name)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public KeyWordAtom dealWithClassOfStatement(Object object){
        String type = object.getClass().getSimpleName();
        KeyWordAtom keyWordAtom = new KeyWordAtom();
        System.out.println("Stype: "+type);
        switch (type){
            case "VariableDeclaratorInfo":{
//                VariableDeclaratorInfo vdi = (VariableDeclaratorInfo)object;
//                keyWordAtom.copyValue(dealWithClassOfStatement(vdi.objectList.get(0)));
//                keyWordAtom.setKeyWordName(vdi.nameStr);
//                keyWordAtom.setLine(vdi.line);
//                System.out.println("VdiKeyWordAtom: "+keyWordAtom);
//                return keyWordAtom;


                VariableDeclaratorInfo vdi = (VariableDeclaratorInfo)object;
                String name = vdi.nameStr;
                Object valueObject = vdi.objectList.get(0);
                List<KeyWordAtom> keyWordAtom1 = dealWithClassOfExpression(valueObject);

                keyWordAtom.line = vdi.line;
                keyWordAtom.setKeyWordName(name);
                keyWordAtom.changeKeyWordAtom(keyWordAtom1);

                return keyWordAtom;
            }
            case "CastExpression":{
                CastExpression ce = (CastExpression)object;
                keyWordAtom.copyValue(dealWithClassOfStatement(ce.objectList.get(0)));
                return keyWordAtom;
            }
            case "MethodCallExpression":{
//                MethodCallExpression mce = (MethodCallExpression)object;
////                keyWordAtom.copyValue(mce.getKeyWordAtom());
//
//                if (mce.objectScope.getClass().getSimpleName().equals("NameExpression")){
//                    String name = ((NameExpression)(mce.objectScope)).name;
//                    keyWordAtom.setKeyWordName(name);
//
//                }else if (mce.objectScope.getClass().getSimpleName().equals("MethodCallExpression")){
//                    //System.out.println(objectScope.getClass().getSimpleName());
//                    keyWordAtom.copyValue(((MethodCallExpression)(mce.objectScope)).getKeyWordAtom());
//                }
//
//                if (mce.parameterObjectList.size()!=0){
//                    if (mce.parameterObjectList.size()==1){
//                        Object parameter = mce.parameterObjectList.get(0);
//                        switch (parameter.getClass().getSimpleName()){
//                            case "IntegerLiteralExpression":{
//                                IntegerLiteralExpression ile = (IntegerLiteralExpression)parameter;
//                                if (keyWordAtom.row.equals("")){
//                                    keyWordAtom.setRowType("int");
//                                    keyWordAtom.setRow(String.valueOf(ile.getI()));
//                                }else{
//                                    keyWordAtom.setColumnType("int");
//                                    keyWordAtom.setColumn(String.valueOf(ile.getI()));
//                                }
//                                break;
//                            }
//                            //case ""
//                        }
//                    }else{
//                        System.out.println("parameters are more than one!!");
//                    }
//                    return keyWordAtom;
//                }
//                System.out.println("RowType:"+keyWordAtom);
//                return keyWordAtom;
                String structure = "";

                List<MethodWord> methodWordList = new ArrayList<>();
                MethodCallExpression mce = (MethodCallExpression)object;

                String methodName;
                keyWordAtom.line = mce.line;
                while(true){
                    System.out.println("ScopeType: "+mce.objectScope.getClass().getSimpleName() );
                    System.out.println("ScopeName: "+mce.objectName.getClass().getSimpleName() );
                    if (mce.objectScope.getClass().getSimpleName().equals("NameExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        methodName = ((NameExpression)(mce.objectScope)).name;
                        System.out.println("methodName: "+methodName);
                        System.out.println("Parameters: "+mce.parameterObjectList);
                        break;
                    }else if (mce.objectScope.getClass().getSimpleName().equals("MethodCallExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        System.out.println("Parameters: "+mce.parameterObjectList);
                        mce = (MethodCallExpression) mce.objectScope;
                    }else if (mce.objectScope.getClass().getSimpleName().equals("FieldAccessExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        methodName = ((FieldAccessExpression)(mce.objectScope)).name;
                        System.out.println("methodName: "+mce.objectScope);
                        break;
                    }
                }


                boolean flag = false;
                for (int i = 0;i<keyWordList.size();i++){
                    if (keyWordList.get(i).name.equals(methodName)){
                        flag = true;
                        structure = keyWordList.get(i).dataStructure;
                        break;
                    }
                }




                Collections.reverse(methodWordList);
                System.out.println(methodWordList);
                if (flag){
                    keyWordAtom.keyWordName =methodName;
                    if (structure == "ElemwntList"){
                        if (methodWordList.get(0).name=="getList"&&methodWordList.get(1).name=="get"){
                            keyWordAtom.column = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.columnType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }if (methodWordList.get(2).name=="getList"&&methodWordList.get(3).name=="get"){
                            keyWordAtom.row = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.rowType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }
                    }else if (structure == "Element"){
                        if (methodWordList.get(0).name=="getList"&&methodWordList.get(1).name=="get"){
                            keyWordAtom.row = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.rowType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }
                    }else{
                        for(MethodWord methodWord : methodWordList){
                            if (methodWord.parameterList.size()!=0){

                                if (methodWord.name.equals("add")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else if (methodWord.name.equals("delete")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.deleteKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else if (methodWord.name.equals("get")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else{
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }


                            }
                        }
                    }



                }else{
                    keyWordAtom.setKeyWordName("NULL");
                    for(MethodWord methodWord : methodWordList){
                        if (methodWord.parameterList.size()!=0){

                            if (methodWord.name.equals("add")){
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }else if (methodWord.name.equals("delete")){
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.deleteKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }else {
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }

                        }
                    }
                }
                return keyWordAtom;
            }


            case "AssignExpression": {
                AssignExpression ae = (AssignExpression)object;
                String operator = ae.operator;

                keyWordAtom.line = ae.line;
                switch (operator){
                    case "=":{
                        keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(ae.valueObject));
                        keyWordAtom.setKeyWordName(ae.targetObject.toString());
                        keyWordAtom.line = ae.line;
                        break;
                    }
                    case "+=": {
                        keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(ae.valueObject));
                        keyWordAtom.setKeyWordName(ae.targetObject.toString());
                        break;
                    }


                }
                return keyWordAtom;
            }
            default:{
                return null;
            }
        }
    }


    public List<KeyWordAtom> dealWithClassOfExpression(Object object){
        String type = object.getClass().getSimpleName();
        List<KeyWordAtom> keyWordAtomList = new ArrayList<>();
        System.out.println("type: "+type);
        switch(type){
            case "CastExpression":{
                CastExpression ce = (CastExpression)object;
                keyWordAtomList = (dealWithClassOfExpression(ce.objectList.get(0)));
                return keyWordAtomList;
            }

            case "IntegerLiteralExpression":{
                KeyWordAtom keyWordAtom = new KeyWordAtom();
                keyWordAtom.setKeyWordName("NULL");
                keyWordAtom.column = String.valueOf(((IntegerLiteralExpression) object).i);
                keyWordAtom.columnType = "int";
                keyWordAtomList.add(keyWordAtom);
                return keyWordAtomList;
            }

            case "MethodCallExpression":{
                KeyWordAtom keyWordAtom = new KeyWordAtom();
                String structure = "";
                List<MethodWord> methodWordList = new ArrayList<>();
                MethodCallExpression mce = (MethodCallExpression)object;
                String methodName;
                keyWordAtom.line = mce.line;
                while(true){
                    if (mce.objectScope.getClass().getSimpleName().equals("NameExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        methodName = ((NameExpression)(mce.objectScope)).name;
                        System.out.println("methodName: "+methodName);
                        break;
                    }else if (mce.objectScope.getClass().getSimpleName().equals("MethodCallExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        System.out.println("Parameters: "+mce.parameterObjectList);
                        mce = (MethodCallExpression) mce.objectScope;
                    }else if (mce.objectScope.getClass().getSimpleName().equals("FieldAccessExpression")){
                        methodWordList.add(new MethodWord(mce.objectName,mce.parameterObjectList));
                        methodName = ((FieldAccessExpression)(mce.objectScope)).name;
                        System.out.println("methodName: "+mce.objectScope);
                        break;
                    }
                }


                boolean flag = false;
                for (int i = 0;i<keyWordList.size();i++){
                    if (keyWordList.get(i).name.equals(methodName)){
                        flag = true;
                        structure = keyWordList.get(i).dataStructure;
                        break;
                    }
                }
                System.out.println("structure: "+structure);
                Collections.reverse(methodWordList);
                System.out.println("methodWordList: "+methodWordList);
                if (flag){
                    keyWordAtom.keyWordName =methodName;
                    if (structure.equals("ElemwntList")){
                        System.out.println("123123");
                        if (methodWordList.get(0).name.equals("getList")&&methodWordList.get(1).name.equals("get")){
                            keyWordAtom.column = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.columnType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }if (methodWordList.get(2).name.equals("getList")&&methodWordList.get(3).name.equals("get")){
                            keyWordAtom.row = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.rowType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }
                    }else if (structure.equals("Element") ){
                        if (methodWordList.get(0).name.equals("getList")&&methodWordList.get(1).name.equals("get")){
                            keyWordAtom.row = methodWordList.get(1).parameterList.get(0).toString();
                            keyWordAtom.rowType = methodWordList.get(1).parameterList.get(0).getClass().getSimpleName();
                        }
                    }else{
                        for(MethodWord methodWord : methodWordList){
                            if (methodWord.parameterList.size()!=0){

                                if (methodWord.name.equals("add")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else if (methodWord.name.equals("delete")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.deleteKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else if (methodWord.name.equals("get")){
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }else{
                                    for (int k = 0;k<methodWord.parameterList.size();k++){
                                        keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                    }
                                }


                            }
                        }
                    }



                }else{
                    keyWordAtom.setKeyWordName("NULL");
                    for(MethodWord methodWord : methodWordList){
                        if (methodWord.parameterList.size()!=0){

                            if (methodWord.name.equals("add")){
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }else if (methodWord.name.equals("delete")){
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.deleteKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }else if (methodWord.name.equals("get")){
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }else{
                                for (int k = 0;k<methodWord.parameterList.size();k++){
                                    keyWordAtom.changeKeyWordAtom(dealWithClassOfExpression(methodWord.parameterList.get(k)));
                                }
                            }


                        }
                    }
                }
                keyWordAtomList.add(keyWordAtom);
                return keyWordAtomList;
            }

            case"ObjectCreation":{
                ObjectCreation objectCreation = (ObjectCreation) object;
                System.out.println("objectCreation: "+objectCreation);
                List<Object> paramterList = objectCreation.parameterObjectList;
                for (Object o : paramterList){
                    KeyWordAtom keyWordAtom = new KeyWordAtom();
                    System.out.println(dealWithClassOfExpression(o));
                    if (dealWithClassOfExpression(o).size()!=0){
                        keyWordAtom.addKeyWordAtom(dealWithClassOfExpression(o));
                    }

                    keyWordAtomList.add(keyWordAtom );
                }
                return keyWordAtomList;

            }

            default:{
                System.out.println("output is null");

                return keyWordAtomList;
            }
        }
    }

    public void readKeyWordFromFile() throws IOException {
        keyWordList = new DealWithInfoToFile().readKeyWordfile();
        System.out.println("KeyWordList: "+keyWordList);

    }


    public void printIntoBtmFile() throws IOException {
        FileWriter file = new FileWriter("ProgramTest/src/main/java/byteman/"+"Test"+".btm");
        BufferedWriter output = new BufferedWriter(file);

        String  str1 = "";

        System.out.println("KeyWordsList: "+ KeyWordsList);
        System.out.println("ForList: "+ forEachStateList);

        for (int j = 0; j<KeyWordsList.size();j++){
            int line = KeyWordsList.get(j).line;
            for (int forNum = 0;forNum<forEachStateList.size();forNum++){
                if (line<=forEachStateList.get(forNum).last&&line>=forEachStateList.get(forNum).line){
                    str1 = str1 + CreateBTMString(KeyWordsList.get(j),forEachStateList.get(forNum));
                }else{
                    str1 = str1 + CreateBTMString(KeyWordsList.get(j),null);
                }
            }
        }


        for (int i = 0;i<KeyWordsList.size();i++){
            if(KeyWordsList.get(i).getColumn().equals("")&&KeyWordsList.get(i).getRow().equals("")){
                continue;
            }


            str1 = "RULE "+KeyWordsList.get(i).getKeyWordName()+""+i+"\n" +
                    "CLASS searchOnInternet."+ "Example02" +"\n" +
                    "METHOD reduce(ElemwntList)\n" +
                    "HELPER byteman.VariableHelper01\n"+
                    "AT LINE "+KeyWordsList.get(i).getLine()+"\n" +
                    "IF true\n" +
                    "DO ";
            if (!KeyWordsList.get(i).getRowType().equals("int")){
                str1 = str1 + "printPara($"+KeyWordsList.get(i).getRow()+",$"+KeyWordsList.get(i).getColumn()+");\n";
            }else {
                str1 = str1 + "printPara("+KeyWordsList.get(i).getRow()+","+KeyWordsList.get(i).getColumn()+");\n";
            }
            str1 = str1 + "ENDRULE\n";
            output.write(str1);
        }output.close();
    }


    public String  CreateBTMString(KeyWordAtom keyWordAtom,ForEachState forEachState){
        String str = "";
        if (forEachState.equals(null)){

        }else{

        }

        return str;
    }

}
