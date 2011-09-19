package hs.models;

import hs.models.adapters.OwnedListAdapter;
import hs.models.events.ItemRangeEvent;
import hs.models.events.ListenerList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BasicOwnedListModel<O, T> implements OwnedListModel<O, T> {
  private final O owner;
  private final OwnedObservableList<O, T> internalList;

  private ObservableList<T> delegate;

  public BasicOwnedListModel(O owner, List<T> delegate) {
    this.owner = owner;
    internalList = new OwnedListAdapter<O, T>(owner, delegate);
    this.delegate = internalList;
  }

  @Override
  public O link(ObservableList<T> linkable) {
    unlink();
    
    delegate = linkable;
    delegate.onItemsInserted().callAll(onItemsInserted());
    delegate.afterItemsRemoved().callAll(afterItemsRemoved());
    delegate.beforeItemsRemoved().callAll(beforeItemsRemoved());
    delegate.onItemsChanged().callAll(onItemsChanged());
    
    return owner;
  }
  
  @Override
  public Object proxy(ObservableList<T> linkable) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public O unlink() {
    if(delegate != internalList) {
      delegate.onItemsInserted().unregister(onItemsInserted());
      delegate.afterItemsRemoved().unregister(afterItemsRemoved());
      delegate.beforeItemsRemoved().unregister(beforeItemsRemoved());
      delegate.onItemsChanged().unregister(onItemsChanged());
      delegate = internalList;
    }
    
    return owner;
  }

  @Override
  public ListenerList<ItemRangeEvent> onItemsChanged() {
    return internalList.onItemsChanged();
  }

  @Override
  public ListenerList<ItemRangeEvent> onItemsInserted() {
    return internalList.onItemsInserted();
  }

  @Override
  public ListenerList<ItemRangeEvent> afterItemsRemoved() {
    return internalList.afterItemsRemoved();
  }

  @Override
  public ListenerList<ItemRangeEvent> beforeItemsRemoved() {
    return internalList.beforeItemsRemoved();
  }

  /*
   * Delegate Methods  TODO investigate if it is worthwhile making ObservableList ownable at all given the low amount of methods that support method chaining
   */
  
  @Override
  public boolean addAll(Collection<? extends T> c) {
    return delegate.addAll(c);
  }

  @Override
  public O add(Collection<? extends T> c) {
    delegate.add(c);
    return owner;
  }
  
  @Override
  public void add(int index, T element) {
    delegate.add(index, element);
  }

  @Override
  public boolean add(T e) {
    return delegate.add(e);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return delegate.addAll(index, c);
  }

  @Override
  public boolean addAll(T... elements) {
    return delegate.addAll(elements);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return delegate.containsAll(c);
  }
  
  @Override
  public T get(int index) {
    return delegate.get(index);
  }

  @Override
  public int indexOf(Object o) {
    return delegate.indexOf(o);
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public Iterator<T> iterator() {
    return delegate.iterator();
  }

  @Override
  public int lastIndexOf(Object o) {
    return delegate.lastIndexOf(o);
  }

  @Override
  public ListIterator<T> listIterator() {
    return delegate.listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return delegate.listIterator(index);
  }

  @Override
  public T remove(int index) {
    return delegate.remove(index);
  }

  @Override
  public boolean remove(Object o) {
    return delegate.remove(o);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  @Override
  public T set(int index, T element) {
    return delegate.set(index, element);
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return delegate.subList(fromIndex, toIndex);
  }

  @Override
  public Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public <X> X[] toArray(X[] a) {
    return delegate.toArray(a);
  }
}
