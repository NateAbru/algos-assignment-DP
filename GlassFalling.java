/**

 * Glass Falling

 */

public class GlassFalling {

	
	public int min(int x, int y)
	{
		if(x < y) return x;
		return y;
	}
	public int max(int x, int y)
	{
		if(x > y) return x;
		return y;
	}


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
  public int glassFallingMemoized(int floors, int sheets) 
  {
	  int memo[][] = new int[floors + 1][sheets + 1];
      
      // initialization of the double dimension array
      for (int i = 0; i < floors; i++) 
      {
          for (int j = 0; j < sheets; j++) 
          {
              memo[i][j] = 0;
          }
      }
      return glassFallingMemoRecur(floors, sheets, memo);
  }
  public int glassFallingMemoRecur(int floors, int sheets, int[][] memo)
  {
	 //if value was already computed, return the value so the recursive call
	 //does not need to recalculate it
	 if(memo[floors][sheets] != 0)
	 {
		 return memo[floors][sheets];
	 }
	 //return floors when floors is 0 or 1 since
	 //when floor is 0 trials are 0 and floors 1 trials is 1
	 //when sheets is 1 worst case would be # of floors
	 if(floors == 0 || floors == 1 || sheets == 1)
	 {
		 return floors;
	 }
	 memo[floors][sheets] = floors;
	 int memValue;
	 for(int i = 1; i <= floors; i++) 
	 {
		 memValue = max(glassFallingMemoRecur(i - 1, sheets - 1, memo), glassFallingMemoRecur(floors - i, sheets, memo));
	 
		 if(memValue < memo[floors][sheets])
		 {
			 memo[floors][sheets] = memValue + 1;
		 }
	 }
	 return memo[floors][sheets];
  }
 


  // Do not change the parameters!

  public int glassFallingBottomUp(int floors, int sheets) {
	  
    //create an array to store the values
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
    				trialMin = glassArray[f2-i][s2] + 1;
    			}
    			else trialMin = glassArray[i-1][s2 - 1] + 1;
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

      int minTrials1Recur = gf.glassFallingMemoized(27, 2);

      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);

      int minTrials2Recur = gf.glassFallingMemoized(100, 3);

      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);

      System.out.println(minTrials1Recur + " " + minTrials1Bottom);

      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
     

  }

}
