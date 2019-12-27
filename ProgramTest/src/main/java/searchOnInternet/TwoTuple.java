package searchOnInternet;

public class TwoTuple {
	public final String tuple1;
	public final String tuple2;
	public TwoTuple(String tuple1, String tuple2) {
		this.tuple1 = tuple1;
		this.tuple2 = tuple2;
	}
	
	public String toString() {
		return "("+tuple1+","+tuple2+")";
	}

}
