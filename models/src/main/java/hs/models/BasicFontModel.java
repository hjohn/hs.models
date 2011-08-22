package hs.models;

import java.awt.Font;

public class BasicFontModel extends BasicOwnedFontModel<Object> {

  public BasicFontModel(Accessor<Font> accessor) {
    super(null, accessor);
  }

}
