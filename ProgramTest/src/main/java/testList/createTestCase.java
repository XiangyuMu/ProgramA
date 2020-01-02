package testList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
//create the testcase file.
public class createTestCase {
	
	
	public void OneTestCase(String filepath,String str) {
		
		try {
			FileWriter file = new FileWriter(filepath, true);
			BufferedWriter output = new BufferedWriter(file);
			//PrintWriter output = new PrintWriter(file);
			System.out.println(str);
			output.write(str);
			output.write("\n");
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String InputTestCase(String filepath) {
		String str = "";
		Scanner scan = new Scanner(System.in);
		boolean flag_1 = true;
		boolean flag_2 = true;
		while(flag_1) {
			System.out.println("请输入下一个测试用例：");
			flag_2 = true;
			while(flag_2) {
				
				System.out.println("请输入测试用例中的一项：");
				String text = scan.next();
				if(text.equals("#1")) {
					flag_2 = false;
					break;
				}
				if(text.equals("#2")) {
					flag_1 = false;
					break;
				}
				str = str+text+"# ";
			}
		//	System.out.println(str);
			OneTestCase(filepath, str);
			str = "";
		}
		return str;
	}

	public static void main(String[] args) {
		createTestCase ctc = new createTestCase();
		System.out.println("请输入测试用例文件名称：");
		Scanner scan = new Scanner(System.in);
		String filename;
		filename = scan.next();
		ctc.InputTestCase(filename+".txt");
	}
}
