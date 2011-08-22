package hs.models;

public interface OwnedListModel<O, T> extends OwnedObservableList<O, T>, OwnedLinkable<O, ObservableList<T>>, ListModel<T> {
}
