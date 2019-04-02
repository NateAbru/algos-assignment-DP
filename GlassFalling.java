/**

 * Glass Falling

 */

public class GlassFalling {



  // Do not change the parameters!

  public int glassFallingRecur(int floors, int sheets) {
     
	 //Base Case
	 //if there's no floors no trials are possible since there are no floors to drop the glass from
	 //if there's only one floor only one trial can be done
	 //if we only have one sheet we need to check each floor starting from 1,
	 //assuming worst case scenario if each floor does not cause the sheet
	 //to break then with one sheet the number of trials would be the 
	 //number of floors
	 if(floors == 0 || floors == 1 || sheets == 1)
	 {
		 return floors;
	 }
	
	 int min = floors;
	 int trialMin;
	 for(int i = 1; i <= floors; i++)
	 {
		if(glassFallingRecur(i - 1, sheets - 1) < glassFallingRecur(floors - i, sheets))
		{
			trialMin = glassFallingRecur(floors - i, sheets);
		}
		else trialMin = glassFallingRecur(i - 1, sheets - 1);
		if(trialMin < min) min = trialMin;
	 }
    return min + 1;
  }



  // Optional:

  // Pick whatever parameters you want to, just make sure to return an int.

  public int glassFallingMemoized() {

    // Fill in here and change the return

    return 0;

  }



  // Do not change the parameters!

  public int glassFallingBottomUp(int floors, int sheets) {
	  
    //create an array to store the memoized values
    //use #of floors and sheets for the double dimension array
	//and add 1 or out of bounds exception will be declared due to for loop bounds
	int[][] glassArray = new int[floors+1][sheets+1];
	int trialMin;
	
	//stores the value of 1 for when there is only 1 floor as there
	//could only be 1 trial in such a case and
	//0 when there are no floors since we cannot drop any sheet
	for(int s = 1; s <= sheets; s++)
	{
		glassArray[1][s] = 1;
		glassArray[0][s] = 0;
	}
	//stores the value of # of floors when there is only 1 sheet
	//since the worst case scenario would be to check 
	//each floor from the bottom up when there is only 1 sheet
    for(int f = 1; f <= floors; f++)
    {
    	glassArray[f][1] = f;
    }
    //this for loop stores the trial # values in the remaining indexes
    for(int s2 = 2; s2 <= sheets; s2++) 
    {
    	for(int f2 = 2; f2 <= floors; f2++)
    	{
    		glassArray[f2][s2] = floors;
    		for(int i = 1; i <= f2; i++)
    		{
    			if(glassArray[i - 1][s2 - 1] < glassArray[f2-i][s2])
    			{
    				trialMin = 1 + glassArray[f2-i][s2];
    			}
    			else trialMin = 1 + glassArray[i-1][s2 - 1];
    			if(trialMin < glassArray[f2][s2]) glassArray[f2][s2] = trialMin;
    		}
    	}
    }
    return glassArray[floors][sheets];
  }





  public static void main(String args[]){
	  
	  GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure

      // in your final turned-in copy, these are the only things printed

      int minTrials1Recur = gf.glassFallingRecur(27, 2);

      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);

      int minTrials2Recur = gf.glassFallingRecur(100, 3);

      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);

      System.out.println(minTrials1Recur + " " + minTrials1Bottom);

      System.out.println(minTrials2Recur + " " + minTrials2Bottom);

  }

}