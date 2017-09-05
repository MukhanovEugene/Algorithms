package org.eim.coursera.sedgewick.dynamic.connectivity;

abstract class DynamicallyConnected implements Connected {

  int[] id;

  public DynamicallyConnected(int size) {
    id = new int[size];
    init(size);
  }

  private void init(int size) {
    for (int i=0;i<size;i++) {
      id[i] = i;
    }
  }

  int[] getId() {
    return id.clone();
  }

}
