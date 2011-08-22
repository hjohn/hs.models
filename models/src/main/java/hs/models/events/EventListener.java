package hs.models.events;

public interface EventListener<T> {
  public void onEvent(T event);
}
