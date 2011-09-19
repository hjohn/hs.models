package hs.models;

public interface Linkable<L> {
  public Object link(L linkable);
  public Object proxy(L linkable);
  public Object unlink();
}
