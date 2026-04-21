package org.example;

import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the embedded Tomcat connector with hardened HTTP/2 settings.
 * This configuration aligns with the updated defaults introduced in Tomcat 9.0.86+
 * to mitigate CVE-2024-24549 (HTTP/2 Denial of Service via improper input validation).
 */
@Configuration
public class TomcatHttp2Config {

    /**
     * Applies HTTP/2 frame and header limits to the embedded Tomcat connector so that
     * requests with excessive or malformed headers are rejected early without triggering
     * expensive processing, in line with the CVE-2024-24549 remediation guidance.
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatHttp2Customizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            if (connector.getProtocolHandler() instanceof Http11NioProtocol) {
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

                // Add the HTTP/2 upgrade protocol with tightened stream and header limits
                Http2Protocol http2Protocol = new Http2Protocol();
                // Limit concurrent HTTP/2 streams per connection to reduce resource consumption
                http2Protocol.setMaxConcurrentStreams(200);
                // Limit the maximum size (bytes) of a single HTTP/2 HEADERS frame
                http2Protocol.setMaxHeaderSize(8192);
                // Limit the number of headers per HTTP/2 request
                http2Protocol.setMaxHeaderCount(100);
                protocol.addUpgradeProtocol(http2Protocol);
            }
        });
    }
}
