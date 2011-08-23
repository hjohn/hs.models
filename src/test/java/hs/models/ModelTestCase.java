package hs.models;

import hs.models.events.Listener;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;

public class ModelTestCase  {

  @Test
  public void testModelLinking() {
    OwnedValueModel<ModelTestCase, Integer> m = new OwnedValueModel<ModelTestCase, Integer>(this, 2);
    OwnedValueModel<ModelTestCase, Integer> n = new OwnedValueModel<ModelTestCase, Integer>(this, 12);
    
    Assert.assertTrue(m.get() == 2);
    Assert.assertTrue(n.get() == 12);
    
    m.set(3);
    
    Assert.assertTrue(m.get() == 3);
    
    m.link(n);
    
    Assert.assertTrue(m.get() == 12);
    Assert.assertTrue(n.get() == 12);
    
    m.set(13);

    Assert.assertTrue(m.get() == 13);
    Assert.assertTrue(n.get() == 13);

    n.set(14);
    
    Assert.assertTrue(m.get() == 14);
    Assert.assertTrue(n.get() == 14);
  }
  
  private Integer valueM;
  private Integer valueN;
  @SuppressWarnings("unused")
  private Integer valueO;
  private boolean firedM;
  private boolean firedN;
  private boolean firedO;
  
  @Test
  public void testModelChangeListeners() {
    final OwnedValueModel<ModelTestCase, Integer> m = new OwnedValueModel<ModelTestCase, Integer>(this, 2);
    final OwnedValueModel<ModelTestCase, Integer> n = new OwnedValueModel<ModelTestCase, Integer>(this, 12);
    
    m.onChange().call(new Listener() {
      @Override
      public void onEvent() {
        valueM = m.get();
        firedM = true;
      }
    });

    n.onChange().call(new Listener() {
      @Override
      public void onEvent() {
        valueN = n.get();
        firedN = true;
      }
    });

    Assert.assertTrue(m.get() == 2);
    Assert.assertTrue(n.get() == 12);
    Assert.assertFalse(firedM);
    Assert.assertFalse(firedN);

    firedM = false;
    firedN = false;
    m.set(3);       // Test changing value of a model
    
    Assert.assertTrue(m.get() == 3);
    Assert.assertTrue(firedM);
    Assert.assertTrue(valueM == 3);

    firedM = false;
    firedN = false;
    m.link(n);      // Test linking a model to another model
    
    Assert.assertTrue(m.get() == 12);
    Assert.assertTrue(n.get() == 12);
    Assert.assertTrue(firedM);
    Assert.assertFalse(firedN);
    Assert.assertTrue(valueM == 12);
    
    firedM = false;
    firedN = false;
    m.set(13);      // Test changing value of a linked model

    Assert.assertTrue(m.get() == 13);
    Assert.assertTrue(n.get() == 13);
    Assert.assertTrue(firedM);
    Assert.assertTrue(firedN);
    Assert.assertTrue(valueM == 13);
    Assert.assertTrue(valueN == 13);

    firedM = false;
    firedN = false;
    n.set(14);      // Test changing value of a linked (dest) model
    
    Assert.assertTrue(m.get() == 14);
    Assert.assertTrue(n.get() == 14);
    Assert.assertTrue(firedM);
    Assert.assertTrue(firedN);
    Assert.assertTrue(valueM == 14);
    Assert.assertTrue(valueN == 14);
    
    final OwnedValueModel<ModelTestCase, Integer> o = new OwnedValueModel<ModelTestCase, Integer>(this, 15);

    o.onChange().call(new Listener() {
      @Override
      public void onEvent() {
        valueO = o.get();
        firedO = true;
      }
    });
 
    Assert.assertTrue(o.get() == 15);
    Assert.assertFalse(firedO);
    
    firedM = false;
    firedN = false;
    firedO = false;
    m.link(o);        // Test linking a previously linked model to a new model
    
    Assert.assertTrue(m.get() == 15);
    Assert.assertTrue(n.get() == 14);
    Assert.assertTrue(o.get() == 15);
    Assert.assertTrue(firedM);
    Assert.assertFalse(firedN);
    Assert.assertFalse(firedO);
    
    firedM = false;
    firedN = false;
    firedO = false;
    n.set(16);         // Test if old destination link model has no more influence on previously linked model

    Assert.assertTrue(m.get() == 15);
    Assert.assertTrue(n.get() == 16);
    Assert.assertTrue(o.get() == 15);
    Assert.assertFalse(firedM);
    Assert.assertTrue(firedN);
    Assert.assertFalse(firedO);
  }
  
  @Test
  public void testExternalModel() {
    JButton button = new JButton();
    
    OwnedBeanModel<ModelTestCase, Color> model = new OwnedBeanModel<ModelTestCase, Color>(null, button, "background");
    
    Assert.assertSame(button.getBackground(), model.get());
    
    model.set(Color.RED);
    
    Assert.assertSame(Color.RED, button.getBackground());
    
    Model<Color> parentModel = new ValueModel<Color>(Color.GREEN);
    
    model.link(parentModel);
    
    Assert.assertSame(Color.GREEN, button.getBackground());
    Assert.assertSame(Color.GREEN, model.get());
    
    model.set(Color.BLUE);
    
    Assert.assertSame(Color.BLUE, button.getBackground());
    
    parentModel.set(Color.YELLOW);
    
    Assert.assertSame(Color.YELLOW, button.getBackground());
    
    model.unlink();  
    
    Assert.assertSame(Color.YELLOW, button.getBackground());
    Assert.assertSame(Color.YELLOW, model.get()); 
  }
}
