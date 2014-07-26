package io.vos.equity.core.spec;

public class FalseSpecification<E> extends BaseSpecification<E> {

  @Override
  public boolean isSatisfiedBy(E element) {
    return false;
  }
}
