package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

public class DBApp {
	ArrayList<Table> tables = new ArrayList<Table>();
	public DBApp() throws IOException {
		String dir = "C:\\Users\\eiade\\Desktop\\metadata.csv";
		File file = new File(dir);
		if(file.createNewFile()) {
			BufferedWriter bf = new BufferedWriter(new FileWriter( dir));
			bf.write("Table Name, Column Name, Column Type, ClusteringKey, Indexed");
		}
	}
	public void createTable(String tableName, String key, Hashtable ht) throws IOException {
		tables.add(new Table(tableName, key, ht));
	}
	
	public void insertIntoTable(String tableName, Hashtable<String, Comparable> ht) throws IOException, ClassNotFoundException {
		String dir  = "C:\\Users\\eiade\\Desktop\\" + tableName+".txt";
		//deserialize 
		FileInputStream file = new FileInputStream(dir); 
        ObjectInputStream in = new ObjectInputStream(file); 
        
        // Method for deserialization of object 
        Table t = (Table)in.readObject();
		t.insertIntoTable(tableName, ht);
		//serialize
		FileOutputStream fileO = new FileOutputStream(dir);
        ObjectOutputStream out = new ObjectOutputStream(fileO);
        out.writeObject(t); 
        
        out.close(); 
        fileO.close();
	}
	
	public void deleteFromTable(String strTableName, Hashtable<String,Comparable> ht) throws ClassNotFoundException, IOException {
		String dir  = "C:\\Users\\eiade\\Desktop\\" + strTableName+".txt";
		//deserialize 
		FileInputStream file = new FileInputStream(dir); 
        ObjectInputStream in = new ObjectInputStream(file); 
        

        
        // Method for deserialization of object 
        Table t = (Table)in.readObject();
        
		t.deleteFromTable(strTableName, ht);
		ArrayList<String> toRem = new ArrayList<String>();
		for(String pageDir: t.pages) {
			FileInputStream fileP = new FileInputStream(pageDir);
			ObjectInputStream inP = new ObjectInputStream(fileP);
			
			
			
			Page p = (Page) inP.readObject();
			
			inP.close();
			fileP.close();
			System.out.println("page size ="+p.size());
			if(p.size()==0) {
				System.out.println("condition");
				Path path  = Paths.get(pageDir); 
				Files.delete(path);
            	toRem.add(pageDir);
			}
		}
		t.pages.removeAll(toRem);
		in.close();
        file.close();
		
		//serialize
		FileOutputStream fileO = new FileOutputStream(dir);
        ObjectOutputStream out = new ObjectOutputStream(fileO);
        out.writeObject(t); 
        
        out.close(); 
        fileO.close();
        
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// eiad is not here t3alo n7ot both marra w7da
		DBApp db = new DBApp();
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("id", "java.lang.Integer");
		ht.put("name", "java.lang.String");
		ht.put("gpa", "java.lang.double");
		
		Hashtable<String, Comparable> ht1 = new Hashtable<String, Comparable>();
		ht1.put("id", new Integer(1));
		ht1.put("name", "Eiad");
		ht1.put("gpa", new Double(0.7));
		
		
		Hashtable<String, Comparable> ht2 = new Hashtable<String, Comparable>();
		ht2.put("id", new Integer(3));
		ht2.put("name", "Mohab");
		ht2.put("gpa", new Double(0.71));
		
		
		Hashtable<String, Comparable> ht3 = new Hashtable<String, Comparable>();
		ht3.put("id", new Integer(2));
		ht3.put("name", "Mai");
		ht3.put("gpa", new Double(0.705));
		
		db.createTable("People", "id", ht);
//		db.insertIntoTable("People", ht1);
//		db.insertIntoTable("People", ht2);
//		db.insertIntoTable("People", ht3);
		
		
		//Getting page
		// Reading the object from a file 
//        FileInputStream file = new FileInputStream("C:\\Users\\eiade\\Desktop\\People755991834.txt"); 
//        ObjectInputStream in = new ObjectInputStream(file); 
          
        // Method for deserialization of object 
        
        
//        Page p = (Page)in.readObject(); 
//          
//        in.close(); 
//        file.close(); 
//          
//        System.out.println("Object has been deserialized "); 
//        System.out.println("N = " + p.N); 
//        System.out.println("size of a page = " + p.size());
//        for(int i = 0;i<p.size();i++) {
//        	System.out.println(((Tuple)p.get(i)).theTuple.get("name"));
//        }
		// done
		Hashtable<String, Comparable> ht4 = new Hashtable<String, Comparable>();
		ht4.put("name", new String("Mohab"));
		//db.deleteFromTable("People", ht4);
//		
//		System.out.println("Num pages after ="+db.tables.get(0).pages.size());
		
	}
}
