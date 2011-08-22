package hs.models;

import hs.models.events.Listener;

import java.awt.Font;

public class BasicOwnedFontModel<O> extends PluggableOwnedModel<O, Font> implements OwnedFontModel<O> {
  private final OwnedValueModel<O, Float> size = new OwnedValueModel<O, Float>(getOwner());
  private final OwnedValueModel<O, Integer> style = new OwnedValueModel<O, Integer>(getOwner());

  public BasicOwnedFontModel(O owner, Accessor<Font> accessor) {
    super(owner, accessor);

    /*
     * Model -> SuperModel 
     */
    
    size.onChange().call(new Listener() {
      @Override
      public void onEvent() {
        if(get().getSize2D() != size.get()) {
          set(get().deriveFont(size.get()));
        }
      }
    });
    
    style.onChange().call(new Listener() {
      @Override
      public void onEvent() {
        if(get().getStyle() != style.get()) {
          set(get().deriveFont(style.get()));
        }
      }
    });
    
    /*
     * SuperModel -> Model
     */
    
    onChange().call(new Listener() {
      @Override
      public void onEvent() {
        size.set(get().getSize2D());
        style.set(get().getStyle());
      }
    });
  }
  
  @Override
  public OwnedModel<O, Float> size() {
    return size;
  }

  @Override
  public OwnedModel<O, Integer> style() {
    return style;
  }
}
