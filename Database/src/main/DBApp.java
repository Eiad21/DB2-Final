package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class DBApp {
	ArrayList<Table> tables = new ArrayList<Table>();
	public DBApp() {
		
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
		//db.createTable("People", "id", ht);
		
		Hashtable<String, Comparable> ht1 = new Hashtable<String, Comparable>();
		ht1.put("id", new Integer(1));
		ht1.put("name", "Eiad");
		ht1.put("gpa", new Double(0.7));
		//db.insertIntoTable("People", ht1);
		
		Hashtable<String, Comparable> ht2 = new Hashtable<String, Comparable>();
		ht2.put("id", new Integer(3));
		ht2.put("name", "Mohab");
		ht2.put("gpa", new Double(0.71));
		//db.insertIntoTable("People", ht2);
		
		Hashtable<String, Comparable> ht3 = new Hashtable<String, Comparable>();
		ht3.put("id", new Integer(2));
		ht3.put("name", "Mai");
		ht3.put("gpa", new Double(0.705));
		db.insertIntoTable("People", ht3);
		
		
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
//		Hashtable<String, Comparable> ht4 = new Hashtable<String, Comparable>();
//		ht4.put("name", new String("Mohab"));
//		db.deleteFromTable("People", ht4);
//		
//		System.out.println("Num pages after ="+db.tables.get(0).pages.size());
		
	}
}
