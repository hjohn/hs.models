package hs.models;

import org.junit.Assert;
import org.junit.Test;

import hs.models.events.EventListener;
import hs.models.events.ItemRangeEvent;

public class ListModelTestCase {

  private ItemRangeEvent e1;
  private ItemRangeEvent e2;
  
  @Test
  public void testModelLinking() {
    ListModel<String> m1 = Models.createArrayList();
    ListModel<String> m2 = Models.createArrayList();
    
    m1.onItemsInserted().call(new EventListener<ItemRangeEvent>() {
      @Override
      public void onEvent(ItemRangeEvent event) {
        e1 = event;
      }
    });
    
    m2.onItemsInserted().call(new EventListener<ItemRangeEvent>() {
      @Override
      public void onEvent(ItemRangeEvent event) {
        e2 = event;
      }
    });

    e1 = null;
    e2 = null;
    m1.add("A");
    
    Assert.assertTrue(e1 != null);
    Assert.assertTrue(e2 == null);
    Assert.assertTrue(e1.getFirstIndex() == 0 && e1.getLastIndex() == 0);

    e1 = null;
    e2 = null;
    m1.add("B");

    Assert.assertTrue(e1 != null);
    Assert.assertTrue(e2 == null);
    Assert.assertTrue(e1.getFirstIndex() == 1 && e1.getLastIndex() == 1);
    
    e1 = null;
    e2 = null;
    m2.link(m1);

    // TODO Check for structural change
    
    e1 = null;
    e2 = null;
    m1.add("C");

    Assert.assertTrue(e1 != null);
    Assert.assertTrue(e2 != null);
    Assert.assertTrue(e1.getFirstIndex() == 2 && e1.getLastIndex() == 2);
    Assert.assertTrue(e2.getFirstIndex() == 2 && e2.getLastIndex() == 2);
    
    e1 = null;
    e2 = null;
    m2.unlink();
    m1.add("D");
    
    Assert.assertTrue(e1 != null);
    Assert.assertTrue(e2 == null);
    Assert.assertTrue(e1.getFirstIndex() == 3 && e1.getLastIndex() == 3);
  }
}
