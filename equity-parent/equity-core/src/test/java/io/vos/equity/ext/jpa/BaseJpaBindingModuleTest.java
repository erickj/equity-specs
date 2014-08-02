package io.vos.equity.ext.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;

public class BaseJpaBindingModuleTest {

  private Injector injector;
  private List<DummyJpaBindingModule> moduleList;

  @Before
  public void setUp() {
    injector = null;
    moduleList = new ArrayList<>();
  }

  public void createInjector(DummyJpaBindingModule... modules) {
    injector = Guice.createInjector(modules);
    for(DummyJpaBindingModule m : modules) {
      moduleList.add(m);
    }
  }

  @Test
  public void testFilterIsBoundToFilterKey_andExposed() {
    createInjector(new DummyJpaBindingModule("dummy"));

    Object filter = injector.getInstance(moduleList.get(0).getFilterKey());
    assertTrue(filter instanceof Filter);
  }

  @Test
  public void testBindingMultipleJpaBindingModules() {
    createInjector(new DummyJpaBindingModule("dummy1"),
                   new DummyJpaBindingModule("dummy2"));

    Filter filter1 = injector.getInstance(moduleList.get(0).getFilterKey());
    Filter filter2 = injector.getInstance(moduleList.get(1).getFilterKey());

    assertNotNull(filter1);
    assertNotNull(filter2);
    assertNotEquals(filter1, filter2);
  }

  @Test
  public void testEntityManager_isPrivate() {
    createInjector(new DummyJpaBindingModule("dummy"));

    try {
      injector.getProvider(EntityManager.class);
      fail("Failed to privately bind EntityManager");
    } catch(ConfigurationException expected) {}
  }

  @Test
  public void testEntityManager_isProvided() {
    createInjector(new DummyJpaBindingModule("dummy"));

    Provider<EntityManager> emProvider = injector.getInstance(
        Key.get(DummyEntityManagerExposer.class, Names.named("dummy"))).emProvider;
    assertNotNull(emProvider);
  }

  @Test
  public void testConfigureInternal_isCalled() {
    DummyJpaBindingModule moduleUnderTest = new DummyJpaBindingModule("dummy");

    assertFalse(moduleUnderTest.configuredInternally);

    createInjector(moduleUnderTest);

    assertTrue(moduleUnderTest.configuredInternally);
  }

  class DummyJpaBindingModule extends BaseJpaBindingModule {

    boolean configuredInternally = false;
    String jpaUnitName;

    DummyJpaBindingModule(String jpaUnitName) {
      this.jpaUnitName = jpaUnitName;
    }

    @Override
    protected String getJpaUnitName() {
      return jpaUnitName;
    }

    @Override
    protected void configureInternal() {
      bind(DummyEntityManagerExposer.class)
          .annotatedWith(Names.named(jpaUnitName))
          .to(DummyEntityManagerExposer.class);
      expose(DummyEntityManagerExposer.class).annotatedWith(Names.named(jpaUnitName));

      configuredInternally = true;
    }
  }

  static class DummyEntityManagerExposer {
    final Provider<EntityManager> emProvider;

    @Inject
    DummyEntityManagerExposer(Provider<EntityManager> emProvider) {
      this.emProvider = emProvider;
    }
  }
}
