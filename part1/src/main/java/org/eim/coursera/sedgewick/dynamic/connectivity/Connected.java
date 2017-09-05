package org.eim.coursera.sedgewick.dynamic.connectivity;

public interface Connected {

  void union(int idx_p, int idx_q);
  boolean isConnected(int idx_p, int idx_q);

}
