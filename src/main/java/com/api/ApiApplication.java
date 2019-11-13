package com.api;

import com.api.injection.ApplicationBinder;
import com.api.resources.UserApi;
import com.api.security.PreMatchingCurrentUserFilter;
import com.api.utils.JacksonMapper;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class ApiApplication extends ResourceConfig {

    public ApiApplication() {

        setApplicationName("Api");

        // Jackson mapping
        register(JacksonMapper.class);  // json serialize/deserialize

        // Providers
        register(new ApplicationBinder()); // for H2K injection dependency
        register(new PreMatchingCurrentUserFilter());

        // Resources
        register(UserApi.class);
    }
}
