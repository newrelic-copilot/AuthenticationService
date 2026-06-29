package org.example;

import org.apache.catalina.Container;
import org.apache.catalina.Wrapper;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatSecurityConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatHardeningCustomizer() {
        return factory -> {
            factory.addConnectorCustomizers(connector -> connector.setProperty("allowPartialPut", "false"));
            factory.addContextCustomizers(context -> {
                Container defaultServlet = context.findChild("default");
                if (defaultServlet instanceof Wrapper) {
                    ((Wrapper) defaultServlet).addInitParameter("readonly", "true");
                }
            });
        };
    }
}
