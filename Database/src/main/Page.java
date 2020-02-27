package main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Page extends Vector {
	int N;
	String key;
	public Page(Tuple tuple ,int N, String key) {
		this.N = N;
		this.key = key;
		this.add(tuple);
	}
	
	public Tuple insertIntoPage(Tuple t, boolean isLastPage) {
		//Last page has space
		System.out.println(this.size());
		if(isLastPage&&this.size()<N) {
			System.out.println("a" +t.theTuple.get("name"));
			this.add(t);
			System.out.println(this.size());
			this.sort(null);
			return null;
		}
		//Last page has no space tl3 mlhash lzma :(
//		else if(isLastPage&&this.size()==N) {
//			System.out.println("b" +t.theTuple.get("name"));
//			return t;
//		}
		//Element bigger than kol 7aga but not last page
		else if(t.compareTo(this.get(this.size()-1))>0){
			System.out.println("c" +t.theTuple.get("name"));
			return t;
			
		}
		//Some element is larger than my element and page not full
		else if(this.size()<N&&t.compareTo(this.get(this.size()-1))<=0) {
			System.out.println("d" +t.theTuple.get("name"));
			this.add(t);
			this.sort(null);
			return null;
		}
		//Some element is larger than my element and page is full even if last page
		else if(this.size()==N&&t.compareTo(this.get(this.size()-1))<=0) {
			System.out.println("e" +t.theTuple.get("name"));
			this.add(t);
			this.sort(null);
			t = (Tuple) this.get(this.size()-1);
			this.remove(t);
			System.out.println("Shifted element= "+t.theTuple.get("name"));
			return t;
		}
		
		
		
		return null;
	}

	public void deleteFromPage(Hashtable<String, Comparable> ht) {
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<Comparable> columnValues = new ArrayList<Comparable>();
		
		Enumeration<String> enumeration = ht.keys();
        // iterate using enumeration object
        while(enumeration.hasMoreElements()) {
 
            String key = enumeration.nextElement();
            columnNames.add(key);
			columnValues.add((Comparable)ht.get(key));
        }
        for(int i = 0;i<this.size();i++) {
        	Tuple t = (Tuple) this.get(i);
        	boolean satisfiesCondition = true;
        	
        	for(int j = 0; j<columnNames.size();j++) {
        		if(!(t.theTuple.get(columnNames.get(j)).equals(columnValues.get(j))))
        			satisfiesCondition = false;
        	}
        	if(satisfiesCondition)
        		this.remove(i);
        }
	}
	
	
}
