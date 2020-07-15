package preprocessing;

import java.util.ArrayList;
import java.util.List;

public class  KeyWordAtom {
    String keyWordName;
    String column = "";
    String row = "";
    int line = -1;
    String rowType;
    String columnType;

    List<KeyWordAtom> sonAtomList = new ArrayList<>();
    String type;
    Object object = null;

    public List<KeyWordAtom> getSonAtomList() {
        return sonAtomList;
    }

    public void setSonAtomList(List<KeyWordAtom> sonAtomList) {
        this.sonAtomList = sonAtomList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KeyWordAtom{" +
                "keyWordName='" + keyWordName + '\'' +
                ", column='" + column + '\'' +
                ", row='" + row + '\'' +
                ", line=" + line +
                ", rowType='" + rowType + '\'' +
                ", columnType='" + columnType + '\'' +
                ", sonAtomList=" + sonAtomList +
                ", type='" + type + '\'' +
                ", object=" + object +
                '}';
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumn() {
        return column;
    }

    public String getRow() {
        return row;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getKeyWordName() {
        return keyWordName;
    }

    public void setKeyWordName(String keyWordName) {
        this.keyWordName = keyWordName;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void copyValue(KeyWordAtom keyWordAtom){
        this.object = keyWordAtom.object;
        this.keyWordName = keyWordAtom.keyWordName;
        this.column = keyWordAtom.column;
        this.row = keyWordAtom.row;
        this.columnType = keyWordAtom.columnType;
        this.rowType = keyWordAtom.rowType;
        if (keyWordAtom.line!=-1){
            this.line = keyWordAtom.line;
        }
    }


    public void addKeyWordAtom(KeyWordAtom keyWordAtom){
        this.sonAtomList.add(keyWordAtom);
    }


    public void changeKeyWordAtom(KeyWordAtom keyWordAtom){
        this.sonAtomList.clear();
        this.sonAtomList.add(keyWordAtom);
    }

    public void deleteKeyWordAtom(KeyWordAtom keyWordAtom){
        boolean flag = true;
        for(KeyWordAtom k : sonAtomList){
            if (k.equals(keyWordAtom)){
                sonAtomList.remove(k);
                flag = false;
                break;
            }
        }
        if (!flag){
            System.out.println("delete nothing from sonAtomList!");
        }
    }
}
