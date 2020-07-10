package preprocessing;

public class KeyWordAtom {
    String keyWordName;
    String column = "";
    String row = "";
    int line = -1;
    String rowType;
    String columnType;

    Object object = null;

    @Override
    public String toString() {
        return "KeyWordAtom{" +
                "keyWordName='" + keyWordName + '\'' +
                ", column='" + column + '\'' +
                ", row='" + row + '\'' +
                ", line=" + line +
                ", rowType='" + rowType + '\'' +
                ", columnType='" + columnType + '\'' +
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

}
