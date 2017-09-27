import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private static final int INITSIZE = 32;
  private int size;
  private Item[] array;

  public RandomizedQueue() {                // construct an empty randomized queue
    array = (Item[]) new Object[INITSIZE];
  }

  public boolean isEmpty() {                 // is the queue empty?
    return size == 0;
  }

  public int size() {                       // return the number of items on the queue
    return size;
  }

  public void enqueue(Item item) {          // add the item
    if (item == null) throw new IllegalArgumentException();
    if (size == (array.length - 1)) {
      int newSize;
      if (array.length <= 0) {
        newSize = INITSIZE;
      } else {
        newSize = array.length * 2;
      }
      array = Arrays.copyOf(array, newSize);
    }
    array[size++] = item;
//    validate(array, size);
  }

//  private void validate(int[] tarray, int tsize) {
//    for (int i = 1; i < tsize; i++) {
//      if (tarray[i] == 0) {
//        StdOut.println(tarray[i]);
//      }
//    }
//  }

  private Item findLast(Item[] tarray) {
    Item cItem;
    for (int i = tarray.length - 1; i >= 0; i--) {
      cItem = tarray[i];
      if (cItem != null) {
        tarray[i] = null;
        return cItem;
      }
    }
    return null;
  }

  public Item dequeue() {                    // remove and return a random item
    if (isEmpty()) throw new NoSuchElementException();
    int rndIndex = getRnd();
    Item rndItem = array[rndIndex];
    array[rndIndex] = array[size - 1];
    array[size - 1] = null;
    --size;
    if (array.length > INITSIZE * 2 && array.length / 2 > size) {
      pack();
    }
    return rndItem;
  }

  private void pack() {
    /* size is less than array.lenght */
    array = Arrays.copyOf(array, size * 2);
  }

  public Item sample() {                    // return (but do not remove) a random item
    if (isEmpty()) throw new NoSuchElementException();
    return array[getRnd()];
  }

  private int getRnd() {
    return StdRandom.uniform(size);
  }

  @Override
  public Iterator<Item> iterator() { // return an independent iterator over items in random order
    return new Iterator<Item>() {

      private int currentIndex = 0;
      private final int itSize = size;
      private int[] itArray = makeNewArray();

      private int[] makeNewArray() {
        int[] internal = new int[itSize];
        for (int i = 0; i < itSize; i++) {
          internal[i] = i;
        }
        StdRandom.shuffle(internal);
//        validate(internal, itSize);
        return internal;
      }

      @Override
      public boolean hasNext() {
        return currentIndex < itSize && array[itArray[currentIndex]] != null;
      }

      @Override
      public Item next() {
        if (currentIndex == itSize) {
          throw new NoSuchElementException();
        }
        return array[itArray[currentIndex++]];
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }

//  public static void main(String[] args)   // unit testing (optional)
//  {
//    int count = 1000;
//    RandomizedQueue<String> rq = new RandomizedQueue<>();
//    for (int i=0; i<count;i++) {
//      rq.enqueue(""+i);
//    }
//
////    rq.validate(rq.array, rq.size);
//    Iterator<String> rqi = rq.iterator();
//    int counter = 0;
//    while(rqi.hasNext()) {
//      StdOut.println(rqi.next());
//      counter++;
//    }
//    if (counter != count) {
//      StdOut.println("Size is wrong "+counter);
//    }
//    StdOut.println(rq.size());
//  }
}
