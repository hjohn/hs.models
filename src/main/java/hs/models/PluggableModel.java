package hs.models;

public class PluggableModel<T> extends PluggableOwnedModel<Object, T> {

  public PluggableModel(Accessor<T> accessor) {
    super(null, accessor);
  }

}
