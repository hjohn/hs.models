package hs.models;

import hs.models.events.EventListener;
import hs.models.events.ListenerList;
import hs.models.events.ModelChangedEvent;
import hs.models.events.Notifier;


public class PluggableOwnedModel<O, T> implements OwnedModel<O, T> {
  private final O owner;
  private final Accessor<T> accessor;
  private final Notifier<ModelChangedEvent<T>> changeNotifier;
  
  private Model<T> linkedModel;
  
  public PluggableOwnedModel(O owner, final Accessor<T> accessor) {
    this.owner = owner;
    this.accessor = accessor;
    
    changeNotifier = new Notifier<ModelChangedEvent<T>>();
    
    onChange().call(new EventListener<ModelChangedEvent<T>>() {
      @Override
      public void onEvent(ModelChangedEvent<T> event) {
        accessor.write(event.getNewValue());
      }
    });
  }
  
  protected O getOwner() {
    return owner;
  }
  
  protected Model<T> getLinkedModel() {
    return linkedModel;
  }
    
  @Override
  public T get() {
    if(linkedModel == null) {
      return accessor.read();
    }
    else {
      return linkedModel.get();
    }
  }
  
  @Override
  public final O set(T value) {
    if(linkedModel == null) {
      T oldValue = get();
      
      if(oldValue != value && (oldValue == null || !oldValue.equals(value))) {
        // System.err.println("MODEL (" + owner + "): " + oldValue + " -> " + value);
        modelChanged(oldValue, value);
      }
    }
    else {
      linkedModel.set(value);
    }
    return owner;
  }
  
  @Override
  public O link(Model<T> model) {
    T oldValue = get();
    unlink();
    linkedModel = model;
    modelChanged(oldValue, get());
    model.onChange().callAll(onChange());
    return owner;
  }
  
  @Override
  public O unlink() {
    if(linkedModel != null) {
      linkedModel.onChange().unregister(onChange());
      linkedModel = null;
    }
    
    return owner;
  }
  
  @Override
  public ListenerList<ModelChangedEvent<T>> onChange() {
    return changeNotifier.getListenerList();
  }

  protected void modelChanged(T oldValue, T newValue) {
    changeNotifier.notifyListeners(new ModelChangedEvent<T>(oldValue, newValue));
  }
}
