package com.diogosantos;

import com.diogosantos.resources.Resource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class App extends Application<Config> {

    @Override
    public void initialize(Bootstrap<Config> configBootstrap) {

    }

    @Override
    public void run(Config config, Environment environment) throws Exception {
        configureCors(environment);

        environment.jersey().register(new Resource());
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            args = new String[]{"serve", "src/main/resources/heroku-config.yml"};
        }
        new App().run(args);
    }

}
