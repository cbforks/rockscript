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
package io.rockscript.cqrs;

import io.rockscript.netty.router.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.rockscript.util.Maps.entry;
import static io.rockscript.util.Maps.hashMap;

@Post("/command")
public class CommandHandler implements RequestHandler {

  static Logger log = LoggerFactory.getLogger(CommandHandler.class);

  @Override
  public void handle(AsyncHttpRequest request, AsyncHttpResponse response, Context context) {
    Command<?> command = request.getBodyJson(Command.class);
    CommandExecutorService commandExecutorService = context.get(CommandExecutorService.class);

    try {
      Response commandResponse = commandExecutorService.execute(command);
      response.bodyJson(commandResponse);
      response.status(commandResponse.getStatus());
      response.send();

    } catch (Exception e) {
      log.debug("Exception while executing command "+command+": "+e.getMessage(), e);
      response.bodyJson(hashMap(entry("message", "Error: " + e.getMessage())));
      response.status(500);
      response.send();
    }
  }
}
