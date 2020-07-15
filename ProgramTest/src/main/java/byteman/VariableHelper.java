//package byteman;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.jboss.byteman.rule.Rule;
//import org.jboss.byteman.rule.helper.Helper;
//import preprocessing.*;
//import reduceExample.Atom;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//@SuppressWarnings("unchecked")
//public class VariableHelper extends Helper {
//
//    protected VariableHelper(Rule rule) {
//        super(rule);
//    }
//
//    /**
//     * 提取了关键词信息
//     * @throws IOException
//     */
//    public void readKeyWordFromFile() throws IOException {
//        traceln("the start of function readKeyWordFromFile");
//        createLinkMap("KeyWordMap");
//        createLinkMap("ValueTable");
//        createLinkMap("currentValue");
//        List<KeyWord> lk = new ArrayList<>();
//        lk = new DealWithInfoToFile().readKeyWordfile();
//        traceln("Link: "+lk);
//        for(int i = 0;i<lk.size();i++){
//            traceln("lk.get: "+lk.get(i).getName());
//            link("KeyWordMap",lk.get(i).getName(),lk.get(i));
//            traceln("keyWordMap.get: "+linked("KeyWordMap"));
//            link("ValueTable",lk.get(i).getName(),null);
//        }
//        traceln("KeyWordMap: "+linked("KeyWordMap"));
//        traceln("ValueTable: "+linked("ValueTable"));
//        traceln("the end of function readKeyWordFromFile");
//    }
//
//    /**
//     *
//     * @param mapNmae
//     * @param keyWordName
//     * @param atom
//     */
//    public void addAtomToKeyWord(String mapNmae, String keyWordName, Atom atom){
//        traceln("the start of function addAtomToKeyWord");
//        KeyWord keyWord = (KeyWord) linked(mapNmae,keyWordName);
//        int formerLine;
//        if (keyWord.getAtomList().size()==0){
//            reduceExample.Element ele = new reduceExample.Element();
//            ele.getList().add(atom);
//            keyWord.getAtomList().add(ele);
//        }else{
//            formerLine = keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().get(0).getRow();
//            if(formerLine==atom.getRow()){
//                keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().add(atom);
//            }else{
//                reduceExample.Element element = new reduceExample.Element();
//                element.getList().add(atom);
//                keyWord.getAtomList().add(element);
//            }
//
//        }
//        link(mapNmae,keyWordName,keyWord);
//        traceln("updatedMap: "+linked(mapNmae,keyWordName));
//        traceln("the end of function addAtomToKeyWord");
//    }
//
//    /**
//     * create the map "VariableDeclaration" the key : number (1,2,3...)   the value : the information of  each statement
//     * @return the value in map
//     * @throws FileNotFoundException
//     * @throws DocumentException
//     */
//    public List<Object> textractFromXML() throws FileNotFoundException, DocumentException {
//        traceln("the start of function textractFromXML");
//        List<Object> list = new ArrayList<>();
//        list = extractTromXML("xmlTest123.xml");
//        traceln("list: "+list);
//        createLinkMap("VariableDeclararion");
//        createLinkMap("VariableDeclarationOnLine");
//        for (int i = 0;i<list.size();i++){
//            link("VariableDeclararion",String.valueOf(i),list.get(i));
//        }
//        traceln("linkNames: "+linkNames("VariableDeclararion"));
//        traceln("linkValues: "+linkValues("VariableDeclararion"));
//        traceln("the end of function textractFromXML");
//        return list;
//    }
//
//    public List<Object> extractTromXML(String fileName) throws FileNotFoundException, DocumentException {
//        traceln("the start of function extractTromXML");
//        List<Object> objectList = new ArrayList<>();
//        InputStream in = null;
//        List<Element> elementList = null;
//        SAXReader reader = new SAXReader();
//        in = new FileInputStream(new File(fileName));
//        Document doc = reader.read(in);
//        Element root = doc.getRootElement();
//        elementList = root.elements();
//        for(Element ele : elementList){
//            objectList.add(extractElement(ele));
//        }
//        traceln("the end of function extractTromXML");
//        return  objectList;
//    }
//
//    public Object extractElement(Element element){
//        traceln("the strat of function extractElement");
//        String name = element.getName();
//        switch(name){
//            case "AssignExpr":{
//                AssignExpression ae = new AssignExpression(element);
//                System.out.println(ae);
//                return ae;
//            }
//            case "BinaryExpr":{
//                BinaryExpression be = new BinaryExpression(element);
//
//                System.out.println(be);
//                return be;
//            }
//            case "Boolean":{
//                BooleanLiteralExpression boe = new BooleanLiteralExpression(element);
//
//                System.out.println(boe);
//                return boe;
//            }
//            case "Char":{
//                CharLiteralExpression cle = new CharLiteralExpression(element);
//
//                System.out.println(cle);
//                return cle;
//            }
//            case "Double":{
//                DoubleLiteralExpression dle = new DoubleLiteralExpression(element);
//
//                System.out.println(dle);
//                return dle;
//            }
//            case "FieldAccessExpr":{
//                FieldAccessExpression fae = new FieldAccessExpression(element);
//
//                System.out.println(fae);
//                return fae;
//            }
//            case "Integer":{
//                IntegerLiteralExpression ile = new IntegerLiteralExpression(element);
//
//                System.out.println(ile);
//                return ile;
//            }
//            case "Long":{
//                LongLiteralExpression lle = new LongLiteralExpression(element);
//
//                System.out.println(lle);
//                return lle;
//            }
//            case "Method":{
//                MethodCallExpression mce = new MethodCallExpression(element);
//
//                System.out.println(mce);
//                return mce;
//            }
//            case "Name":{
//                NameExpression ne = new NameExpression(element);
//
//                System.out.println(ne);
//                return ne;
//            }
//            case "SimpleName":{
//                SimpleNameExpression sne = new SimpleNameExpression(element);
//
//                System.out.println(sne);
//
//                return sne;
//            }
//            case "VariableDeclarationExpr":{
//                VariableDeclaratorExpression vde = new VariableDeclaratorExpression(element);
//
//                System.out.println(vde);
//                return vde;
//            }
//
//            case "StringLiteralExpr":{
//                StringLiteralExpression sle = new StringLiteralExpression(element);
//
//                System.out.println(sle);
//                return sle;
//            }
//            case "Cast":{
//                CastExpression ce = new CastExpression(element);
//                System.out.println(ce);
//                return ce;
//            }
//            case "VariableDeclaratorInfo":{
//                VariableDeclaratorInfo variableDeclaratorInfo = new VariableDeclaratorInfo(element);
//                System.out.println(variableDeclaratorInfo);
//                return variableDeclaratorInfo;
//            }
//            default:{
//                System.out.println("can't find this type!!!!!!!!!!!!");
//                return null;
//            }
//
//        }
//    }
//
//    public List<KeyWord> getKeyWordList(int line){
//        traceln("the start of function getKeyWordList");
//        List<KeyWord> keyWordList = new ArrayList<>();
//        Object object = linked("VariableDeclararion",String.valueOf(line));
//        keyWordList = new Tool().getKeyWord(object);
//        traceln("the end of function getKeyWordList");
//        return keyWordList;
//    }
//
//    public void doIt(int line){
//        traceln("the start of function doIt");
//        List<KeyWord> keyWordList = new ArrayList<>();
//        Object object = linked("VariableDeclararion",String.valueOf(line));
//        keyWordList = getKeyWordList(line);
//        KeyWord letf = keyWordList.get(0);
//        List<KeyWord> rightList = new ArrayList<>();
//        for(int i = 1;i<keyWordList.size();i++){
//            rightList.add(keyWordList.get(i));
//        }
//        traceln("the end of function doIt");
//    }
//
//
//    public void dealAllStatement(int line){
//        List<Object> list = linkValues("VariableDeclararion");
//        Object lineob;
//        for(Object ob : list){
//            if (!checkLineOfStatement(line,ob).equals(null)){
//                lineob = checkLineOfStatement(line,ob);
//                break;
//            }
//        }
//
//
//
//
//    }
//
//
//    public Object checkLineOfStatement(int line,Object ob){
//        String type = ob.getClass().getSimpleName();
//        switch (type){
//            case "VariableDeclaratorInfo":{
//                if( ((VariableDeclaratorInfo) ob).getLine() == line){
//                    return ob;
//                }else {
//                    return null;
//                }
//            }
//
//            default:{
//                traceln("no Object line equals "+line);
//                return null;
//            }
//        }
//    }
//
//    public void dealWithVariableDeclaration(Boolean hasCurrentValue){
//        List<KeyWord> keyWordList = getKeyWordList(24);
//
//    }
////
////    public void dealWithVariableDeclaration(int line,String targetType,int targetPara,String ValueType,int valuePara){
////        if(targetPara>=0){
////
////        }else{
////
////        }
////    }
//}
