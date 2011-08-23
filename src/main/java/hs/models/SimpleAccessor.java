package hs.models;

public class SimpleAccessor<T> implements Accessor<T> {
  private T value;
  
  public SimpleAccessor(T initialValue) {
    this.value = initialValue;
  }
  
  @Override
  public T read() {
    return value;
  }

  @Override
  public void write(T value) {
    this.value = value;
  }
}
