package io.vos.equity.ext.jpa;

import com.google.inject.Key;
import com.google.inject.PrivateModule;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;

import javax.servlet.Filter;

public abstract class BaseJpaBindingModule extends PrivateModule implements JpaBindingModule {

  private Key<Filter> filterKey;

  /**
   * Subclasses must override this method to provide the JPA unit name.
   */
  protected abstract String getJpaUnitName();

  /**
   * Subclasses may override this method for additional configuration.
   */
  protected void configureInternal() {}

  @Override
  public final Key<Filter> getFilterKey() {
    if (filterKey == null) {
      filterKey = Key.get(Filter.class, Names.named(getJpaUnitName()));
    }
    return filterKey;
  }

  @Override
  public final void configure() {
    // @see https://github.com/google/guice/wiki/JPA
    install(new JpaPersistModule(getJpaUnitName()));

    // @see https://groups.google.com/forum/#!topic/google-guice/2VK-bdsnjZc/discussion
    bind(getFilterKey()).to(PersistFilter.class);
    expose(getFilterKey());

    configureInternal();
  }
}
