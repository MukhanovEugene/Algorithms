package org.eim.coursera.sedgewick.mergesort;

public class MergeSortBU {

  private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo, j = mid + 1;

    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++]; // i out of it's half
      else if (j > hi) a[k] = aux[i++]; // j out of it's half
      else if (lessOrEquals(aux[i], aux[j])) a[k] = aux[i++];
      else a[k] = aux[j++];
    }

  }

  private boolean isSorted(Comparable[] a, int i, int hi) {
    for (int k = i; k < hi; k++) {
      if (a[i].compareTo(a[i + 1]) > 0) { // any wrong order
        return false;
      }
    }
    return true;
  }

  private boolean lessOrEquals(Comparable aux, Comparable aux1) {
    return aux.compareTo(aux1) <= 0;
  }

  public void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

    for (int sz = 1; sz < hi; sz = sz + sz) {
      for (int i = 0; i < hi - sz; i += sz * 2) {
        merge(a,aux,i,i + sz - 1, Math.min(i + (sz * 2) - 1, hi - 1));
      }
    }

  }

}
