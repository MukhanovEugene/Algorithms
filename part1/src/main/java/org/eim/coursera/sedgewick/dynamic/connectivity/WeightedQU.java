package org.eim.coursera.sedgewick.dynamic.connectivity;

public class WeightedQU extends DynamicallyConnected {

  private int[] sz;

  public WeightedQU(int size) {
    super(size);
    sz = new int[size];
    for (int i=0; i<sz.length; i++) {
      sz[i] = 1;
    }
  }

  @Override
  public void union(int idx_p, int idx_q) {
    int p = id[idx_p];
    int q = id[idx_q];
    if (p == q) return;
    if (sz[p] < sz[q]) {
      id[p] = q;
      sz[q] += sz[p];
    } else  {
      id[q] = p;
      sz[p] += sz[q];
    }
  }

  @Override
  public boolean isConnected(int idx_p, int idx_q) {
    return root(idx_p) == root(idx_q);
  }

  private int root(int x) {
    while(id[x] != x) {
      x = id[x];
    }
    return x;
  }

}
