package learnJavaParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CreateList {
	private static final String FILE_PATH = "src/main/java/learnJavaParser/Example.java";
	public static void main(String[] args) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		VoidVisitor<?> methodNameVisitor = new ForPrinter();
		methodNameVisitor.visit(cu, null);
		//addInfo(cu);
	}
	
	private static class ForPrinter extends VoidVisitorAdapter<Void>{
		public void visit(AssignExpr fs,Void arg) {
			super.visit(fs, arg);	
//			fs.getBody().asBlockStmt().getStatement(0).asExpressionStmt().getExpression().asAssignExpr().getChildNodes().get(1).getMetaModel()
			System.out.println(fs.getValue());
			
			//System.out.println(fs.getBody().asBlockStmt().getStatement(0).asExpressionStmt().getExpression().asAssignExpr().getChildNodes().get(1));
		}
	}
	
	
	private static  void addInfo(CompilationUnit cu) {
		ClassOrInterfaceDeclaration co = cu.getClassByName("Example").get();
		MethodDeclaration md = co.getMethods().get(0);
		BlockStmt bs = md.getBody().get();
		List<Statement> ls = bs.getStatements();
		ForStmt fs;
		List<Parameter>  lp = md.getParameters();
		Expression par = null ;
		for(int k = 0;k<lp.size();k++) {      //找到第一个循环的形式为list的变量名称
			if(lp.get(k).getTypeAsString().indexOf("List")!=-1) {
				//par = lp.get(k).getNameAsString();
				par = lp.get(k).getNameAsExpression();
			}
		}
		for(int i = 0;i<ls.size();i++) {
			if(ls.get(i).getMetaModel().toString() == "ForStmt") {
				fs = ls.get(i).asForStmt();
				Expression num ;
				for(int j = 0;j<fs.getUpdate().size();j++) {
					if(fs.getUpdate().get(j).getMetaModel().toString() == "UnaryExpr") {
						num = fs.getUpdate().get(j).asUnaryExpr().getExpression();
					}	
				}
				
				NodeList<Expression> nle = new NodeList<Expression>();
				nle.add(new NameExpr("list"));
				
				NameExpr clazz = new NameExpr("System");
		        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
		        MethodCallExpr call = new MethodCallExpr(field, "println",nle);
		        
				System.out.println(fs.getBody().asBlockStmt().addStatement(call));  //添加到合适的位置
				System.out.println(fs);
				System.out.println(fs.getChildNodes());
				
			}
		}
		
		
	}

}
