package io.vos.equity.core.spec;

public interface Specification<E> {

  boolean isSatisfiedBy(E element);

  Specification<E> and(Specification<E> otherSpec);

  Specification<E> or(Specification<E> otherSpec);

  Specification<E> not();
}
