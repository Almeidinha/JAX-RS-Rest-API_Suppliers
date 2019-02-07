package com.almeida.supplier;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resources")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "com.almeida.supplier.resources");
        return properties; 
    }
}
