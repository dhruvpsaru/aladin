package pal.database;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import pal.settings.ControlSettings;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by dhruvr on 26/7/16.
 */
public class EntityManagerFactoryProvider implements Provider<EntityManagerFactory> {


    private static EntityManagerFactory ENITY_MANAGER_FACTORY;

    @Inject
    public EntityManagerFactoryProvider(ControlSettings controlSettings) {
        if (ENITY_MANAGER_FACTORY == null) {
            ENITY_MANAGER_FACTORY = createFactory(controlSettings);
        }
    }

    @Override
    public EntityManagerFactory get() {
        return ENITY_MANAGER_FACTORY;
    }


    private EntityManagerFactory createFactory(final ControlSettings settings) {

        ImmutableMap<String, String> properties = ImmutableMap.<String, String>builder()
                .put("hibernate.cache.provider_class", settings.get("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider"))
                .put("hibernate.dialect", settings.get("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect"))
                .put("hibernate.connection.driver_class", settings.get("hibernate.connection.driver_class", "org.hibernate.dialect.MySQL5InnoDBDialect"))
                .put("hibernate.connection.driver", settings.get("hibernate.connection.driver", settings.get("hibernate.connection.driver", "com.mysql.jdbc.Driver")))
                .put("hibernate.connection.url", settings.get("hibernate.connection.url", settings.get("hibernate.connection.url")))
                .put("hibernate.connection.user", settings.get("hibernate.connection.user", settings.get("hibernate.connection.user")))
                .put("hibernate.connection.password", settings.get("hibernate.connection.password", settings.get("hibernate.connection.password")))
                .put("hibernate.hbm2ddl.auto", settings.get("hibernate.hbm2ddl.auto", settings.get("hibernate.hbm2ddl.auto", "update")))
                .put("hibernate.show_sql", settings.get("hibernate.show_sql", settings.get("hibernate.show_sql", "false")))
                .put("hibernate.use_sql_comments", settings.get("hibernate.use_sql_comments", settings.get("hibernate.use_sql_comments", "false")))
                .put("hibernate.generate_statistics", settings.get("hibernate.generate_statistics", settings.get("hibernate.generate_statistics", "false")))
                .put("hibernate.c3p0.min_size", settings.get("hibernate.c3p0.min_size", settings.get("hibernate.c3p0.min_size", "1")))
                .put("hibernate.c3p0.max_size", settings.get("hibernate.c3p0.max_size", settings.get("hibernate.c3p0.max_size", "4")))
                .put("hibernate.c3p0.timeout", settings.get("hibernate.c3p0.timeout", settings.get("hibernate.c3p0.timeout", "1800")))
                .put("hibernate.c3p0.max_statements", settings.get("hibernate.c3p0.max_statements", settings.get("hibernate.c3p0.max_statements", "50")))
                .put("hibernate.c3p0.idle_test_period", settings.get("hibernate.c3p0.idle_test_period", settings.get("hibernate.c3p0.idle_test_period", "true")))
                .put("hibernate.c3p0.debugUnreturnedConnectionStackTraces", settings.get("hibernate.c3p0.debugUnreturnedConnectionStackTraces", ""))
                .put("hibernate.c3p0.unreturnedConnectionTimeout", settings.get("hibernate.c3p0.unreturnedConnectionTimeout", "4200"))
                .build();

        return Persistence.createEntityManagerFactory("persistUnits", properties);
    }
}
