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
package io.rockscript.handlers;

import java.util.List;

import com.google.inject.Inject;
import io.rockscript.engine.EngineImpl;
import io.rockscript.engine.EventJson;
import io.rockscript.netty.router.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Get("/events")
public class EventsHandler implements RequestHandler {

  static Logger log = LoggerFactory.getLogger(EventsHandler.class);

  @Inject
  EngineImpl engine;

  @Override
  public void handle(Request request, Response response) {
    List<EventJson> events = engine
      .getServiceLocator()
      .getEventStore()
      .getEvents();

    response.bodyJson(events);
    response.statusOk();
    response.send();
  }
}