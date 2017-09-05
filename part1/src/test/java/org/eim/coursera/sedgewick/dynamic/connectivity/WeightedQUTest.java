package org.eim.coursera.sedgewick.dynamic.connectivity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class WeightedQUTest {

  @Test
  public void testInitialization() {
    int size = 10;
    WeightedQU weightedQU = new WeightedQU(size);
    int[] id = weightedQU.getId();
    assertThat(id, is(notNullValue()));
    assertThat(id.length, is(size));
    for (int i=0; i < id.length; i++) {
      assertThat(id[i],is(i));
    }
  }

  @Test
  public void testConnection1() {
    int size = 10;
    int first = 1;
    int second = 9;
    WeightedQU weightedQU = new WeightedQU(size);
    weightedQU.union(first,second);
    assertThat(weightedQU.isConnected(first,second), is(true));
    assertThat(weightedQU.isConnected(first,0), is(false));
    assertThat(weightedQU.isConnected(first,8), is(false));
    assertThat(weightedQU.isConnected(0,second), is(false));
    assertThat(weightedQU.isConnected(8,second), is(false));
  }

  @Test
  public void testConnectionChain() {
    int size = 10;
    WeightedQU weightedQU = new WeightedQU(size);
    weightedQU.union(1,8);
    weightedQU.union(6,4);
    weightedQU.union(8,4);
    assertThat(weightedQU.isConnected(1,4), is(true));
  }

}
