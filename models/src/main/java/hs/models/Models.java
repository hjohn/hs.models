package hs.models;

import java.util.ArrayList;

public class Models {
  public static <T> ListModel<T> createArrayList() {
    return new BasicListModel<T>(new ArrayList<T>());
  }
}
