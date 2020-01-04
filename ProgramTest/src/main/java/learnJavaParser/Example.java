package learnJavaParser;

public class Example {
	public int sum(int num) {
		int count = 0;
		for(int i = 1;i<num;i++) {
			count = count + i;
			i = i+1;
			System.out.println("count");
		}
		return count;
	}

}
