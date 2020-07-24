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

    }

    public void dealwithKeyWord(String sonNode,String dadNode,String row,String column,String rowType,String columnType){
        KeyWord dadkeyWord = (KeyWord) linked("KeyWordMap",dadNode);
        KeyWord sondkeyWord = (KeyWord) linked("KeyWordMap",sonNode);
        int rowNum = -1;
        int columnNum = -1;
        if (rowType.equals("Int")&rowType.equals("Int")){
            rowNum = Integer.parseInt(row);
            columnNum = Integer.parseInt(column);
        }else  if (rowType.equals("")){
            rowNum = Integer.parseInt(row);
        }
        List<Element> atomList = sondkeyWord.getAtomList();
        if (rowNum!=-1&&columnNum!=-1){
            for (Element element:atomList){
                for (Atom atom : element.getList()){
                    if (atom.getRow()==rowNum&&atom.getColumn()==columnNum){
                        Element e = new Element();
                        e.getList().add(atom);
                        dadkeyWord.getAtomList().add(e);
                        break;
                    }
                }
            }
        }else if (rowNum!=-1&&columnNum==-1){

        }


    }
}
