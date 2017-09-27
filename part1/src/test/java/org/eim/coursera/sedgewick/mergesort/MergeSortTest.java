package org.eim.coursera.sedgewick.mergesort;

import org.junit.Test;

public class MergeSortTest {

  @Test
  public void testSimpleSort1() {
      Comparable[] a = {6,5,4,11,88,12,99};
      MergeSort mergeSort = new MergeSort();
      mergeSort.sort(a);
      for (int i=0; i<a.length; i++) {
        System.out.println(a[i]);
      }
  }


}
