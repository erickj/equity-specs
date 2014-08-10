package io.vos.equity.model.controller;

import io.vos.equity.model.Model;

import javax.annotation.Nullable;

public interface ModelController<E extends Model> {

  /**
   * Returns a new managed instance of {@code E}, the model will be committed to the datastore
   * automatically at the end of the transaction.
   * @see <a href="http://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html_single/#d0e1030">The hibernate JPA documentation</a>
   *
   * @return E
   */
  @Nullable
  E create();

  /**
   * Finds the model with the given primary key from the datastore.
   *
   * @param primaryKey - Number of primary Key
   * @return E
   */
  @Nullable
  E find(Integer primaryKey);

  /**
   * Removes the model from the datastore.
   *
   * @param entity - The entity to destroy.
   */
  void destroy(E entity);

  /**
   * Marks an entity to be stored in the datasource.
   *
   * @param entity - The entity to persist.
   */
  void persist(E entity);
}
