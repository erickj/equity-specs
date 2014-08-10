package io.vos.equity.model.controller;

import io.vos.equity.model.Model;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;

@Singleton
class ModelControllerImpl<T extends Model> implements ModelController<T> {

  private Provider<EntityManager> emProvider;
  private Logger log;
  private Class<T> clazz;

  @Inject
  ModelControllerImpl(Provider<EntityManager> emProvider, Logger log, TypeLiteral<T> typeLiteral) {
    this.emProvider = emProvider;
    this.log = log;
    this.clazz = convertTypeLiteral(typeLiteral);

    log.info(String.format("Instantiated ModelController<%s>", clazz));
  }

  // This suppression is due to the manner in which I am setting up the ModelControllerImpl
  // injection. See {@link ModelControllerModule} for more. The suppression is necessary due the the
  // type erasure of <T>.
  // @see http://www.angelikalanger.com/GenericsFAQ/FAQSections/TechnicalDetails.html#Topic1
  @SuppressWarnings("unchecked")
  private Class<T> convertTypeLiteral(TypeLiteral<T> typeLiteral) {
    Preconditions.checkArgument(typeLiteral.getRawType() instanceof Class);
    return (Class<T>) typeLiteral.getRawType();
  }

  @Nullable
  @Override
  public T create() {
    T entity;
    try {
      entity = clazz.newInstance();
    } catch (InstantiationException|IllegalAccessException e) {
      log.throwing(this.getClass().getName(), "create", e);
      return null;
    }
    return entity;
  }

  @Transactional
  @Override
  public T find(Integer id) {
    return getEM().find(clazz, id);
  }

  @Transactional
  @Override
  public void destroy(T entity) {
    getEM().remove(entity);
  }

  @Transactional
  @Override
  public void persist(T entity) {
    getEM().persist(entity);
  }

  private EntityManager getEM() {
    return emProvider.get();
  }
}
