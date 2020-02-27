package main;
import java.io.*;

public class Deserialize {
	public static void main(String[] args) {
		try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream("C:\\Users\\eiade\\Desktop\\People1698916685.txt"); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            Page p = (Page)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Object has been deserialized "); 
            System.out.println("N = " + p.N); 
            System.out.println("key = " + p.key); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
  
    } 
}