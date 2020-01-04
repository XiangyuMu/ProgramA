package AnalysisProgress;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class progress1 {

	private static String filepath = "";
	
	public static void main(String[] args) throws Exception {
		CompilationUnit cu = StaticJavaParser.parse(new File(filepath));
		
	}
	
	
}
