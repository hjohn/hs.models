package hs.models;

import hs.models.events.ListenerList;
import hs.models.events.ModelChangedEvent;


public interface Model<T> extends Linkable<Model<T>> {
  public T get();
  public Object set(T value);
//  public Object link(Model<T> model);
//  public <F> Object link(Model<F> model, Convertor<F, T> convertor);
//  public <F> Object link(Model<F> model, Convertor<F, T> convertor, Convertor<T, F> writer);
  public ListenerList<ModelChangedEvent<T>> onChange();
}
