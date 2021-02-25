package my.studies;

import java.io.IOException;
import java.util.Scanner;

public class Interfaz {
	
	// This class is responsible of creating and calling a ToBuyList instance, which loads the file. We add a try-catch block to the constructor to catch a possible error in the file.
	// We change the file name to produce the error, and on run the problem should print an error line as shown below, this doesn't break the program because we "handle" it
	private ToBuyList toBuyList;
	
	public Interfaz(String file) {
		try {
			toBuyList = new ToBuyList(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There has been an error with the file " + file);
		}
	}
	
	public void showMenu() {
		Scanner scanner = new Scanner(System.in);
		int selectedOption;
		do {
			System.out.println("To exit type 0");
			System.out.println("To show the element type the position of the element");
			selectedOption = scanner.nextInt();
			if(selectedOption != 0) {
				showElement(selectedOption);
			}
		} while (selectedOption != 0);
	}

	private void showElement(int selectedOption) {
		System.out.println("The element " + selectedOption + " from the list is " + toBuyList.getElement(selectedOption));
		
	}

}
