package learnJavaParser;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VoidVisitorComplete {
	
	private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
		@Override
		public void visit(MethodDeclaration md, Void arg) {
			// TODO Auto-generated method stub
			super.visit(md, arg);
			System.out.println("Method Name Printed: " + md.getName());
		}
	}

}
