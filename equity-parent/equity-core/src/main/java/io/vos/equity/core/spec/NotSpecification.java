package io.vos.equity.core.spec;

import com.google.common.base.Preconditions;

class NotSpecification<E> extends BaseSpecification<E> {

  private final Specification<E> spec;

  NotSpecification(Specification<E> spec) {
    this.spec = Preconditions.checkNotNull(spec);
  }

  @Override public boolean isSatisfiedBy(E element)  {
    return !spec.isSatisfiedBy(element);
  }
}
