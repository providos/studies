import java.util.Scanner;

public class BreakPlate { 
	  
    // A utility function to get maximum of two integers 
    static int maxOfTwoCases(int a, int b) 
    { 
        return (a > b) ? a : b; 
    } 
  
    // Function to get minimum number of tries needed in worst case with n plates and f floors
    static int minimumTriesToBreakPlate(int plates, int floors) 
    { 
        /* Create a 2D array droppedPlateInFloor[i][j] which will represent minimum number of tries needed for i plates and j floors.
         * Where plate doesn't break
         *  */
        int droppedPlateInFloor[][] = new int[plates + 1][floors + 1]; // We create an instance of a new 2D array (matrix) where first [] is the row and second [] is the column, so size is [3][101]
        int result;
        int plate; // for current plate
        int floor; // for tries/floors
        int droppingFloor; // current floor/try
  
        // We create a base case for one floor or no floors depending on user's input
        // We need only one try for one floor and 
        // 0 tries for 0 floors 
        for (plate = 1; plate <= plates; plate++) { // where plates is 2 in our case 
            droppedPlateInFloor[plate][1] = 1; // assign 1 to one try for first floor for i plates
            droppedPlateInFloor[plate][0] = 0; // assign 0 tries for no floor for i plates
        } 
  
        // We always have x tries for x floors for one plate. 
        for (floor = 1; floor <= floors; floor++)
            droppedPlateInFloor[1][floor] = floor; // this will fill the first row for the first plate with all the floors/tries (1,2,3...100)
  
        // Fill rest of the entries in matrix using optimal substructure property 
        for (plate = 2; plate <= plates; plate++) { // Move to second row in matrix
            for (floor = 2; floor <= floors; floor++) { // Repeat for all entries in 2nd row, (fill second row for all floors)
                droppedPlateInFloor[plate][floor] = Integer.MAX_VALUE; // Assign the max value of int 2^31-1 
                for (droppingFloor = 1; droppingFloor <= floor; droppingFloor++) { // Compare values on each row/floor for each plate from one floor to the next one, i.e: floor 3 and 4, 6 and 7, etc.
                    result = 1 + maxOfTwoCases( // The result is the max of the two plates floor value
                                  droppedPlateInFloor[plate - 1][droppingFloor - 1], // this is for the first case (breaks)
                                  droppedPlateInFloor[plate][floor - droppingFloor]); // this is for the second case (doesn't break)
                                  
                    if (result < droppedPlateInFloor[plate][floor])
                        droppedPlateInFloor[plate][floor] = result;
                } 
            } 
        } 
  
        // droppedPlateInFloor[n][f] holds the result, which would be the same for any of the plates
        return droppedPlateInFloor[plates][floors]; 
    } 
  
    /* 
     * Problem: Write algorithm to find the lowest floor from which if a plate drops, it will break, do this with 
     * the MINIMUM amount of drops. (we actually don't want it to break, that's why we are asked for the lowest floor)
     * 
     * If we have one plate and 100 floors and we wish to be sure of obtaining the right result, 
     * the experiment can be carried out in only one way.
     * Drop the plate from the first-floor; if it survives, drop it from the second-floor. Go upward until it breaks. 
     * So in the worst case, this method may require 100 droppings.
     * 
     * But if we have 2 plates, the problem is not actually to find the critical floor (worst case), 
     * but merely to decide the floors from which plates should be dropped so that the total number of drops are minimized.
     * 
     * When we drop an egg from a floor f, there can be two cases (1) The plate breaks (2) The plate doesn’t break.
     * Assumptions:
    - A plate that survives a drop can be used again.
    - A broken plate must be discarded.
    - If a plate breaks when dropped, then it would break if dropped from next floor.
    - If a plate survives a drop then it would survive a lower floor/drop.
    - The effect of a drop is the same for all plates.

Breakdown:
If a plate breaks after dropping from ‘x’ floor, then we only need to check for floors lower than ‘x’ with remaining plates as some floor 
should exist lower than ‘x’ in which plate would not break; so the problem reduces to x-1 floors and n-1 plates.
If the plate doesn’t break after dropping from the ‘x’ floor, then we only need to check for floors higher than ‘x’; 
so the problem reduces to ‘f-x’ floors and n plates. (f = total amount of floors)
Since we need to minimize the number of drops in worst case, we take the maximum of the two cases. 
We consider the max of above two cases for every floor and choose the floor which has minimum amount of drops.

					1 + we add 1 to guarantee the plate breaks from the next possible floor or attempt
					|
					|	min of two cases (lowest floor or lowest tries)
					|	|
					|	|			first case plate breaks		second case plate doesn't break
					|	|				|								|
					|	|				|	reduce 1 plate and one drop	|
					|	|				|	|							| remain with same plates but remain with 
					|	|				|	|							| amount of floors minus the dropping floor (if 100 floors and the dropping floor is 30 we remain with 70 more floors to try)
					|	|				|	|							|	|
					|	|				|	|							|	|			 x being the dropping floor which is also the number of tries
					|	|				|	|							|	|			 |	
plateBreaks(n, f) = 1 + min{max(plateBreaks(n – 1, x – 1), plateBreaks(n, f – x)), where x is in {1, 2, …, f}} 

Suppose we have 2 plates and 2 floors: (2, 2) n=2, f=2
Remember we always have 2 possibilities (either breaks or not).

	So we start from floor 1. (2, 1) n=2, f=2, x=1
	
	* First case it breaks [plateBreaks(n – 1, x – 1)].
	* 
		We remain with 1 plate and x-1 floors because that's 1 drop, that is first try, (2-1, 1-1).
		So we see that from floor 1 it breaks, so we add 1 for this case because the lowest floor it takes not to break is 0.
		That means that in next floor it does break, so the lowest possible drop is 0 for (1,0)

	* Second case doesn't break [plateBreaks(n, f – x)].
	* 
		So we see that from floor 1 it doesn't break, we remain with the 2 plates and (f floors - current drop floor x), that is (2, 2-1)
		Now we have another floor/try, so the lowest possible drop is now 1
		
	* Next we compare these two dimensions (1,0),(2,1) values and take the max value of one of them 
	* In this case we remain with 1 and the amount of tries in worst case = 1 + max((1,0), (2,1))

So next we try from second floor. (2, 2) n=2, f=2, x=2

Again we have 2 possibilities:
	* First case it breaks, [plateBreaks(n – 1, x – 1)]. 
	* So we end up with (2-1, 2-2). (1,0), since we know the answer = 0 we don't need to evaluate it again
	
	Second case doesn't break [plateBreaks(n, f – x)].
	So from second floor we remain with (2 plates, 0 floors/tries).
	That means the lowest possible drop is 1.
	
	Now we add these cases and get 2 possible drops for 2 plates
     * 
     * For this exercise we will use the Dynamic Programming approach. 
    Because we don't want to process the same values for floors we already know the answer
    */
    public static void main(String args[]) 
    { 
    	Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
    	System.out.print("Enter plates: ");  
    	int plates= sc.nextInt();  
    	System.out.print("Enter floors: ");  
    	int floors= sc.nextInt();  
        //int plates = 2, floors = 100; // input
        System.out.println("Minimum number of tries in worst case with " + plates + " plates and " + floors + " floors is " + minimumTriesToBreakPlate(plates, floors)); 
    } 
}