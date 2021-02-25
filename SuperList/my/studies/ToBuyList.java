package my.studies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// ToBuyList is the blueprint/frame of a list of articles to buy, in here we specify the methods and attributes of this list

// Create the class with an appropiate name
public class ToBuyList {
	
	// ~ Attributes / Fields ------------------------------------------------------------------------------------------------------------------
	// Note: "list" is a private field/attribute of ToBuyList class (private meaning it can only be accessed/modified from this class only). 
	// Side note: We may get the value from another class but not access it entirely to modify it. So it's a safe way to protect the content
	
	private ArrayList<String> list;
	
	
	// ~ Constructors -------------------------------------------------------------------------------------------------------------------------
	/*
	 * The constructor is called when an object of a class is created. It can be used to set initial values for object attributes.
	 * Note that the constructor name must match the class name, and it cannot have a return type (like void).
	 * All classes have constructors by default: if you do not create a class constructor yourself, Java creates one for you. 
	 * However, then you are not able to set initial values for object attributes.
	 * */
	
	/**
	 * ToBuyList is a constructor of the class, meaning it initializes a ToBuyList instance with a fileName as initial value
	 * fileName is the file that has the list of articles to buy
	 * 
	 */
		
	public ToBuyList(String fileName) {
		// When this constructor is created, we call loadList method and pass on the fileName
		loadList(fileName);
	}
	
	// ~ Methods ------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * loadList is a private void method, meaning it can only be accessed/used from within this class, it takes the fileName as input and creates a new ArrayList
	 * 
	 */
	private void loadList(String fileName) {
		// Create and store a new ArrayList of the articles to buy, from the file into list variable
		
		/*  We add a try-catch block since Files.readAllLines is a read-write kind of method, if we don't do this, the program will not compile
			There are two types of exceptions: Checked and Unchecked.
			Checked: are the exceptions that are checked at compile time. 
			If some code within a method throws a checked exception, then the method must either handle the exception or it must specify the exception using throws keyword.
		 	In this case, Files.readAllLines() method does throw a checked exception (IOException to be specific) so we must add an exception handler
		 	Unchecked: are the exceptions that are not checked at compile time. Instead when the program runs.
		 */
		try {
			list = new ArrayList<>(Files.readAllLines(Paths.get(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * getElement is a public method, meaning it may be used outside of this class, it takes and int number as the index and returns the name of the article from the list in a String format
	 * 
	 */
	public String getElement(int index) {
		if(index < list.size()) {
			return list.get(index);
		}
		else {
			return "not found";
		}
		
	}

}
