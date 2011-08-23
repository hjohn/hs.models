package hs.models;

import java.awt.Font;

public interface OwnedFontModel<O> extends FontModel, OwnedModel<O, Font> {
  @Override
  public OwnedModel<O, Float> size();
  @Override
  public OwnedModel<O, Integer> style();
}
