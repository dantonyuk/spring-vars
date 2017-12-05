## Working with external variables

Assume there are some external variables. Some spring beans need fresh values of these variables but obtaining them is
expensive. This approach is suggesting getting values during initialization, and reinitializing beans after variables
are changed. Only beans dependent on changed variables should be reinitialized. 

To reach this out this library uses custom scope for such kind of beans and intercepts calls for variables.

There should be one service providing access to the variables. It should implement
[VarProvider](src/main/java/com/github/hyla/springvars/VarProvider.java) interface:

```java
@Service
public class VarService implements VarProvider {
    @Override
    String findVar(Object caller, String name) {
        ...
    }
}
```

Enable var-scoped beans:

```java
@Configuration
@EnableVarScope
@ComponentScan(scopedProxy = ScopedProxyMode.INTERFACES)
public class AppConfig {
    ...
}
```

Note that var-dependent beans should be proxied. Otherwise beans which use var-dependent beans won't see new instances
after reinitialization.

Then mark beans using variables with
[```@UsesVars```](src/main/java/com/github/hyla/springvars/annotation/UsesVars.java) annotation:

```java
@Service
@UsesVars
public class MyServiceImpl implements MyService {

    @Autowired
    private VarProvider varProvider;

    private String myParam;

    @PostConstruct
    public void init() {
        myParam = varProvider.findVar(this, "MY_PARAM");
    }
}
```

Note that we pass a reference to the service itself as a first parameter. The reason is simple:
I couldn't find an easy way to get the caller in
[```VarAspect```](src/main/java/com/github/hyla/springvars/VarAspect.java). So I've decided to pass it explicitly.

There should be also a listener which will be informed about changes, e.g.:

```java
@RestController
public class VarChangingController {

    @Autowired
    private VarManager varManager;

    @PostMapping("/notify?vars={varNames}")
    public String notifyAboutChanges(@PathVariable List<String> varNames) {
        varManager.varChanged(varNames);
        return "OK";
    }
}
``` 

Now everything is ready to work. Check out [VarTest code](src/test/java/com/github/hyla/springvars/VarTest.java).
