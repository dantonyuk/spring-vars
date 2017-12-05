package com.github.hyla.springvars.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VarScope implements Scope {

    private Map<Object, String> beanNames = Collections.synchronizedMap(new IdentityHashMap<>());
    private Map<String, Object> scopedObjects = new ConcurrentHashMap<>();
    private Map<String, Runnable> destructionCallbacks = new ConcurrentHashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object bean = scopedObjects.get(name);

        if (bean == null) {
            bean = objectFactory.getObject();
            scopedObjects.put(name, bean);
            beanNames.put(bean, name);
        }

        return bean;
    }

    @Override
    public Object remove(String name) {
        Runnable destructor = destructionCallbacks.remove(name);
        if (destructor != null) {
            destructor.run();
        }

        Object bean = scopedObjects.remove(name);

        if (bean != null) {
            beanNames.remove(bean);
        }

        return bean;
    }

    public Object removeBean(Object bean) {
        String beanName = beanNames.get(bean);

        if (beanName != null) {
            return remove(beanName);
        }

        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
