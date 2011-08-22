package hs.models.events;

import java.util.ArrayList;
import java.util.List;

public class ListenerList<E> {
  private final List<Object> listeners = new ArrayList<Object>();
    
  public void call(Listener listener) {
    listeners.add(listener);
  }

  public void call(EventListener<E> listener) {
    listeners.add(listener);
  }
  
  public void callAll(ListenerList<E> listenerList) { // TODO callAll -- maybe just call, or unregisterAll
    listeners.add(listenerList);
  }
  
  public void unregister(ListenerList<E> listenerList) {
    listeners.remove(listenerList);
  }
  
  public void unregister(Listener listener) {
    listeners.remove(listener);
  }
  
  public void unregister(EventListener<E> listener) {
    listeners.remove(listener);
  }

  @SuppressWarnings("unchecked")
  void notifyListeners(E event) {
    for(Object listener : listeners) {
      if(listener instanceof ListenerList<?>) {
        ((ListenerList<E>)listener).notifyListeners(event);
      }
      else if(listener instanceof EventListener<?>) {
        ((EventListener<E>)listener).onEvent(event);
      }
      else {
        ((Listener)listener).onEvent();
      }
    }
  }
}
