package hs.models;

public class BeanModel<T> extends OwnedBeanModel<Object, T> {

  public BeanModel(Object instance, String propertyName) {
    super(null, instance, propertyName);
  }

}
