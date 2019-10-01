package com.upday.newsarticle

import net.thucydides.core.util.SystemEnvironmentVariables

open class AcceptanceTestsBase {

    fun getBaseUrl(): String {
        val variables = SystemEnvironmentVariables.createEnvironmentVariables()
        var appHost = variables.getProperty("app.host")
        var appPort = variables.getProperty("app.port")

        if (appHost == "" || appPort == "") {
            appHost = "localhost"
            appPort = "8080"
        }

        return "http://$appHost:$appPort/%s"
    }
}