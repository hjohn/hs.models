package hs.models;


import hs.models.events.ItemRangeEvent;
import hs.models.events.ListenerList;

import java.util.Collection;
import java.util.List;

public interface ObservableList<T> extends List<T> {
//  public Object link(ListModel<T> model);
//  public Object unlink();
  
  public ListenerList<ItemRangeEvent> onItemsInserted();
  public ListenerList<ItemRangeEvent> afterItemsRemoved();
  public ListenerList<ItemRangeEvent> beforeItemsRemoved();
  public ListenerList<ItemRangeEvent> onItemsChanged();
  
//  public int size();
//  public T get(int index);
//  public T set(int index, T element);
//  public Object add(T element);
  public boolean addAll(T... elements);
  public Object add(Collection<? extends T> elements);
//  public void clear();
//  public int indexOf(T element);
//  public T remove(int index);
//  public boolean remove(T element);
}
