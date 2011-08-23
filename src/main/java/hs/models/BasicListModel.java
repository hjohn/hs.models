package hs.models;

import java.util.List;

public class BasicListModel<T> extends BasicOwnedListModel<Object, T> {

  public BasicListModel(List<T> delegate) {
    super(null, delegate);
  }

}
