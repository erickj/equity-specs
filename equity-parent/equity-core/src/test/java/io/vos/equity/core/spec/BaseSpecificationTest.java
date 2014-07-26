package io.vos.equity.core.spec;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BaseSpecificationTest {

  private static final Object SATISFYING_ELEMENT = new Object();
  private static final Object UNSATISFYING_ELEMENT = new Object();

  private static final Specification<Object> TRUE_SPEC = new TrueSpecification<>();
  private static final Specification<Object> FALSE_SPEC = new FalseSpecification<>();

  @Test
  public void testIsSatisfiedBy() {
    Specification<Object> spec = new FakeSpecification<>();

    assertTrue(spec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertFalse(spec.isSatisfiedBy(UNSATISFYING_ELEMENT));
  }

  @Test
  public void testAnd() {
    Specification<Object> aSpec = new FakeSpecification<>();
    Specification<Object> aSpecAndTrueSpec = aSpec.and(TRUE_SPEC);

    assertTrue(aSpecAndTrueSpec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertFalse(aSpecAndTrueSpec.isSatisfiedBy(UNSATISFYING_ELEMENT));

    Specification<Object> aSpecAndFalseSpec = aSpec.and(FALSE_SPEC);

    assertFalse(aSpecAndFalseSpec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertFalse(aSpecAndFalseSpec.isSatisfiedBy(UNSATISFYING_ELEMENT));
  }

  @Test
  public void testOr() {
    Specification<Object> aSpec = new FakeSpecification<>();
    Specification<Object> aSpecOrTrueSpec = aSpec.or(TRUE_SPEC);

    assertTrue(aSpecOrTrueSpec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertTrue(aSpecOrTrueSpec.isSatisfiedBy(UNSATISFYING_ELEMENT));

    Specification<Object> aSpecOrFalseSpec = aSpec.or(FALSE_SPEC);

    assertTrue(aSpecOrFalseSpec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertFalse(aSpecOrFalseSpec.isSatisfiedBy(UNSATISFYING_ELEMENT));
  }

  @Test
  public void testNot() {
    Specification<Object> notASpec = new FakeSpecification<>().not();

    assertFalse(notASpec.isSatisfiedBy(SATISFYING_ELEMENT));
    assertTrue(notASpec.isSatisfiedBy(UNSATISFYING_ELEMENT));
  }

  private class FakeSpecification<E> extends BaseSpecification<E> {
    @Override public boolean isSatisfiedBy(E element) {
      return element == SATISFYING_ELEMENT;
    }
  }
}
