package hs.models;

public interface Accessor<T> {
  public T read();
  public void write(T value);
}
