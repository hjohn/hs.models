package hs.models;

public class ValueModel<T> extends OwnedValueModel<Object, T> {

  public ValueModel(T initialValue) {
    super(null, initialValue);
  }

  public ValueModel() {
    super(null);
  }

}
