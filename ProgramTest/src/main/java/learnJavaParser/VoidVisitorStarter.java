package learnJavaParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VoidVisitorStarter {
	private static final String FILE_PATH = "src/main/java/learnJavaParser/ReversePolishNotation.java";
	
	public static void main(String[] args) throws Exception {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		
//		VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
//		methodNameVisitor.visit(cu, null);
		
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
		methodNameCollector.visit(cu, methodNames);
		methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
	}
	
	private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
		@Override
		public void visit(MethodDeclaration md, Void arg) {
			// TODO Auto-generated method stub
			super.visit(md, arg);
			System.out.println("Method Name Printed: " + md.getName());
		}
	}
	
	private static class MethodNameCollector extends VoidVisitorAdapter<List<String>>{
		@Override
		public void visit(MethodDeclaration md, List<String> collector) {
			// TODO Auto-generated method stub
			super.visit(md, collector);
			collector.add(md.getNameAsString());
		}
	}

}
