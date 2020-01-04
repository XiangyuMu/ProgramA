package method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import AnalysisProgress.functionInfo;

public class RelatedToPosition {
	
	
	public static void main(String[] args) throws Exception {              //主函数
		String FILE_PATH = "src/main/java/searchOnInternet/";
		String Filename = "";
		System.out.println("请输入文件名称：");
		Scanner scan = new Scanner(System.in);
		Filename = scan.next();
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH+Filename+".java"));
		
		List<String> ListValue = new ArrayList<String>();
		VoidVisitor<List<String>> md = new InputList();
		ListValue.add("list");
		md.visit(cu, ListValue);
		VoidVisitor<List<String>> md_1 = new InputList_1();
		md_1.visit(cu, ListValue);
		VoidVisitor<List<String>> md_2 = new InputList_2();
		md_2.visit(cu, ListValue);
		VoidVisitor<List<String>> md_3 = new InputList_3();
		md_3.visit(cu, ListValue);
		boolean flag = false;
		int num_1 ,num_2;
		while(!flag) {
			num_1 = ListValue.size();
			md.visit(cu, ListValue);
			md_1.visit(cu, ListValue);
			md_2.visit(cu, ListValue);
			md_3.visit(cu, ListValue);
			num_2 = ListValue.size();
			if(num_1==num_2) {
				flag=true;
			}
		}
		
		System.out.println(ListValue);                      //生成存储与测试用例相关的变量的list
		
		ModifierVisitor<List<String>> checkProgramVisitor = new checkProgram();
		checkProgramVisitor.visit(cu, ListValue);
		System.out.println(cu);
	}
	
	
	private static class InputList extends VoidVisitorAdapter<List<String>>{       //以等号判断list相关
		@Override
		public void visit(AssignExpr ae,List<String> collector) {       
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(ae, collector);
			list_key.add(ae.getValue().toString());
			list_value.add(ae.getTarget().toString());
			for(int i=0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i))) {
						collector.add(list_key.get(i).trim());
					}
				}
			}
		}
	}
	
	private static class InputList_1 extends VoidVisitorAdapter<List<String>>{        //也是以等号判断list相关，但判断的是变量声明时的
		@Override
		public void visit(VariableDeclarator vd,List<String> collector) {
			List<String> list_1 = new ArrayList<String>();
			super.visit(vd, list_1);
//			System.out.println("ae:"+vd.toString());
			list_1.add(vd.toString());
			System.out.println("Init: "+vd.getNameAsString());
			List<Optional<Expression>> loe = new ArrayList<Optional<Expression>>();
			loe.add(vd.getInitializer());
			List<String> namelist = new ArrayList<String>();
			namelist.add(vd.getNameAsString());
			
			for(int m = 0;m<loe.size();m++) {
				if(!loe.get(m).isPresent()) {
					loe.remove(m);
					namelist.remove(m);
				}
			}
//			System.out.println("loe: "+loe);
//			System.out.println("name: "+namelist);
			for(int i = 0;i<loe.size();i++) {
				for(int j = 0;j<collector.size();j++) {
					if(loe.get(i).get().toString().contains(collector.get(j))&&!collector.contains(namelist.get(i))) {
						collector.add(namelist.get(i));
					}
				}
			}
			//String init = vd.getInitializer().get().toString();
//			for(int i = 0;i<list_1.size();i++) {
//				if(list_1.get(i).indexOf("=")!=-1) {
//					String[] list = list_1.get(i).split("=");
//					if(list.length==2) {
//						for(int j = 0;j<collector.size();j++) {
//							if(list[1].contains(collector.get(j))&&!collector.contains(list[0])) {
//								collector.add(list[0]);
//							}
//						}
//					}
//				}
//			}
		}
	}
	
	private static class InputList_2 extends VoidVisitorAdapter<List<String>>{      //在foreach中判断list相关
		@Override
		public void visit(ForEachStmt fes, List<String> collector) {
			// TODO Auto-generated method stub
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(fes, collector);
			list_key.add(fes.getVariable().getVariables().get(0).toString());
			list_value.add(fes.getIterable().toString());
			for(int i = 0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i))) {
						collector.add(list_key.get(i).trim());
					}
				}
			}
			
