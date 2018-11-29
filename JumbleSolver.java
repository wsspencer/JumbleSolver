//Author: Scott Spencer

import java.io.*;
import java.util.*;

public class JumbleSolver {
	public static void main(String[] args) {
		
		//read in a dictionary file and start a scanner to iterate over it
		Scanner sc = null;
		try {
			File dictionaryFile = new File("dictionary.txt");
			sc = new Scanner(dictionaryFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(1);
		}
		//generate hash table for easier navigation of our dict
		HashMap<String,String> lookup = new HashMap<String,String>();
		
		//iterate over our dict file and add it to our hash table
		while (sc.hasNextLine()) {
			//our value to add to the map
			String value = sc.nextLine();
			//Ensure value is lowercase so there's no mixups with case in the dictionary
			value = value.toLowerCase();
			//the sorted word will serve as the key
			char[] chars = value.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);
			//add to the map at (key, value) in such a way that ALL variations of that jumbled
			//word will be printed
			String current = lookup.get(key);
			//tack on more words if there is already a value at the key.  
			if (current != null) {
				lookup.put(key, current + "\n" + value);
			}
			else {
				lookup.put(key, value);
			}
		}
		
		//get our jumbled input word from our user
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a word: ");
		String word = in.next();
		//Ensure key is lowercase so there's no mixups with case in the dictionary
		word = word.toLowerCase();
		//perform a lookup in our hash map using our sorted input word as the key
		while (!word.equalsIgnoreCase("quit")) {
			//sort our input word by the same hashing algorithm used to create our hash map
			char[] inChars = word.toCharArray();
			Arrays.sort(inChars);
			String inKey = new String(inChars);
			if (lookup.containsKey(inKey)) {
				System.out.println(lookup.get(inKey));
			}
			else {
				System.out.println("Not found.");
			}
			System.out.print("\nPlease enter a word: ");
			word = in.next();	
		}
	}

}
