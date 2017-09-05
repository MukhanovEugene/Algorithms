package org.eim.coursera.sedgewick.dynamic.connectivity;

public class QuickUnion extends DynamicallyConnected {

  public QuickUnion(int size) {
    super(size);
  }

  private int root(int idx) {
    while(id[idx] != idx) {
      idx = id[idx];
    }
    return idx;
  }

  public boolean isConnected(int idx_p, int idx_q) {
    return root(idx_p) == root(idx_q);
  }

  public void union(int idx_p, int idx_q) {
    int p = id[idx_p];
    int q = id[idx_q];
    id[p] = q;
  }

}
