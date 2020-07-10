package preprocessing;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import searchOnInternet.Example02;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateBTMFileAgain {
    private List<KeyWordAtom> KeyWordsList = new ArrayList<>();
    Map VariableDeclararion = new HashMap();
    Map VariableDeclarationOnLine = new HashMap();
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

    public KeyWordAtom dealWithClassOfStatement(Object object){
        String type = object.getClass().getSimpleName();
        KeyWordAtom keyWordAtom = new KeyWordAtom();
        System.out.println("type: "+type);
        switch (type){
            case "VariableDeclaratorInfo":{
                VariableDeclaratorInfo vdi = (VariableDeclaratorInfo)object;
                keyWordAtom.copyValue(dealWithClassOfStatement(vdi.objectList.get(0)));
                keyWordAtom.setKeyWordName(vdi.nameStr);
                keyWordAtom.setLine(vdi.line);
                System.out.println("VdiKeyWordAtom: "+keyWordAtom);
                return keyWordAtom;
            }
            case "CastExpression":{
                CastExpression ce = (CastExpression)object;
                keyWordAtom.copyValue(dealWithClassOfStatement(ce.objectList.get(0)));
                return keyWordAtom;
            }
            case "MethodCallExpression":{
                MethodCallExpression mce = (MethodCallExpression)object;
//                keyWordAtom.copyValue(mce.getKeyWordAtom());

                if (mce.objectScope.getClass().getSimpleName().equals("NameExpression")){
                    String name = ((NameExpression)(mce.objectScope)).name;
                    keyWordAtom.setKeyWordName(name);

                }else if (mce.objectScope.getClass().getSimpleName().equals("MethodCallExpression")){
                    //System.out.println(objectScope.getClass().getSimpleName());
                    keyWordAtom.copyValue(((MethodCallExpression)(mce.objectScope)).getKeyWordAtom());
                }

                if (mce.parameterObjectList.size()!=0){
                    if (mce.parameterObjectList.size()==1){
                        Object parameter = mce.parameterObjectList.get(0);
                        switch (parameter.getClass().getSimpleName()){
                            case "IntegerLiteralExpression":{
                                IntegerLiteralExpression ile = (IntegerLiteralExpression)parameter;
                                if (keyWordAtom.row.equals("")){
                                    keyWordAtom.setRowType("int");
                                    keyWordAtom.setRow(String.valueOf(ile.getI()));
                                }else{
                                    keyWordAtom.setColumnType("int");
                                    keyWordAtom.setColumn(String.valueOf(ile.getI()));
                                }
                                break;
                            }
                            //case ""
                        }
                    }else{
                        System.out.println("parameters are more than one!!");
                    }
                    return keyWordAtom;
                }
                System.out.println("RowType:"+keyWordAtom);
                return keyWordAtom;
            }
            case "IntegerLiteralExpression":{
                keyWordAtom.setObject(((IntegerLiteralExpression) object).getI());
                return keyWordAtom;
            }

            case "AssignExpression": {
                
            }
            default:{
                return null;
            }
        }
    }


    public void readKeyWordFromFile() throws IOException {
        List<KeyWord> lk = new ArrayList<>();
        lk = new DealWithInfoToFile().readKeyWordfile();
        System.out.println("KeyWordList: "+lk);
    }


    public void printIntoBtmFile() throws IOException {
        FileWriter file = new FileWriter("ProgramTest/src/main/java/byteman/"+"Test"+".btm");
        BufferedWriter output = new BufferedWriter(file);

        String  str1;

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

}