//			System.out.println("fes.getV"+fes.getIterable());
		}
	}
	
	private static class InputList_3 extends VoidVisitorAdapter<List<String>>{      //在函数调用中完善list
		@Override
		public void visit(MethodCallExpr mce, List<String> collector) {
			// TODO Auto-generated method stub
			List<String> list_key = new ArrayList<String>();
			List<String> list_value = new ArrayList<String>();
			super.visit(mce,collector);
//			System.out.println("methodname:"+mce+" methodarg:"+mce.getArguments());   //此处有问题，需要改！
			list_key.add(mce.toString());
			list_value.add(mce.getArguments().toString());
			for(int i = 0;i<list_key.size();i++) {
				for(int j=0;j<collector.size();j++) {
					if(list_value.get(i).contains(collector.get(j))&&!collector.contains(list_key.get(i).split("\\.")[0])) {
						int num = list_key.get(i).indexOf(list_value.get(i));
//						System.out.println("list_key: "+list_key.get(i));
						String[] key = list_key.get(i).split("\\.");
//						System.out.println("key: "+key.length);
						collector.add(key[0].trim());
					}
				}
			}
			
		}
	}
	
	private static class checkProgram extends ModifierVisitor<List<String>>{
		@Override
		public ClassOrInterfaceDeclaration visit(ClassOrInterfaceDeclaration co, List<String> collector) {
			// TODO Auto-generated method stub
			super.visit(co, collector);
			System.out.println("collector:"+collector);
			List<functionInfo> lf = new ArrayList<functionInfo>();
			List<List <functionInfo>> llf = new ArrayList<List<functionInfo>>();
			checkType(co,collector,llf);
			writeInProgram(co,llf);
			return co;
		}
	}
	
	private static ClassOrInterfaceDeclaration checkType(ClassOrInterfaceDeclaration co,List<String> collector,List<List <functionInfo>> llf ) {
		MethodDeclaration reduceMd  = new MethodDeclaration();
		reduceMd = co.getMethodsByName("reduce").get(0);
		BlockStmt bs = reduceMd.getBody().get();
		List<Statement> ls = bs.getStatements();
		List<ForStmt> fl = new ArrayList<ForStmt>();
		List<ForEachStmt> fel = new ArrayList<ForEachStmt>();
		List<WhileStmt> wl = new ArrayList<WhileStmt>();
		List<Parameter>  lp = reduceMd.getParameters();
		Expression par ;
		for(int k = 0;k<lp.size();k++) {      //找到第一个循环的形式为list的变量名称
			if(lp.get(k).getTypeAsString().indexOf("ElemwntList")!=-1) {
				//par = lp.get(k).getNameAsString();
				par = lp.get(k).getNameAsExpression();
//				System.out.println(par);
			}else {
				System.out.println("错误！reduce函数没有输入！");
			}
		}
		
		//System.out.println(ls);
		
		for(int i = 0;i<ls.size();i++) {           //找到reduce函数中的while、for和foreach循环，放入wl、fl和fel中。
			if(ls.get(i).isForStmt()) {
				fl.add(ls.get(i).asForStmt());
			}else if(ls.get(i).isForEachStmt()) {
				fel.add(ls.get(i).asForEachStmt());
			}else if(ls.get(i).isWhileStmt()) {
				wl.add(ls.get(i).asWhileStmt());
			}
		}
		List<functionInfo> lf;
		if(fl.size()!=0) {
			for(int flNum = 0;flNum<fl.size();flNum++) {
				List<Statement> lstmt;
				System.out.println("for");
				lstmt = fl.get(flNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
				llf.add(lf);
				
			}
		}
		if(fel.size()!=0) {
			for(int felNum = 0;felNum<fel.size();felNum++) {
				List<Statement> lstmt;
				System.out.println("foreach");
				//System.out.println(fel.get(felNum).getBody().asBlockStmt().getStatements());
				lstmt = fel.get(felNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
				llf.add(lf);
			}
		}
		if(wl.size()!=0) {
			for(int wlNum = 0;wlNum<wl.size();wlNum++) {
				List<Statement> lstmt;
				System.out.println("while");
				lstmt = wl.get(wlNum).getBody().asBlockStmt().getStatements();
				lf = extractInfo(lstmt,collector);
//				if(lf.size()!=0) {
//					llf.add(lf);
//				}
			
				llf.add(lf);
			}
		}
//		System.out.println(reduceMd);
		System.out.println("llf: "+llf);
		return co;
	}
	
	
	private static List<functionInfo> extractInfo(List<Statement> ls,List<String> collector) {
		List<functionInfo> lf = new ArrayList<functionInfo>();
		IfStmt is;
		int num = 0;
		for(int i = 0;i<ls.size();i++) {
			if(ls.get(i).isIfStmt()) {
				is = ls.get(i).asIfStmt();
				Expression ifcondition ;
				ifcondition = is.getCondition();
				boolean isrelatedtoValue = false;
				List<String> tempoaryList = new ArrayList<String>();
				System.out.println("collector: "+collector);
				System.out.println("ifcondition"+ifcondition);
				for(int k = 0;k<collector.size();k++) {    //确定判断条件中是否包含测试用例相关值,即确定是否与值有关
					if(ifcondition.toString().contains(collector.get(k))) {
						tempoaryList.add(collector.get(k));
						//System.out.println("运行到此！");
						//System.out.println("ifcondition: "+ifcondition);
						isrelatedtoValue = true;
						//System.out.println(fi);
					}
				}
				System.out.println("tempoaryList："+tempoaryList);
				String maxStr = "";
				if(tempoaryList.size()>1) {
					int max = 0;
					for(int m = 0;m<tempoaryList.size();m++) {
						if(max<tempoaryList.get(m).length()) {
							max = tempoaryList.get(m).length();
							maxStr = tempoaryList.get(m);
						}
					}
					System.out.println("value:"+maxStr);
				}else if(tempoaryList.size()==1){
					maxStr = tempoaryList.get(0);
				}
				if(isrelatedtoValue) {
					functionInfo fi = new functionInfo();
					fi.setIfCondition(ifcondition.toString());
					fi.setIsRelationToValue(true);
					fi.getValueList().add(maxStr);
					lf.add(fi);
				}
				if(!isrelatedtoValue) {
					System.out.println("与值无关");
					functionInfo fi = new functionInfo();
					fi.setIfCondition(ifcondition.toString());
					fi.setIsRelationToPosition(true);
					fi.getPositionList().add("position_num_"+num);
					num = num + 1;
					lf.add(fi);
				}
				System.out.println("第一个lf:"+lf.toString());
//				
//				if(ifcondition.toString().indexOf("list")!=-1) {
//					functionInfo fi = new functionInfo();
//					fi.setIsRelationToValue(true);
//					fi.getValueList().add("list");
//				}
			}
			System.out.println("第二个lf:"+lf.toString());
		}
		System.out.println("第三个lf:"+lf.toString());
		return lf;
	}
	
	private static ClassOrInterfaceDeclaration writeInProgram(ClassOrInterfaceDeclaration co,List<List <functionInfo>> llf) {
		MethodDeclaration reduceMd  = new MethodDeclaration();
		reduceMd = co.getMethodsByName("reduce").get(0);
		BlockStmt bs = reduceMd.getBody().get();
		List<Statement> ls = bs.getStatements();
		
		
//		System.out.println("类型是: "+ls.get(0));
//		ExpressionStmt es = new ExpressionStmt();
//		VariableDeclarationExpr vde = new VariableDeclarationExpr();
//		VariableDeclarator vd = new VariableDeclarator();
//		vd.setName("qwe");
//		vd.setType("int");
//		vd.setInitializer("0");
//		NodeList<VariableDeclarator> nvd = new NodeList<>();
//		nvd.add(vd);
//		vde.setVariables(nvd);
//		es.setExpression(vde);
//		bs.addStatement( es);
//		System.out.println("结果是："+bs);
		
		List<ForStmt> fl = new ArrayList<ForStmt>();
		List<ForEachStmt> fel = new ArrayList<ForEachStmt>();
		List<WhileStmt> wl = new ArrayList<WhileStmt>();
		List<Parameter>  lp = reduceMd.getParameters();
		int i = 0;
		int k = 0;
		while(i<llf.size()) {
			Statement s = null;
			while(k<ls.size()) {				
				if(ls.get(k).isForEachStmt()) {                   //取一个for循环
					s = ls.get(k).asForEachStmt();
					break;
				}else if(ls.get(k).isForStmt()) {
					s = ls.get(k).asForStmt();
					break;
				}else if(ls.get(k).isWhileStmt()) {
					s = ls.get(k).asWhileStmt();
					break;
				}else {
					k++;
				}
			}
			
			List<functionInfo> lf = llf.get(i);
			if(lf.size()!=0){		//取一个functonInfo，如为空则直接跳过。
				for(int j = 0;j<lf.size();j++) {
					if(lf.get(j).getIsRelationToPosition()) {
						addInfoRelatedToPosition(lf.get(j),s,bs);
					}else if(lf.get(j).getIsRelationToValue()){
						addInfoRelatedToValue(lf.get(j),s,bs);
					
					}
				}
			}
			i++;
			k++;
			
		}
		
		
		
		
//		for(int i = 0;i<llf.size();i++) {
//			List<functionInfo> lf = llf.get(i);
//			for(int k = 0;k<lf.size();k++) {
//				if(lf.get(k).getIsRelationToPosition()==true) {
//					
//				}else {
//					
//				}
//			}
//		}
		
		
		return co;
		
	}
	
	
	private static void addInfoRelatedToPosition(functionInfo f,Statement st,BlockStmt bs) {
		if(st.isForStmt()) {

			ExpressionStmt es = new ExpressionStmt();              //在for外定义一个新变量记录位置信息
			VariableDeclarationExpr vde = new VariableDeclarationExpr();
			VariableDeclarator vd = new VariableDeclarator();
			vd.setName(f.getPositionList().get(0));
			vd.setType("int");
			vd.setInitializer("0");
			NodeList<VariableDeclarator> nvd = new NodeList<>();
			nvd.add(vd);
			vde.setVariables(nvd);
			es.setExpression(vde);
			bs.addStatement( 0,es);
			
			UnaryExpr ue = new UnaryExpr();                      //循环一次位置把变量加一
			ue.setExpression(f.getPositionList().get(0));
			ExpressionStmt es_1 = new ExpressionStmt();
			es_1.setExpression(ue);
			st.asForStmt().getBody().asBlockStmt().addStatement(0,es_1);
			
			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getPositionList().get(0)+": \"+"+f.getPositionList().get(0));
	        
	        List<Statement> st_1 = st.asForStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
		
			
		}else if(st.isForEachStmt()) {
			ExpressionStmt es = new ExpressionStmt();              //在for外定义一个新变量记录位置信息
			VariableDeclarationExpr vde = new VariableDeclarationExpr();
			VariableDeclarator vd = new VariableDeclarator();
			vd.setName(f.getPositionList().get(0));
			vd.setType("int");
			vd.setInitializer("0");
			NodeList<VariableDeclarator> nvd = new NodeList<>();
			nvd.add(vd);
			vde.setVariables(nvd);
			es.setExpression(vde);
			bs.addStatement( 0,es);
			
			UnaryExpr ue = new UnaryExpr();                      //循环一次位置把变量加一
			ue.setExpression(f.getPositionList().get(0));
			ExpressionStmt es_1 = new ExpressionStmt();
			es_1.setExpression(ue);
			st.asForEachStmt().getBody().asBlockStmt().addStatement(0,es_1);
			
			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getPositionList().get(0)+": \"+"+f.getPositionList().get(0));
	        
	        List<Statement> st_1 = st.asForEachStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
		}else if(st.isWhileStmt()) {

			ExpressionStmt es = new ExpressionStmt();              //在for外定义一个新变量记录位置信息
			VariableDeclarationExpr vde = new VariableDeclarationExpr();
			VariableDeclarator vd = new VariableDeclarator();
			vd.setName(f.getPositionList().get(0));
			vd.setType("int");
			vd.setInitializer("0");
			NodeList<VariableDeclarator> nvd = new NodeList<>();
			nvd.add(vd);
			vde.setVariables(nvd);
			es.setExpression(vde);
			bs.addStatement( 0,es);
			
			UnaryExpr ue = new UnaryExpr();                      //循环一次位置把变量加一
			ue.setExpression(f.getPositionList().get(0));
			ExpressionStmt es_1 = new ExpressionStmt();
			es_1.setExpression(ue);
			st.asWhileStmt().getBody().asBlockStmt().addStatement(0,es_1);
			
			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getPositionList().get(0)+": \"+"+f.getPositionList().get(0));
	        
	        List<Statement> st_1 = st.asWhileStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
		
			
		}
	}
	private static void addInfoRelatedToValue(functionInfo f,Statement st,BlockStmt bs) {
		if(st.isForStmt()) {
			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getValueList().get(0)+": \"+"+f.getValueList().get(0));
	        
	        List<Statement> st_1 = st.asForStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
			
		}else if(st.isForEachStmt()) {

			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getValueList().get(0)+": \"+"+f.getValueList().get(0));
	        
	        List<Statement> st_1 = st.asForEachStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
			
		
			
		}else if(st.isWhileStmt()) {

			NameExpr clazz = new NameExpr("System");             //在if中输出变量的值
	        FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	        MethodCallExpr call = new MethodCallExpr(field, "println");
	        call.addArgument("\""+f.getValueList().get(0)+": \"+"+f.getValueList().get(0));
	        
	        List<Statement> st_1 = st.asWhileStmt().getBody().asBlockStmt().getStatements();
	        
	        for(int i = 0;i<st_1.size();i++) {
	        	if(st_1.get(i).isIfStmt()) {
	        		
	        		IfStmt is = st_1.get(i).asIfStmt();
	        		if(is.getCondition().toString().equals(f.getIfCondition())) {
	        			is.getThenStmt().asBlockStmt().addStatement(call);
	        			break;
	        		}
	        		
	        	}
	        }
			
		
			
		}
	}
}
