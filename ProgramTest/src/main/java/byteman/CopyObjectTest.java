package byteman;

public class CopyObjectTest implements Cloneable{
    Object o = new Object();
    public CopyObjectTest(Object o){
        this.o = o;
    }

    @Override
    public String toString() {
        return "CoptObjectTest{" +
                "o=" + o +
                '}';
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        CopyObjectTest copyObjectTest = (CopyObjectTest) super.clone();
        copyObjectTest.setO(this.o);
        return copyObjectTest;
    }
}
