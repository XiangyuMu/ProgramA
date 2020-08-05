package byteman;

import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;
import preprocessing.DealWithInfoToFile;
import preprocessing.KeyWord;
import reduceExample.Atom;
import reduceExample.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VariableHelper01 extends Helper {

    protected VariableHelper01(Rule rule) {
        super(rule);
    }

    public void initFunc() throws IOException {
        createLinkMap("KeyWordMap");
        createLinkMap("ValueTable");
        List<KeyWord> lk = new ArrayList<>();
        lk = new DealWithInfoToFile().readKeyWordfile();
        traceln("lk: "+lk);
        for(int i = 0;i<lk.size();i++){
            traceln("lk.get: "+lk.get(i).getName());
            link("KeyWordMap",lk.get(i).getName(),lk.get(i));
            traceln("keyWordMap.get: "+linked("KeyWordMap",lk.get(i).getName()));
            link("ValueTable",lk.get(i).getName(),null);
        }
    }

    public void addAtomToKeyWord(String mapNmae, String keyWordName, Atom atom){
        KeyWord keyWord = (KeyWord) linked(mapNmae,keyWordName);
        int formerLine;
        if (keyWord.getAtomList().size()==0){
            reduceExample.Element ele = new reduceExample.Element();
            ele.getList().add(atom);
            keyWord.getAtomList().add(ele);
        }else{
            formerLine = keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().get(0).getRow();
            if(formerLine==atom.getRow()){
                keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().add(atom);
            }else{
                reduceExample.Element element = new reduceExample.Element();
                element.getList().add(atom);
                keyWord.getAtomList().add(element);
            }

        }
        link(mapNmae,keyWordName,keyWord);
        traceln("updatedMap: "+linked(mapNmae,keyWordName));
        traceln(linkValues(mapNmae).get(0).getClass().getName());
    }


    public void dealwithKeyWord(String dadNode,String row,String column,String rowType,String columnType,String operation){
        KeyWord keyWord = (KeyWord) linked("KeyWordMap",dadNode);
        int rowNum = -1;
        int columnNum = -1;
        if (rowType.equals("Int")&&columnType.equals("Int")){
            rowNum = Integer.parseInt(row);
            columnNum = Integer.parseInt(column);
        }else  if (rowType.equals("Int")&&columnType.equals("")){
            rowNum = Integer.parseInt(row);
        }
        List<Element> atomList = keyWord.getAtomList();
        if (operation=="add"){
            if (rowNum!=-1&&columnNum!=-1){
                if (atomList.size()>rowNum){
                    if (atomList.get(rowNum).getList().size()>columnNum){
                        Atom atom = atomList.get(rowNum).getList().get(columnNum);
                        Element element = new Element();
                        element.getList().add(atom);
                        keyWord.getAtomList().add(element);
                        link("KeyWordMap",dadNode,keyWord);
                    }else {
                        traceln("the column < size! ");
                    }
                }else{
                    traceln("the row < size! ");
                }
            }else if (rowNum!=-1&&columnNum==-1){
                if (atomList.size()>rowNum){
                    Element element = atomList.get(rowNum);
                    keyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,keyWord);
                }else {
                    traceln("the row < size! ");
                }
            }
        }else if (operation == "delete"){
            if (rowNum!=-1&&columnNum!=-1){
                if (atomList.size()>rowNum){
                    if (atomList.get(rowNum).getList().size()>columnNum){
                        keyWord.getAtomList().get(rowNum).getList().remove(columnNum);

                        link("KeyWordMap",dadNode,keyWord);
                    }else {
                        traceln("the column < size! ");
                    }
                }else{
                    traceln("the row < size! ");
                }
            }else if (rowNum!=-1&&columnNum==-1){
                if (atomList.size()>rowNum){
                    keyWord.getAtomList().remove(rowNum);
                    link("KeyWordMap",dadNode,keyWord);
                }else {
                    traceln("the row < size! ");
                }
            }
        }else if (operation == "change"){
            if (rowNum!=-1&&columnNum!=-1){
                if (atomList.size()>rowNum){
                    if (atomList.get(rowNum).getList().size()>columnNum){
                        Atom atom = atomList.get(rowNum).getList().get(columnNum);
                        Element element = new Element();
                        element.getList().add(atom);
                        keyWord.getAtomList().add(element);
                        link("KeyWordMap",dadNode,keyWord);
                    }else {
                        traceln("the column < size! ");
                    }
                }else{
                    traceln("the row < size! ");
                }
            }else if (rowNum!=-1&&columnNum==-1){
                if (atomList.size()>rowNum){
                    Element element = atomList.get(rowNum);
                    keyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,keyWord);
                }else {
                    traceln("the row < size! ");
                }
            }
        }
    }

    public void dealwithKeyWord(String sonNode,String dadNode,String row,String column,String rowType,String columnType,String operation){
        traceln("before update: "+linkValues("KeyWordMap"));
        KeyWord dadkeyWord = (KeyWord) linked("KeyWordMap",dadNode);
        KeyWord sondkeyWord = (KeyWord) linked("KeyWordMap",sonNode);
        int rowNum = -1;
        int columnNum = -1;
        if (rowType.equals("Int")&&columnType.equals("Int")){
            rowNum = Integer.parseInt(row);
            columnNum = Integer.parseInt(column);
        }else  if (rowType.equals("Int")&&columnType.equals("")){
            rowNum = Integer.parseInt(row);
        }else if (rowType.equals("")&&columnType.equals("Int")){
            columnNum = Integer.parseInt(column);
        }else if (rowType.equals("Variable")){
            rowNum = (int)linked("count",row);
        }else if (columnType.equals("Variable")){
            columnNum = (int)linked("count",column);
        }

        List<Element> atomList = sondkeyWord.getAtomList();
        traceln("row and column : "+rowNum+ " "+columnNum);
        traceln("atomList : "+atomList);
        if (rowNum!=-1&&columnNum!=-1){
            if (operation.equals("add")){
                if (atomList.size()>rowNum){
                    if (atomList.get(rowNum).getList().size()>columnNum){
                        Atom atom = atomList.get(rowNum).getList().get(columnNum);
                        Element element = new Element();
                        element.getList().add(atom);
                        dadkeyWord.getAtomList().add(element);
                        link("KeyWordMap",dadNode,dadkeyWord);
                    }else {
                        traceln("the column < size! ");
                    }
                }else{
                    traceln("the row < size! ");
                }
            }else if (operation.equals("change")){
                {
                    if (atomList.size()>rowNum){
                        if (atomList.get(rowNum).getList().size()>columnNum){
                            Atom atom = atomList.get(rowNum).getList().get(columnNum);
                            Element element = new Element();
                            element.getList().add(atom);
                            dadkeyWord.getAtomList().clear();
                            dadkeyWord.getAtomList().add(element);
                            link("KeyWordMap",dadNode,dadkeyWord);
                        }else {
                            traceln("the column < size! ");
                        }
                    }else{
                        traceln("the row < size! ");
                    }
                }
            }

        }else if (rowNum!=-1&&columnNum==-1){
            if (operation.equals("add")){
                if (atomList.size()>rowNum){
                    Element element = atomList.get(rowNum);
                    dadkeyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,dadkeyWord);
                }else {
                    traceln("the row < size! ");
                }
            }else if (operation.equals("change")){
                if (atomList.size()>rowNum){
                    Element element = atomList.get(rowNum);
                    dadkeyWord.getAtomList().clear();
                    dadkeyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,dadkeyWord);
                }else {
                    traceln("the row < size! ");
                }
            }

        }else if (rowNum==-1&&columnNum!=-1){
            if (operation.equals("add")){
                if (atomList.get(0).getList().size()>columnNum){
                    Atom atom = atomList.get(0).getList().get(columnNum);
                    Element element = new Element();
                    element.getList().add(atom);

                    dadkeyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,dadkeyWord);
                }else {
                    traceln("the column < size! ");
                }
            }else if (operation.equals("change")){
                if (atomList.get(0).getList().size()>columnNum){
                    Atom atom = atomList.get(0).getList().get(columnNum);
                    Element element = new Element();
                    element.getList().add(atom);
                    dadkeyWord.getAtomList().clear();
                    dadkeyWord.getAtomList().add(element);
                    link("KeyWordMap",dadNode,dadkeyWord);
                }else {
                    traceln("the column < size! ");
                }
            }
        }

        traceln("after update: "+linkValues("KeyWordMap"));

    }


    public void dealwithKeyWord(String sonname, String fathername,String operation) {
        KeyWord dadkeyWord = (KeyWord)linked("KeyWordMap", fathername);
        KeyWord sondkeyWord = (KeyWord)linked("KeyWordMap", sonname);
        List<Element> atomList = sondkeyWord.getAtomList();

        if (operation.equals("change")){
            dadkeyWord.getAtomList().clear();
            for (Element element:atomList){
                dadkeyWord.getAtomList().add(element);
            }
        }else if (operation.equals("add")){
            for (Element element:atomList){
                dadkeyWord.getAtomList().add(element);
            }
        }else if (operation.equals("delete")){
            for (Element element:atomList){
                dadkeyWord.getAtomList().remove(element);
            }
        }

    }

    public void forAtom(String sonname, String fathername) {
        traceln("KeyWordMap: " + linkValues("KeyWordMap"));
        KeyWord dadkeyWord = (KeyWord)linked("KeyWordMap", fathername);
        KeyWord sondkeyWord = (KeyWord)linked("KeyWordMap", sonname);
        traceln("forAtom: " + readCounter(fathername) + " " +fathername);
        int rowNum = readCounter(fathername);
        int columnNum = -1;
        List<Element> atomList = sondkeyWord.getAtomList();
        if (rowNum != -1 && columnNum == -1 && atomList.size() > rowNum) {
            Element element = (Element)atomList.get(rowNum);
            traceln("dadkeyWord.getAtomList: " + dadkeyWord);
            traceln("ElementWord.getAtomList: " + element);
            dadkeyWord.getAtomList().clear();
            dadkeyWord.getAtomList().add(element);
        }

    }
}
