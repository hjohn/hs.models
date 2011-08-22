package hs.models.events;


public class Notifier<T> {
  private final ListenerList<T> listenerList = new ListenerList<T>();
    
  public ListenerList<T> getListenerList() {
    return listenerList;
  }

  public void notifyListeners(T event) {
    listenerList.notifyListeners(event);
  }
}
