package io.vos.equity.core.spec;

public abstract class BaseSpecification<E> implements Specification<E> {

  @Override
  public abstract boolean isSatisfiedBy(E element);

  @Override
  public final Specification<E> and(Specification<E> otherSpec) {
    return new AndSpecification<E>(this, otherSpec);
  }

  @Override
  public final Specification<E> or(Specification<E> otherSpec) {
    return new OrSpecification<E>(this, otherSpec);
  }

  @Override
  public final Specification<E> not() {
    return new NotSpecification<E>(this);
  }
}
