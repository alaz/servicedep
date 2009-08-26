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

import org.osgi.service.cm.ConfigurationAdmin

/**
 * ConfigAdmin OSGi service dependency
 *
 * Mix in this trait to your component class and refer it in DS's XML descriptor
 * like
    <reference
        name="ConfigAdmin Service"
        interface="org.osgi.service.cm.ConfigurationAdmin"
        bind="bindConfigAdmin"
        unbind="unbindConfigAdmin"
        cardinality="1..1"
        policy="dynamic"/>
 */
trait ConfigAdminServiceDep {
    protected var configAdmin: Option[ConfigurationAdmin] = None

    def bindConfigAdmin(ca: ConfigurationAdmin): Unit = configAdmin = {
        assert(ca != null)
        Some(ca)
    }

    def unbindConfigAdmin(ca: ConfigurationAdmin): Unit = {
        assert(ca != null)
        configAdmin = None
    }

    // TODO: method to update some service's configuration
}
