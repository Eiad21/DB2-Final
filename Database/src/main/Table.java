package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;
// 
public class Table implements Serializable {
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<String> columnTypes = new ArrayList<String>();
	ArrayList<String> pages = new ArrayList<String>();
	String tableName;
	String tableKey;
	int N = 2;
	public Table(String tableName, String tableKey, Hashtable ht) throws IOException {
		this.tableName = tableName;
		this.tableKey = tableKey;
		Enumeration<String> enumeration = ht.keys();
        // iterate using enumeration object
        while(enumeration.hasMoreElements()) {
 
            String key = enumeration.nextElement();
            columnNames.add(key);
			columnTypes.add((String)ht.get(key));
        }
		//DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    //Date dateobj = new Date();
	    columnNames.add("TouchDate");
		columnTypes.add("java.util.Date");
		
		String directory = "C:\\Users\\eiade\\Desktop\\"+tableName+".txt";
		File file = new File(directory);
		file.createNewFile();
		FileOutputStream fileO = new FileOutputStream(directory, true);
        ObjectOutputStream out = new ObjectOutputStream(fileO);
        out.writeObject(this); 
        
        out.close(); 
        fileO.close(); 
	}
	
	public void insertIntoTable(String tableName, Hashtable<String, Comparable> ht) throws IOException, ClassNotFoundException {
		Tuple t = new Tuple(ht, tableKey);
		if(pages.size()==0) {
			System.out.println("test2");
			Random random = new Random();
			int r = random.nextInt(2147000000);
			String directory = "C:\\Users\\eiade\\Desktop\\" + tableName + r + ".txt";
			Page p = new Page(t, N, tableKey);
			File file = new File(directory);
			file.createNewFile();
			FileOutputStream fileO = new FileOutputStream(directory, true);
            ObjectOutputStream out = new ObjectOutputStream(fileO);
            pages.add(directory);
            out.writeObject(p); 
            
            out.close(); 
            fileO.close(); 
			
		}
		else {
			int pageCounter = 0;
			do {
				FileInputStream file = new FileInputStream(pages.get(pageCounter)); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	            
	            // Method for deserialization of object 
	            Page p = (Page)in.readObject();
	            boolean lastPage = pageCounter == pages.size()-1;
	            t = p.insertIntoPage(t, lastPage);
	            if(!(t==null)&&lastPage) {
	            	// START
	            	
	            	Random random = new Random();
	    			int r = random.nextInt(2147000000);
	    			String directory = "C:\\Users\\eiade\\Desktop\\" + tableName + r + ".txt";
	    			Page pLast = new Page(t, N, tableKey);
	    			File fileLast = new File(directory);
	    			fileLast.createNewFile();
	    			FileOutputStream fileO = new FileOutputStream(directory, true);
	                ObjectOutputStream out = new ObjectOutputStream(fileO);
	                pages.add(directory);
	                out.writeObject(pLast); 
	                
	                out.close(); 
	                fileO.close();
	                
	                t = null;
	            	
	            	// END
	            }
	            
	            // START SER USED PAGE
	            
	            FileOutputStream fileSER = new FileOutputStream(pages.get(pageCounter));
                ObjectOutputStream outSER = new ObjectOutputStream(fileSER);
                outSER.writeObject(p); 
                
                outSER.close(); 
                fileSER.close();
	            
	            // END SER USED PAGE
	            
	            pageCounter++;
	            	
			}while(!(t==null));
		}
	}

	public void deleteFromTable(String strTableName, Hashtable<String, Comparable> ht) throws IOException, ClassNotFoundException {
			for(int pageCounter = 0;pageCounter<pages.size();pageCounter++) {
				FileInputStream file = new FileInputStream(pages.get(pageCounter)); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	             
	            // Method for deserialization of object 
	            Page p = (Page)in.readObject();
	            System.out.println("Size before delete= "+p.size());
	            p.deleteFromPage(ht);
	            
	            FileOutputStream fileSER = new FileOutputStream(pages.get(pageCounter));
                ObjectOutputStream outSER = new ObjectOutputStream(fileSER);
                outSER.writeObject(p); 
                
                outSER.close(); 
                fileSER.close();
	            if(p.size()==0) {
	            	String dir = pages.get(pageCounter);
	            	File toDel = new File(pages.get(pageCounter));
	            	System.out.println(pages.get(pageCounter));
	            	toDel.delete();
	            	pages.remove(pageCounter);
	            }
	            System.out.println("Size after delete= "+p.size());
			}
	} //eficiency ya bro
	
//	public static void main(String[] args) throws IOException {
//		Hashtable<String, String> ht = new Hashtable<String, String>();
//		ht.put("id", "java.lang.Integer");
//		ht.put("name", "java.lang.String");
//		ht.put("gpa", "java.lang.double");
//		Table table = createTable("Student", "id", ht);
//		Table t = createTable("People", "id", ht);
//		Hashtable<String, Comparable> theTuple = new Hashtable<String, Comparable>();
//		theTuple.put("id", new Integer(1));
//		theTuple.put("name", new String("Mohab"));
//		theTuple.put("gpa", new Double(0.99));
//		t.insertIntoTable("People", theTuple);
//		System.out.println("Hi");
//	
//} nbos 3leha hhhhhhhbm best effort service +1 farah :P
	
	// tdf3y kam??? oh yeah wt f**** :)
	public void updateTable(String strTableName, String key, Hashtable<String, Comparable> ht) throws IOException, ClassNotFoundException {
		//if key is in HT throw exception
		for(int pageCounter = 0;pageCounter<pages.size();pageCounter++) {
			FileInputStream file = new FileInputStream(pages.get(pageCounter)); 
            ObjectInputStream in = new ObjectInputStream(file); 
             
            // Method for deserialization of object 
            Page p = (Page)in.readObject();
            // to be implemented VV
            // p.updatePage(ht);
            
            FileOutputStream fileSER = new FileOutputStream(pages.get(pageCounter));
            ObjectOutputStream outSER = new ObjectOutputStream(fileSER);
            outSER.writeObject(p); 
            
            outSER.close(); 
            fileSER.close();
           
            
		}
}

}
