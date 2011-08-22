package hs.models.events;

public class ModelChangedEvent<T> {
  private final T oldValue;
  private final T newValue;

  public ModelChangedEvent(T oldValue, T newValue) {
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public T getOldValue() {
    return oldValue;
  }

  public T getNewValue() {
    return newValue;
  }
}
