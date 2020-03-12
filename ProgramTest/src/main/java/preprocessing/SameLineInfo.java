package preprocessing;

import java.util.ArrayList;
import java.util.List;

/**
 *the writeKeyWord and readKeyWord of a line
 */
public class SameLineInfo {
    List<KeyWord> readlist = new ArrayList<>();
    List<KeyWord> writelist = new ArrayList<>();

    public List<KeyWord> getReadlist() {
        return readlist;
    }

    public void setReadlist(List<KeyWord> readlist) {
        this.readlist = readlist;
    }

    public List<KeyWord> getWritelist() {
        return writelist;
    }

    public void setWritelist(List<KeyWord> writelist) {
        this.writelist = writelist;
    }


    @Override
    public String toString() {
        return "SameLineInfo{" +
                "readlist=" + readlist +
                ", writelist=" + writelist +
                '}';
    }
}
