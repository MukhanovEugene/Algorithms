package org.eim.coursera.sedgewick.quicksort;

public class QuickSort {

  public void sort(Comparable[] _input) {
    sort(_input,0,_input.length-1);
  }

  private void sort(Comparable[] _input, int lo, int hi) {
    Comparable mid = _input[lo + (hi-lo)/2];
    int i = lo;
    int j = hi;
    while(i <= j) {
      while (_input[i].compareTo(mid) < 0) {
        i++;
      }
      while (_input[j].compareTo(mid) > 0) {
        j--;
      }
      if (i <= j) {
        exchange(_input, i, j);
        i++;
        j--;
      }
    }
    if (lo < j) {
      sort(_input, lo, j);
    }
    if (i < hi) {
      sort(_input, i, hi);
    }
  }

  private void exchange(Comparable[] input, int i, int j) {
    Comparable temp = input[i];
    input[i] = input[j];
    input[j] = temp;
  }

}
