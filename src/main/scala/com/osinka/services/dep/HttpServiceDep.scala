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

import org.osgi.service.http.HttpService

/**
 * OSGi HttpService dependency
 *
 * Mix in this trait to your component class and refer it in DS's XML descriptor
 * like
    <reference
        name="HTTP service"
        interface="org.osgi.service.http.HttpService"
        bind="bindHttpService"
        unbind="unbindHttpService"
        cardinality="0..1"
        policy="dynamic"/>
 */
class HttpServiceDep {
    protected var httpService: Option[HttpService] = None

    def bindHttpService(service: HttpService): Unit = httpService = {
        assert(service != null)
        Some(service)
    }

    def unbindHttpService(service: HttpService): Unit = {
        assert(service != null)
        httpService = None
    }
}
