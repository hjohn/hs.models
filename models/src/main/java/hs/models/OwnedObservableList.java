package hs.models;


import hs.models.events.ItemRangeEvent;
import hs.models.events.ListenerList;

import java.util.Collection;

public interface OwnedObservableList<O, T> extends ObservableList<T> {
  @Override
  public ListenerList<ItemRangeEvent> onItemsInserted();
  @Override
  public ListenerList<ItemRangeEvent> afterItemsRemoved();
  @Override
  public ListenerList<ItemRangeEvent> beforeItemsRemoved();
  @Override
  public ListenerList<ItemRangeEvent> onItemsChanged();
  
//  public O add(T element);
//  public O addAll(T... elements);
//  public O addAll(Collection<T> elements);
  @Override
  public O add(Collection<? extends T> elements);
}
