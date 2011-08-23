package hs.models;

public interface OwnedLinkable<O, L> extends Linkable<L> {
  @Override
  public O link(L linkable);
  @Override
  public O unlink();
}
