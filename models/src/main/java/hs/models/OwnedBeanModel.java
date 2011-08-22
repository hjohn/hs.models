package hs.models;

public class OwnedBeanModel<O, T> extends PluggableOwnedModel<O, T> {
  public OwnedBeanModel(O owner, Object instance, String propertyName) {
    super(owner, new BeanAccessor<T>(instance, propertyName));
  }
}
