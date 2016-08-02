package ginie.database;

import com.google.inject.AbstractModule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by dhruvr on 26/7/16.
 */
public class DBModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();


    @Override
    protected void configure() {
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class).asEagerSingleton();
        bind(EntityManager.class).toProvider(EntityManagerProvider.class).asEagerSingleton();
    }


}
