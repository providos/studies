package my.studies;

import java.util.Scanner;

public class Interfaz {
	
	private ToBuyList toBuyList;
	
	public Interfaz(String file) {
		toBuyList = new ToBuyList(file);
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
