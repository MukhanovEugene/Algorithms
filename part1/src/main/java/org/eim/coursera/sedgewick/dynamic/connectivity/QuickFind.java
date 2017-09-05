package org.eim.coursera.sedgewick.dynamic.connectivity;

public class QuickFind extends DynamicallyConnected {

  public QuickFind(int size) {
    super(size);
  }

  public void union(int idx_p, int idx_q) {
    int p = id[idx_p];
    int q = id[idx_q];
    for (int i=0;i<id.length;i++) {
      if (id[i] == p) {
       id[i] = q;
      }
    }
  }

  public boolean isConnected(int idx_p, int idx_q) {
    return id[idx_p] == id[idx_q];
  }

}
