package org.jmtrading;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TradeProcessorRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:queue:tradeQueue")
                .to("bean:tradeProcessorBean");
    }
}
