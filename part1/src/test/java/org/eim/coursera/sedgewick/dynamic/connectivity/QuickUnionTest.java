package org.eim.coursera.sedgewick.dynamic.connectivity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class QuickUnionTest {

  @Test
  public void testInitialization() {
    int size = 10;
    QuickUnion quickUnion = new QuickUnion(size);
    int[] id = quickUnion.getId();
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
    QuickUnion quickUnion = new QuickUnion(size);
    quickUnion.union(first,second);
    assertThat(quickUnion.isConnected(first,second), is(true));
    assertThat(quickUnion.isConnected(first,0), is(false));
    assertThat(quickUnion.isConnected(first,8), is(false));
    assertThat(quickUnion.isConnected(0,second), is(false));
    assertThat(quickUnion.isConnected(8,second), is(false));
  }

  @Test
  public void testConnectionChain() {
    int size = 10;
    QuickUnion quickUnion = new QuickUnion(size);
    quickUnion.union(1,8);
    quickUnion.union(6,4);
    quickUnion.union(8,4);
    assertThat(quickUnion.isConnected(1,4), is(true));
  }

}
