/*
 * Copyright (c) 2017, RockScript.io. All rights reserved.
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
package io.rockscript.activity.test;

import io.rockscript.engine.EngineException;
import io.rockscript.engine.impl.Execution;

public class TestError {

  String message;
  int line;
  String scriptId;

  public TestError(Throwable t) {
    this.message = t.getMessage();
    if (t instanceof EngineException) {
      EngineException engineException = (EngineException) t;
      Execution execution = engineException.getExecution();
      if (execution!=null) {
        this.line = execution.getElement().getLocation().getLine();
        this.scriptId = execution.getEngineScript().getScript().getId();
      }
    }
  }

  public TestError(String message, String scriptId, int line) {
    this.message = message;
    this.scriptId = scriptId;
    this.line = line;
  }

  public String getMessage() {
    return message;
  }

  public String getScriptId() {
    return scriptId;
  }

  public int getLine() {
    return line;
  }

  @Override
  public String toString() {
    return "[scriptId:" + scriptId +",line:" + line +"] "+message;
  }
}
