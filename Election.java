import java.util.*;

class Election{
  
  int numvoters; //if -1, then DNE
  int numcandidates; //
  ArrayList<Voter> voters = new ArrayList<Voter>();
  
  //Takes in number of votes and number of candidates for election
  public Election(int numvoter, int numcandidate)
  {
    ArrayList<Voter> newarray = new ArrayList<Voter>();
    for(int i = 0; i < numvoter; i++)
    {
      Voter a = new Voter(numcandidate);
      newarray.add(a);
    }
    this.voters = newarray;
    this.numvoters = numvoter;
    this.numcandidates = numcandidate;
  }
  
  //Gets the results of the election
  public ArrayList<Voter> getResults()
  {
    return voters;
  }
  
  //Get total number of votes of the election
  public int getnumvoter()
  {
    return numvoters;
  }
  
  //Gets the total number of candidates of the election
  public int getnumcandidates()
  {
    return numcandidates;
  }
  
  //Prints the results of the election
  public void showResults()
  {
    for(int i = 0; i < voters.size(); i++)
    {
    voters.get(i).printOrder();
    }
    
  }
  
  
  
  public static void main(String[] args)
  {
    Election a = new Election(3,3);
    Voter b = a.getResults().get(1);
    b.printOrder();
    
  }
  
}