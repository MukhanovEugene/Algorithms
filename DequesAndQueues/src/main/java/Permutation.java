import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

//  private static final String[] test = {"first", "second", "third", "forth", "fifth", "sixes"};

  public static void main(String[] args) {
    int count = Integer.parseInt(args[0]);
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
    for (String value : StdIn.readAllStrings()) {
      randomizedQueue.enqueue(value);
    }

//    for (String value: test) {
//      randomizedQueue.enqueue(value);
//    }

    Iterator<String> iterator = randomizedQueue.iterator();
    while (count > 0 && iterator.hasNext()) {
      StdOut.println(iterator.next());
      count--;
    }
  }

}
