package org.jmtrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        // Generate trades
        TradeGenerator tradeGenerator = context.getBean(TradeGenerator.class);
        tradeGenerator.generateTrades();
    }
}
