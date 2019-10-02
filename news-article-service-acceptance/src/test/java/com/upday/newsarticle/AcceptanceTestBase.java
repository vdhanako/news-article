package com.upday.newsarticle;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class AcceptanceTestBase {

    public String getBaseUrl() {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String appHost = variables.getProperty("app.host");
        String appPort = variables.getProperty("app.port");

        if(appHost.equals("") || appPort.equals("")) {
            appHost = "localhost";
            appPort = "8080";
        }

        return "http://" + appHost + ":" + appPort + "/%s";
    }

}
