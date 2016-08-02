package pal.service;

import com.beust.jcommander.internal.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dhruvr on 31/7/16.
 */
public class ServiceBuilder implements Iterable<Module> {

    private final List<Module> modules = Lists.newArrayList();

    public ServiceBuilder(Module... modules) {
        for (Module m : modules) {
            addModule(m);
        }
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    @Override
    public Iterator<Module> iterator() {
        return modules.iterator();
    }

    public Injector createInjector() {
        return Guice.createInjector(modules);
    }
}
