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

public class HalperTest extends Helper {
    protected HalperTest(Rule rule) {
        super(rule);
    }

    public void readKeyWordFromFile() throws IOException {
        createLinkMap("KeyWordMap");
        createLinkMap("ValueTable");
        List<KeyWord> lk = new ArrayList<>();
        lk = new DealWithInfoToFile().readKeyWordfile();
        for(int i = 0;i<lk.size();i++){
            link("KeyWordMap",lk.get(i).getName(),lk.get(i));
            link("ValueTable",lk.get(i).getName(),null);
        }
    }

    public boolean isChangeValue(String name,Object currentValue){
        boolean isChanged = false;
        Object formerValue = linked("ValueTable",name);
        if (formerValue.equals(currentValue)){
            isChanged = false;
        }else {
            isChanged = true;
            link("ValueTable",name,currentValue);
        }
        return isChanged;
    }

    public void addAtomToKeyWord(String mapNmae,String keyWordName,Atom atom){
        KeyWord keyWord = (KeyWord) linked(mapNmae,keyWordName);
        int formerLine = keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().get(0).getRow();
        if(formerLine==atom.getRow()){
            keyWord.getAtomList().get(keyWord.getAtomList().size()-1).getList().add(atom);
        }else{
            Element element = new Element();
            element.getList().add(atom);
            keyWord.getAtomList().add(element);
        }

        link(mapNmae,keyWordName,keyWord);
    }



    public void printInfo(String str){
        System.out.println(str);
    }
}