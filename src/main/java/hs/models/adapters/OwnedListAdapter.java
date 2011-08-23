package hs.models.adapters;

import hs.models.OwnedObservableList;
import hs.models.events.ItemRangeEvent;
import hs.models.events.ListenerList;
import hs.models.events.Notifier;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OwnedListAdapter<O, T> implements OwnedObservableList<O, T> {
  private final O owner;

  private final List<T> delegate;
  
  public OwnedListAdapter(O owner, List<T> delegate) {
    this.owner = owner;
    this.delegate = delegate;
    this.itemsInsertedNotifier = new Notifier<ItemRangeEvent>();
    this.afterItemsRemovedNotifier = new Notifier<ItemRangeEvent>();
    this.beforeItemsRemovedNotifier = new Notifier<ItemRangeEvent>();
    this.itemsChangedNotifier = new Notifier<ItemRangeEvent>();
  }

  @Override
  public void add(int index, T e) {
    delegate.add(index, e);
    itemsInsertedNotifier.notifyListeners(new ItemRangeEvent(index, index));
  }

  @Override
  public O add(Collection<? extends T> c) {
    addAll(c);
    return owner;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    boolean result = false;

    if(!c.isEmpty()) {
      int startIndex = delegate.size();
      result = delegate.addAll(c);
      int index = delegate.size() - 1;
      itemsInsertedNotifier.notifyListeners(new ItemRangeEvent(startIndex, index));
    }

    return result;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    boolean result = false;

    if(!c.isEmpty()) {
      int startIndex = index;
      result = delegate.addAll(index, c);
      int endIndex = index + c.size() - 1;
      itemsInsertedNotifier.notifyListeners(new ItemRangeEvent(startIndex, endIndex));
    }

    return result;
  }
  
  @Override
  public synchronized boolean add(T e) {
    delegate.add(e);
    int index = delegate.size() - 1;
    itemsInsertedNotifier.notifyListeners(new ItemRangeEvent(index, index));
    
    return true;
  }

  @Override
  public synchronized boolean addAll(T... e) {
    if(e.length > 0) {
      int startIndex = delegate.size();
      for(T t : e) {
        delegate.add(t);
      }
      int index = delegate.size() - 1;
      itemsInsertedNotifier.notifyListeners(new ItemRangeEvent(startIndex, index));
      return true;
    }
    
    return false;
  }
  
  @Override
  public synchronized void clear() {
    int size = delegate.size();
    
    if(size > 0) {
      beforeItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(0, size - 1));
      delegate.clear();
      afterItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(0, size - 1));
    }
  }

  @Override
  public T get(int index) {
    return delegate.get(index);
  }
  
  @Override
  public int indexOf(Object e) {
    return delegate.indexOf(e);
  }

  @Override
  public T remove(int index) {
    beforeItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(index, index));
    T oldElement = delegate.remove(index);
    afterItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(index, index));
    return oldElement; 
  }

  @Override
  public synchronized boolean remove(Object e) {
    int index = delegate.indexOf(e);
    
    if(index >= 0) {
      beforeItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(index, index));
      delegate.remove(index);
      afterItemsRemovedNotifier.notifyListeners(new ItemRangeEvent(index, index));
      return true;
    }
    return false;
  }

  @Override
  public T set(int index, T e) {
    T oldElement = delegate.set(index, e);
    itemsChangedNotifier.notifyListeners(new ItemRangeEvent(index, index));
    return oldElement;
  }
  
  @Override
  public int size() {
    return delegate.size();
  }

  private Notifier<ItemRangeEvent> itemsInsertedNotifier;
  
  @Override
  public ListenerList<ItemRangeEvent> onItemsInserted() {
    return itemsInsertedNotifier.getListenerList();
  }
  
  private Notifier<ItemRangeEvent> afterItemsRemovedNotifier;
  
  @Override
  public ListenerList<ItemRangeEvent> afterItemsRemoved() {
    return afterItemsRemovedNotifier.getListenerList();
  }

  private Notifier<ItemRangeEvent> beforeItemsRemovedNotifier;
  
  @Override
  public ListenerList<ItemRangeEvent> beforeItemsRemoved() {
    return beforeItemsRemovedNotifier.getListenerList();
  }

  private Notifier<ItemRangeEvent> itemsChangedNotifier;
  
  @Override
  public ListenerList<ItemRangeEvent> onItemsChanged() {
    return itemsChangedNotifier.getListenerList();
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<T> iterator() {
    return Collections.unmodifiableList(delegate).iterator();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<T> listIterator() {
    return delegate.listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public <X> X[] toArray(X[] a) {
    throw new UnsupportedOperationException();
  }
}
