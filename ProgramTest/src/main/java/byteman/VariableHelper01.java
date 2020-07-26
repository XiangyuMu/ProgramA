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


    public void dealwithKeyWord(String dadNode,String row,String column,String rowType,String columnType){
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

    public void dealwithKeyWord(String sonNode,String dadNode,String row,String column,String rowType,String columnType){
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
        }
        List<Element> atomList = sondkeyWord.getAtomList();
        if (rowNum!=-1&&columnNum!=-1){
            if (atomList.size()>rowNum){
                if (atomList.get(rowNum).getList().size()>columnNum){
                    Atom atom = atomList.get(rowNum).getList().get(columnNum);
                    Element element = new Element();
                    element.getList().add(atom);
                    dadkeyWord.getAtomList().add(element);
                }else {
                    traceln("the column < size! ");
                }
            }else{
                traceln("the row < size! ");
            }
        }else if (rowNum!=-1&&columnNum==-1){
            if (atomList.size()>rowNum){
                Element element = atomList.get(rowNum);
                dadkeyWord.getAtomList().add(element);
            }else {
                traceln("the row < size! ");
            }
        }

        traceln("after update: "+linkValues("KeyWordMap"));

    }

    public void forAdd(){
        traceln("forFunc!!");
    }
}
