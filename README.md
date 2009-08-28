# OSGi service dependencies

The idea is to provide simple Scala traits for common OSGi service dependencies -
they can be so easy injected using Declarative Services. Thus, if say you want
to get `HttpService` in you component, you just need to follow two simple steps:

1. Extend you component with trait `HttpServiceDep`
2. Refer `HttpService` in your component description along with `bind` and
   `unbind` methods.

Example:

    import com.osinka.services.dep.HttpServiceDep

    class MyWebFrontend extends HttpServiceDep {
        def whatever = {
            for (httpSvc <- httpService) {
                // do whatever you want -- if you have HttpService injected
            }
        }
    }

And your DS XML descriptor (e.g. `OSGI-INF/frontend.xml`):

    <?xml version="1.0" encoding="UTF-8"?>
    <component name="WWW frontend">
        <implementation class="example.MyWebFrontend"/>
        <reference
            name="HTTP service"
            interface="org.osgi.service.http.HttpService"
            bind="bindHttpService"
            unbind="unbindHttpService"
            cardinality="0..1"
            policy="dynamic"/>
    </component>

## Services:
* `LogServiceDep`
  * Provides `log(level: Int, message: String)` method as well (will log only if `LogService` was injected)
* `HttpServiceDep`
* `ConfigAdminServiceDep`

## Something special
* `ServiceComponent` provides `activate`/`deactivate` methods and
  `componentContext` variable (`Option[ComponentContext]`). It also has `bundleContext: Option[BundleContext]` method for convinience.
