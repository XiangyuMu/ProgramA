package reduceExample;

public class Atom {
    private Object atom;
    private int row = -1;
    private int column = -1;

    public Object getAtom() {
        return atom;
    }

    public void setAtom(Object atom) {
        this.atom = atom;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Atom() {
    }

    public Atom(Object atom) {
        this.atom = atom;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "atom=" + atom +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
