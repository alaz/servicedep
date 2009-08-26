/**
 * Copyright (C) 2009 alaz <azarov@osinka.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.osinka.services.dep

import org.osgi.service.log.LogService

/**
 * LogService OSGi service dependency
 *
 * Mix in this trait to your component class and refer it in DS's XML descriptor
 * like
    <reference
        name="Log service"
        interface="org.osgi.service.log.LogService"
        bind="bindLogService"
        unbind="unbindLogService"
        cardinality="0..1"
        policy="dynamic"/>
 */
trait LogServiceDep {
    protected var logService: Option[LogService] = None

    def bindLogService(ls: LogService): Unit = {
        assert(ls != null)
        logService = Some(ls)
    }

    def unbindLogService(ls: LogService): Unit = {
        assert(ls != null)
        logService = None
    }

    /**
     * API for descendants
     */
    // TODO: LogServiceHolder to extend Component, check componentContext in runtime, use ServiceReference to log messages
    protected def log(level: Int, msg: String): Unit = logService.foreach{ _.log(level, msg) }
}
