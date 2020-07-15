package preprocessing;

public class ForInfo {

    int begin;
    int end;
    String number;
    public int getBegin() {
        return begin;
    }
    public void setBegin(int begin) {
        this.begin = begin;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public String toString() {
        return "ForInfo [begin=" + begin + ", end=" + end + ", number=" + number + "]\n";
    }
    public ForInfo(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }




}
