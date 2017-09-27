package org.eim.coursera.sedgewick.quicksort;

import org.junit.Test;

public class QuickSortTest {

  @Test
  public void testQuickSort1() {
    QuickSort quuckSort = new QuickSort();
    Comparable[] input = {567,342,11,2223,879,0,0,1,1,541,309,51,909,99,169};
    quuckSort.sort(input);
    for (int i=0; i<input.length; i++) {
      System.out.println(input[i]);
    }
  }

}
