import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;

class Voter {
  
  private int[] order;
  
  //Array shuffler to generate the random preference table.
private static void shuffleArray(int[] array)
{
    int index;
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--)
    {
        index = random.nextInt(i + 1);
        if (index != i)
        {
            array[index] ^= array[i];
            array[i] ^= array[index];
            array[index] ^= array[i];
        }
    }
}
  //Voter that takes in the total number of candidates. The voter will generate a random prefererance table of the candidates.
  public Voter(int number)
  {
    int[] a = new int[number];
    a[0] = 1;
    for(int i = 1; i < a.length; i++)
    {
      a[i] = a[i-1] + 1;
    }
    shuffleArray(a);
    this.order = a;
    
  }
  
  //Gets the voter's prefereance order
  public int[] getOrder()
  {
    return order;
  }
  
  //Prints the voter's preference order.
  public void printOrder()
  {
    System.out.println(Arrays.toString(order)); 
  }
  

  
  
}