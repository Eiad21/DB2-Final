package main;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;


public class Sandbox {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		int[] arr = {0,1,2,2,2,2,2,2,3,4};
//		int key = 2;
//		int first = 0;
//		int last = 8;
//		int mid = (first + last)/2;  
//		   while( first <= last ){  
//		      if ( arr[mid] < key ){  
//		        first = mid + 1;     
//		      }else if ( arr[mid] == key && first==last ){  
//		        System.out.println("Element is found at index: " + mid);  
//		        break;  
//		      }else{  
//		         last = mid - 1;  
//		      }  
//		      mid = (first + last)/2;  
//		   }  
//		   if ( first > last ){  
//		      System.out.println("Element is not found!");
//		   } 
//		Vector v = new Vector<Integer>();
//		v.add(1);
//		//v.add(1);
//		v.add(2);
//		
//		System.out.println(getFirstOccurence(v, 2));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date));
	}
	
	public static int getFirstOccurence(Vector v, Comparable key) {
		int first = 0;
		int last = v.size()-1;
		
		int mid = (first + last)/2;  
		   while( first <= last ){  
		      if ( ((Comparable)v.get(mid)).compareTo(key)<0 ){  
		        first = mid + 1;     
		      }else if (  ((Comparable)v.get(mid)).equals(key) && first==last ){  
		    	  return mid;
		      }else{  
		         last = mid - 1;  
		      }  
		      mid = (first + last)/2;  
		   }  
		   if ( first > last ){  
			   if(mid+1<v.size()&&v.get(mid+1).equals(key)) {
				   System.out.println("mohab is right");
				   return mid+1;
			   }
			   
		   }
		   return -1;
	}
}
