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
package io.rockscript.engine.impl;

public class ActivityEndRequestRunnable implements Runnable {

  ActivityEndRequest activityEndRequest;
  Lock lock;
  EngineScriptExecution lockedScriptExecution;
  LocalEngine localEngine;

  public ActivityEndRequestRunnable(ActivityEndRequest activityEndRequest, Lock lock, EngineScriptExecution lockedScriptExecution, LocalEngine localEngine) {
    this.activityEndRequest = activityEndRequest;
    this.lock = lock;
    this.lockedScriptExecution = lockedScriptExecution;
    this.localEngine = localEngine;
  }

  @Override
  public void run() {
    try {
      localEngine.endActivity(
          lockedScriptExecution,
          activityEndRequest.getContinuationReference(),
          activityEndRequest.getResult());
    } finally {
      localEngine.releaseLock(lock, lockedScriptExecution);
    }
  }
}