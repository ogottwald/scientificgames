package com.scientificgames.app;

import org.glassfish.jersey.server.ResourceConfig;

public class StudentApplication extends ResourceConfig {
	public StudentApplication() {
        packages("com.scientificgames.services");
    }
}
