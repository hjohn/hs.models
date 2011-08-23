package hs.models;

public class OwnedValueModel<O, T> extends PluggableOwnedModel<O, T> {

  public OwnedValueModel(O owner, T initialValue) {
    super(owner, new SimpleAccessor<T>(initialValue));
  }
  
  public OwnedValueModel(O owner) {
    this(owner, null);
  }
}
