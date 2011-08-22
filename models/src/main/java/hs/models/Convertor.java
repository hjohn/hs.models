package hs.models;

public interface Convertor<F, T> {
  public T convert(F value);
}
