package io.vos.equity.core.spec;

public class TrueSpecification<E> extends BaseSpecification<E> {

  @Override
  public boolean isSatisfiedBy(E element) {
    return true;
  }
}
