package io.vos.equity.core.spec;

import com.google.common.base.Preconditions;

class OrSpecification<E> extends BaseSpecification<E> {

  private final Specification<E> aSpec;
  private final Specification<E> bSpec;

  OrSpecification(Specification<E> aSpec, Specification<E> bSpec) {
    this.aSpec = Preconditions.checkNotNull(aSpec);
    this.bSpec = Preconditions.checkNotNull(bSpec);
  }

  @Override public boolean isSatisfiedBy(E element)  {
    return aSpec.isSatisfiedBy(element) || bSpec.isSatisfiedBy(element);
  }
}
