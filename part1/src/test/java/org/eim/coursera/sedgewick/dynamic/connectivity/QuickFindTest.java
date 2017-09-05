package org.eim.coursera.sedgewick.dynamic.connectivity;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class QuickFindTest {

  @Test
  public void testInitialization() {
    int size = 10;
    QuickFind quickFind = new QuickFind(size);
    int[] id = quickFind.getId();
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
    QuickFind quickFind = new QuickFind(size);
    quickFind.union(first,second);
    assertThat(quickFind.isConnected(first,second), is(true));
    assertThat(quickFind.isConnected(first,0), is(false));
    assertThat(quickFind.isConnected(first,8), is(false));
    assertThat(quickFind.isConnected(0,second), is(false));
    assertThat(quickFind.isConnected(8,second), is(false));
  }

  @Test
  public void testConnectionChain() {
    int size = 10;
    QuickFind quickFind = new QuickFind(size);
    quickFind.union(1,8);
    quickFind.union(6,4);
    quickFind.union(8,4);
    assertThat(quickFind.isConnected(1,4), is(true));
  }

}
