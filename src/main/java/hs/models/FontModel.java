package hs.models;

import java.awt.Font;

public interface FontModel extends Model<Font> {
  public Model<Float> size();
  public Model<Integer> style();
}
