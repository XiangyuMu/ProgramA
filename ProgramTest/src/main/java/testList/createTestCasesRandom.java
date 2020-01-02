package testList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//create Test cases random
public class createTestCasesRandom {
	private int count = 0;
	private String firstItem = "";
	private int min;
	private int max;
	private List<String> secondItemList = new ArrayList<String>();
	
	
	
	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getFirstItem() {
		return firstItem;
	}



	public void setFirstItem(String firstItem) {
		this.firstItem = firstItem;
	}



	public int getMin() {
		return min;
	}



	public void setMin(int min) {
		this.min = min;
	}



	public int getMax() {
		return max;
	}



	public void setMax(int max) {
		this.max = max;
	}



	public List<String> getSecondItemList() {
		return secondItemList;
	}



	public void setSecondItemList(List<String> secondItemList) {
		this.secondItemList = secondItemList;
	}



	public void createTestCase(String filename) {
		FileWriter file;
		try {
			file = new FileWriter(filename, true);
			BufferedWriter output = new BufferedWriter(file);
		//PrintWriter output = new PrintWriter(file);
			for(int i = 0;i<count;i++) {
				double num = Math.random();
				int finalnum = (int) (min+num*(max-min));
				secondItemList.add(Integer.toString(finalnum));
			}
			for(int k = 0;k<count;k++) {
				String str = firstItem+"# "+secondItemList.get(k)+"# ";
				System.out.println(str);
				output.write(str);
				output.write("\n");
		
			}
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		createTestCasesRandom ctcr = new createTestCasesRandom();
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入所需生成测试用例的个数：");
		int count = scan.nextInt();
		ctcr.setCount(count);
		System.out.println("请输入所需生成测试用例的范围：");
		System.out.println("最小值：");
		int min = scan.nextInt();
		ctcr.setMin(min);
		System.out.println("最大值：");
		int max = scan.nextInt();
		ctcr.setMax(max);
		System.out.println("请输入测试用例的每项中的第一个：");
		String firstItem = scan.next();
		ctcr.setFirstItem(firstItem);
		System.out.println("请输入所需生成测试用例文件名称");
		String filename = scan.next();
		ctcr.createTestCase(filename+".txt");
		
//		for(int i = 5;i<40;i++) {
//			createTestCasesRandom ctcr = new createTestCasesRandom();
//			ctcr.setCount(5);
//			ctcr.setMin(0);
//			ctcr.setMax(5);
//			ctcr.setFirstItem("1");
//			ctcr.createTestCase("case"+i+".txt");
//			System.out.println("case"+i+".txt"+" 生成成功");
//		}
	}

}
