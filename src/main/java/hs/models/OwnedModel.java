package hs.models;

import hs.models.events.ListenerList;
import hs.models.events.ModelChangedEvent;


public interface OwnedModel<O, T> extends Model<T>, OwnedLinkable<O, Model<T>> {
  @Override
  public O set(T value);
//  public O link(Model<T> model);
//  public <F> O link(Model<F> model, Convertor<F, T> convertor);
//  public <F> O link(Model<F> model, Convertor<F, T> convertor, Convertor<T, F> writer);
  @Override
  public ListenerList<ModelChangedEvent<T>> onChange();
}
