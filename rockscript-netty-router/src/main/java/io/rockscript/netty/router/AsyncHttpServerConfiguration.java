/*
 * Copyright ©2017, RockScript.io. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rockscript.netty.router;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.router.Router;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class AsyncHttpServerConfiguration {

  protected Router<Class<?>> router = new Router<>();
  protected int port = 3652;  // The default RockScript port number
  protected List<Interceptor> interceptors;
  protected MapContext context = new MapContext();
  protected JsonHandler jsonHandler;
  protected DefaultHttpHeaders defaultResponseHeaders;

  public AsyncHttpServer build() {
    return new AsyncHttpServer(this);
  }

  public AsyncHttpServerConfiguration component(AsyncHttpServerComponent component) {
    component.configure(this);
    return this;
  }

  public Context getContext() {
    return context;
  }

  public AsyncHttpServerConfiguration context(Object key, Object value) {
    this.context.put(key, value);
    return this;
  }

  public Router<Class<?>> getRouter() {
    return router;
  }

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public AsyncHttpServerConfiguration port(int port) {
    this.port = port;
    return this;
  }

  public List<Interceptor> getInterceptors() {
    return this.interceptors;
  }

  public void setInterceptors(List<Interceptor> interceptors) {
    this.interceptors = interceptors;
  }

  public JsonHandler getJsonHandler() {
    return jsonHandler;
  }

  public void setJsonHandler(JsonHandler jsonHandler) {
    this.jsonHandler = jsonHandler;
  }

  public AsyncHttpServerConfiguration jsonHandler(JsonHandler jsonHandler) {
    this.jsonHandler = jsonHandler;
    return this;
  }

  public AsyncHttpServerConfiguration interceptor(Interceptor interceptor) {
    if (interceptors==null) {
      interceptors = new ArrayList<>();
    }
    this.interceptors.add(interceptor);
    return this;
  }

  public AsyncHttpServerConfiguration scan(Class... classes) {
    if (classes!=null) {
      for (Class clazz : classes) {
        scan(clazz);
      }
    }
    return this;
  }

  public AsyncHttpServerConfiguration scan(Class<?> clazz) {
    Gets repeatableAnnotation = clazz.getDeclaredAnnotation(Gets.class);
    Get[] annotations = repeatableAnnotation!=null ? repeatableAnnotation.value() : null;
    for (Get annotation : list(clazz.getDeclaredAnnotation(Get.class), annotations)) {
      GET(annotation.value(), clazz);
    }

    Puts repeatablePuts = clazz.getDeclaredAnnotation(Puts.class);
    Put[] puts = repeatablePuts!=null ? repeatablePuts.value() : null;
    for (Put annotation : list(clazz.getDeclaredAnnotation(Put.class), puts)) {
      PUT(annotation.value(), clazz);
    }

    Posts repeatablePosts = clazz.getDeclaredAnnotation(Posts.class);
    Post[] posts = repeatablePosts!=null ? repeatablePosts.value() : null;
    for (Post annotation : list(clazz.getDeclaredAnnotation(Post.class), posts)) {
      POST(annotation.value(), clazz);
    }

    Deletes repeatableDeletes = clazz.getDeclaredAnnotation(Deletes.class);
    Delete[] deletes = repeatableDeletes!=null ? repeatableDeletes.value() : null;
    for (Delete annotation : list(clazz.getDeclaredAnnotation(Delete.class), deletes)) {
      DELETE(annotation.value(), clazz);
    }

    return this;
  }

  private <T> List<T> list(T annotation, T[] annotations) {
    List<T> list = new ArrayList<>();
    if (annotation!=null) {
      list.add(annotation);
    }
    if (annotations!=null) {
      for (T e : annotations) {
        list.add(e);
      }
    }
    return list;
  }

  public AsyncHttpServerConfiguration DELETE(String path, Class<?> target) {
    router.DELETE(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration GET(String path, Class<?> target) {
    router.GET(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration HEAD(String path, Class<?> target) {
    router.HEAD(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration OPTIONS(String path, Class<?> target) {
    router.OPTIONS(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PATCH(String path, Class<?> target) {
    router.PATCH(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration POST(String path, Class<?> target) {
    router.POST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PUT(String path, Class<?> target) {
    router.PUT(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration TRACE(String path, Class<?> target) {
    router.TRACE(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration DELETE_FIRST(String path, Class<?> target) {
    router.DELETE_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration GET_FIRST(String path, Class<?> target) {
    router.GET_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration HEAD_FIRST(String path, Class<?> target) {
    router.HEAD_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration OPTIONS_FIRST(String path, Class<?> target) {
    router.OPTIONS_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PATCH_FIRST(String path, Class<?> target) {
    router.PATCH_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration POST_FIRST(String path, Class<?> target) {
    router.POST_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PUT_FIRST(String path, Class<?> target) {
    router.PUT_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration TRACE_FIRST(String path, Class<?> target) {
    router.TRACE_FIRST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration DELETE_LAST(String path, Class<?> target) {
    router.DELETE_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration GET_LAST(String path, Class<?> target) {
    router.GET_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration HEAD_LAST(String path, Class<?> target) {
    router.HEAD_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration OPTIONS_LAST(String path, Class<?> target) {
    router.OPTIONS_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PATCH_LAST(String path, Class<?> target) {
    router.PATCH_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration POST_LAST(String path, Class<?> target) {
    router.POST_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration PUT_LAST(String path, Class<?> target) {
    router.PUT_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration TRACE_LAST(String path, Class<?> target) {
    router.TRACE_LAST(path, target);
    return this;
  }

  public AsyncHttpServerConfiguration notFound(Class<?> target) {
    router.notFound(target);
    return this;
  }

  public AsyncHttpServerConfiguration defaultNotFoundHandler() {
    router.notFound(DefaultNotFoundHandler.class);
    return this;
  }

  public AsyncHttpServerConfiguration defaultResponseHeader(String name, String value) {
    if (defaultResponseHeaders==null) {
      defaultResponseHeaders = new DefaultHttpHeaders();
    }
    defaultResponseHeaders.add(name, value);
    return this;
  }

  public DefaultHttpHeaders getDefaultResponseHeaders() {
    return defaultResponseHeaders;
  }

  /** Load EventHandlerModule's from the classpath using ServiceLoader,
   * to add a module, add the classname in a resource file called
   * META-INF/services/io.rockscript.netty.router.AsyncHttpServerModule */
  public AsyncHttpServerConfiguration loadAsyncHttpServerModulesFromClassPath() {
    // Load pluggable requesthandler dynamically
    ServiceLoader<AsyncHttpServerModule> requestHandlers = ServiceLoader.load(AsyncHttpServerModule.class);
    for (AsyncHttpServerModule asyncHttpServerModule : requestHandlers) {
      asyncHttpServerModule.register(this);
    }
    return this;
  }
}
