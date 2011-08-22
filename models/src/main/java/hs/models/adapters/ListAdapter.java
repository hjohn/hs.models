package hs.models.adapters;

import java.util.List;

public class ListAdapter<T> extends OwnedListAdapter<Object, T> {

  public ListAdapter(List<T> delegate) {
    super(null, delegate);
  }
}
