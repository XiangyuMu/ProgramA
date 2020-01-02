package testList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import reduceExample.Element;
import reduceExample.ElemwntList;

//create dir of test cases for one program
public class createDir {
	private String path;
	private String filename;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void createFileDir(File file) {
		path = file.getAbsolutePath();
		String[] a= path.split("\\\\");
		int l = a.length;
		path = "";
		for(int i = 0;i<l-1;i++) {
			path = path +a[i]+ "\\" ;
		}
		filename = a[l-1];
		System.out.println(filename);
		String[] b = filename.split("\\.");
		filename = b[0];
		File filedir = new File(path+filename);
		filedir.mkdir();		
	}
	
	
	
	public void removeFile() {
		new File(path+filename+".txt").delete();
	}
	
	
	public void createRelatedFile(List<ElemwntList> list) throws IOException {
		
		for(int i = 0;i<list.size();i++) {
			FileWriter file = new FileWriter(path+filename+"\\"+i+".txt", true);
			BufferedWriter output = new BufferedWriter(file);
			List<Element> el = list.get(i).getList();
			String str = "";
			for(int k =0;k<el.size();k++) {
				
				List<Object> ol = el.get(k).getList();
				for(int j = 0;j<ol.size();j++) {
					str = str +ol.get(j).toString()+ "# ";
				}
				output.write(str);
				output.write("\n");
				str = "";
			}
			output.close();
			
		}
		removeFile();
	}
	
	public void createAllCasesInOneFile(List<ElemwntList> list, String filename) throws IOException{
		FileWriter file = new FileWriter(filename+"mix.txt", true);
		BufferedWriter output = new BufferedWriter(file);
		for(int i = 0;i<list.size();i++) {
			output.write("$"+i);
			output.write("\n");
			List<Element> el = list.get(i).getList();
			String str = "";
			for(int k =0;k<el.size();k++) {
				List<Object> ol = el.get(k).getList();
				for(int j = 0;j<ol.size();j++) {
					str = str +ol.get(j).toString()+ "# ";
				}
				output.write(str);
				output.write("\n");
				str = "";
			}
			
		}
		output.close();
		removeFile();
	}

}
