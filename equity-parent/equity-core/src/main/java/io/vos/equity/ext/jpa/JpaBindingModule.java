package io.vos.equity.ext.jpa;

import com.google.inject.Key;
import com.google.inject.Module;

import javax.servlet.Filter;

public interface JpaBindingModule extends Module {

  Key<Filter> getFilterKey();

}
