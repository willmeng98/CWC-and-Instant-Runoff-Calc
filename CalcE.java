import java.util.*;

class CalcE{
  
  //Calculates the winner between 2 candidates when given an Election a of more than 2 candidates
  public static int calcwinner(int candidate1,int candidate2, Election a)
  {
    int score1 = 0;
    int score2 = 0;
    for(int i = 0; i < a.getResults().size(); i++)
    {
      int[] table = a.getResults().get(i).getOrder();
      
      int indexof1 = 0;
      int indexof2 = 0;
      for(int z = 0; z < table.length; z++)
       {
        if(table[z] == candidate1)
        {
          indexof1 = z;
        }
        if(table[z] == candidate2)
        {
          indexof2 = z;
        }     
        }
      
      if(indexof1 < indexof2)
      {
        score1++;
      }
      else
      {
        score2++;
      }
      
    }
    if(score1 > score2)
    {
     return candidate1;
    }
    else
    {
      return candidate2;
    }
  }
  
  //Finds the Condorcet Winner of Election a. If none exists, returns -1.
  public static int findCWC(Election a)
  {
    int[] scores = new int[a.getnumcandidates()];
    int[][] tableresults = new int[a.getnumcandidates()][a.getnumcandidates()];
    for(int i = 0; i < tableresults.length;i++)
    {
      for(int r = 0; r < tableresults[i].length; r++)
      {
        tableresults[i][r] = calcwinner(i + 1,r +1,a);
        //System.out.print("--" + tableresults[i][r]);
        scores[tableresults[i][r] -1]++;
        //System.out.print(tableresults[i][r]);
      }
      
    }
    for(int i = 0; i < scores.length;i++)
    {
      if(scores[i] == a.getnumcandidates() *2 -1)
      {
        return i+1;
      }
    }
    return -1;
  }
  
  //Removes candidate b from preference schedule a
  public static int[] removein(int[] a, int b)
  {
    int index = 0;
    int[] c = new int[a.length - 1];
    for(int i = 0; i < a.length; i++)
    {
      if(a[i] == b)
      {
        index = i;
        break;
      }
    }
    for(int i = 0; i < c.length; i++)
    {
      if(i < index)
      {
        c[i] = a[i];
      }
      if(i >= index)
      {
        c[i] = a[i+1];
      }
    }
    return c;
  }
  
  //Finds the instant runoff winner of election a.
  public static int findins(Election a)
  {
    
    int[][] results = new int[a.getnumvoter()][];
    for(int i = 0; i < results.length;i++)
    {
      results[i] = a.getResults().get(i).getOrder();
    }
    int candidates = a.getnumcandidates();
    int[] count1 = new int[a.getnumvoter()];
    int[] final1 = new int[a.getnumvoter()];
    
    int numberdone = 0;
    while(numberdone != candidates - 1)
    {
      for(int i = 0; i < results.length ;i++)
      {
        count1[results[i][0]-1]++;
      }
      
      int minindex = 0;
      int minvalue = 100000000;
      for(int i = 0; i < count1.length;i++)
      {
        if((count1[i] <= minvalue)&&(count1[i] >= 0))
        {
          minvalue = count1[i];
          minindex = i;
        }
        //System.out.print(count1[i]);
      }
      //System.out.println("------");
      count1[minindex] = -1;
      for(int i = 0; i < results.length;i++)
      {
        results[i] = removein(results[i], minindex + 1);
      }
      
      for(int i = 0; i < results.length;i++)
    {
        
        for(int r = 0; r < results[i].length;r++)
    {
         // System.out.print(results[i][r]);
        }
       // System.out.println();
      }
      
      
      
      for(int i = 0; i < count1.length;i++)
      {
        final1[i] = count1[i];
      }
      for(int i = 0; i < count1.length;i++)
      {
        if(count1[i] != -1)
        {
          count1[i] = 0;
        }
      }
      
      numberdone++;  
    }
    
    int maxindex = 0;
    for(int i = 0; i < final1.length;i++)
      {
      //System.out.println(count1[i]);
      if((final1[i] >= 1))
        {
        return i + 1;
      }
      
    }
    return -1;
  }
  
  public static void main(String args[])
  {
    
    int Number_Elections = 100000; //number of elections to run
    
    int hasCWC = 0;
    int CWCisINS = 0;
    for(int i = 0; i < Number_Elections ; i++) 
    {
      Election a = new Election(4,3); //(#voters,#cands);
      int willcwc = findCWC(a);
      int willins = findins(a);
      if(willcwc != -1)
      {
        hasCWC++;
      }
      if(willcwc == willins)
      {
        CWCisINS++;
      }
    }
    
    
    Election a = new Election(4,3);
    a.showResults();
    int x = findCWC(a);
    System.out.println(x);
    System.out.println(findins(a));
    //System.out.println(calcwinner(3,2,a));
    //System.out.println("Instant Runoff elected CWC: " + CWCisINS);
    //System.out.println("Has CWC: " + hasCWC );
    //System.out.println("Total number of elections ran: " + Number_Elections);
    
    
  }
  
}