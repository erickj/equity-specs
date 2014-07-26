package io.vos.equity.webapp.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(APPLICATION_JSON)
@Singleton
class JsonMessageBodyWriter implements MessageBodyWriter<Object> {

  private static final String UTF_8 = "UTF-8";

  private final Gson gson;

  @Inject
  JsonMessageBodyWriter(Gson gson) {
    this.gson = Preconditions.checkNotNull(gson);
  }

  @Override
  public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return -1L;
  }

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return true;
  }

  @Override
  public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> headers, OutputStream entityStream)
      throws IOException {
    OutputStreamWriter streamWriter = new OutputStreamWriter(entityStream, UTF_8);
    try {
      if (type.equals(genericType)) {
        gson.toJson(object, streamWriter);
      } else {
        gson.toJson(object, genericType, streamWriter);
      }
    } finally {
      streamWriter.close();
    }
  }
}
