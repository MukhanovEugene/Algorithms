import java.util.Iterator;
import java.util.NoSuchElementException;
//
//import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

  private Node<Item> first;
  private Node<Item> last;
  private int size;

  public Deque() {                          // construct an empty deque
  }

  public boolean isEmpty() {                 // is the deque empty?
    return size == 0;
  }

  private Node<Item> getFirst() {
    return first;
  }

  public int size() {                       // return the number of items on the deque
    return size;
  }

  public void addFirst(Item item) {        // add the item to the front
    if (item == null) throw new IllegalArgumentException();
    if (first == null) {
      first = new Node<Item>(item);
    } else {
      first = new Node<Item>(item, first, null);
    }
    if (last == null || size == 0) {
      last = first;
    }
    size++;
  }

  public void addLast(Item item) {           // add the item to the end
    if (item == null) throw new IllegalArgumentException();
    if (last == null) {
      last = new Node<Item>(item);
    } else {
      last = new Node<Item>(item, null, last);
    }
    if (first == null || size == 0) {
      first = last;
    }
    size++;
  }

  public Item removeFirst() {               // remove and return the item from the front
    if (size == 0) throw new NoSuchElementException();
    Item firstItem = first.getValue();
    first = first.getNext();
    if (first != null) {
      first.setPrevious(null);
    } else {
      last = null;
    }
    size--;
    return firstItem;
  }

  public Item removeLast() {                 // remove and return the item from the end
    if (size == 0) throw new NoSuchElementException();
    Item lastItem = last.getValue();
    last = last.getPrevious();
    if (last != null) {
      last.setNext(null);
    } else {
      first = null;
    }
    size--;
    return lastItem;
  }

  public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
    return new Iterator<Item>() {

      private Node<Item> current = getFirst();

      @Override
      public boolean hasNext() {
        return current != null && current.getValue() != null;
      }

      @Override
      public Item next() {
        if (current == null) {
          throw new NoSuchElementException();
        }
        Item currItem = current.getValue();
        current = current.getNext();
        return currItem;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }

//  public static void main(String[] args) {  // unit testing (optional)
//    Deque<String> deque = new Deque<>();
//    deque.addLast("1");
//    deque.addLast("2");
//    if (deque.removeFirst().equals("1")) {
//      StdOut.println("1");
//    }
//    deque.addFirst("4");
//    deque.addLast("5");
//    StdOut.println(deque.removeFirst());
//    StdOut.println(deque.removeFirst());
//    StdOut.println(deque.removeLast());
//    Iterator it = deque.iterator();
//    if (it.hasNext()) {
//      StdOut.println(it.next());
//    }
//  }

}

class Node<Item> {

  private Node<Item> next;
  private Node<Item> previous;
  private final Item value;

  Node(Item value) {
    this.value = value;
  }

  Node(Item value, Node<Item> next, Node<Item> previous) {
    if (value == null) {
      throw new NoSuchElementException("Item is null");
    }
    this.value = value;
    this.next = next;
    this.previous = previous;
    if (this.previous != null) {
      this.previous.setNext(this);
    }
    if (this.next != null) {
      this.next.setPrevious(this);
    }
  }

  public Node<Item> getNext() {
    return next;
  }

  public Node<Item> getPrevious() {
    return previous;
  }

  public Item getValue() {
    return value;
  }

  public void setNext(Node<Item> newNext) {
    this.next = newNext;
  }

  public void setPrevious(Node<Item> newPrevious) {
    this.previous = newPrevious;
  }

  public String toString() {
    return getValue().toString();
  }
}