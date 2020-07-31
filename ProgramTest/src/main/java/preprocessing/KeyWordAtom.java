package preprocessing;

import java.util.ArrayList;
import java.util.List;

public class  KeyWordAtom {
    @Override
    public String toString() {
        return "KeyWordAtom{" +
                "keyWordName='" + keyWordName + '\'' +
                ", column='" + column + '\'' +
                ", row='" + row + '\'' +
                ", line=" + line +
                ", rowType='" + rowType + '\'' +
                ", columnType='" + columnType + '\'' +
                ", operation='" + operation + '\'' +
                ", sonAtomList=" + sonAtomList +
                ", type='" + type + '\'' +
                ", object=" + object +
                '}';
    }

    String keyWordName;
    String column = "";
    String row = "";
    int line = -1;
    String rowType;
    String columnType;

    String operation = "";
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
        this.sonAtomList = keyWordAtom.sonAtomList;
        this.type = keyWordAtom.type;
    }


    public void addKeyWordAtom(List<KeyWordAtom> keyWordAtomList){
        if (!keyWordAtomList.isEmpty()){
            for (KeyWordAtom k : keyWordAtomList){
                this.sonAtomList.add(k);
            }
        }

    }


    public void changeKeyWordAtom(List<KeyWordAtom> keyWordAtomList){
        this.sonAtomList.clear();
        for (KeyWordAtom k : keyWordAtomList){
            this.sonAtomList.add(k);
        };
    }

    public void deleteKeyWordAtom(List<KeyWordAtom> keyWordAtom){
        boolean flag = true;
        for(KeyWordAtom k : sonAtomList){
            for (KeyWordAtom j : keyWordAtom){
                if (k.equals(j)){
                    sonAtomList.remove(k);
                    flag = true;
                }
            }
        }
        if (!flag){
            System.out.println("delete nothing from sonAtomList!");
        }
    }
}
