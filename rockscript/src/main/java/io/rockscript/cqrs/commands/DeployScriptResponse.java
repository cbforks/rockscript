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
package io.rockscript.cqrs.commands;

import io.rockscript.engine.ParseError;
import io.rockscript.engine.Script;

import java.util.List;
import java.util.stream.Collectors;

/** AsyncHttpResponse from the DeployScriptCommand.
 *
 * EngineDeployScriptResponse are serializable with Gson. */
public class DeployScriptResponse extends Script {

  protected List<ParseError> errors;

  /** for gson serialization */
  DeployScriptResponse() {
  }

  public DeployScriptResponse(Script script, List<ParseError> errors) {
    super(script);
    this.errors = errors;
  }

  public List<ParseError> getErrors() {
    return errors;
  }

  public void setErrors(List<ParseError> errors) {
    this.errors = errors;
  }

  public boolean hasErrors() {
    return errors!=null && !errors.isEmpty();
  }

  public DeployScriptResponse throwIfErrors() {
    if (hasErrors()) {
      String errorPerLine = errors.stream()
        .map(e->e.toString())
        .collect(Collectors.joining("\n"));
      throw new RuntimeException("Deploy errors: \n" + errorPerLine);
    }
    return this;
  }
}
