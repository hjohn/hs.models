package hs.models.events;

public class ItemRangeEvent {
  private final int firstIndex;
  private final int lastIndex;

  public ItemRangeEvent(int firstIndex, int lastIndex) {
    this.firstIndex = firstIndex;
    this.lastIndex = lastIndex;
  }
  
  public int getFirstIndex() {
    return firstIndex;
  }

  public int getLastIndex() {
    return lastIndex;
  }

}
