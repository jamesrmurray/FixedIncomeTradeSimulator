package org.jmtrading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TradeGenerator {
    private static final int THREAD_COUNT = 5;
    private static final int TRADE_COUNT_PER_THREAD = 10;

    private final Random random = new Random();
    private final List<Trade> trades = new ArrayList<>();

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Trade> generateTrades() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(this::generateTradesInThread);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            // Wait until all threads are finished
        }

        return trades;
    }

    private void generateTradesInThread() {
        for (int i = 0; i < TRADE_COUNT_PER_THREAD; i++) {
            Trade trade = generateRandomTrade();
            trades.add(trade);

            // Insert trade into MongoDB
            mongoTemplate.insert(trade);
        }
    }

    private Trade generateRandomTrade() {
        Random random = new Random();

        // Generate a random bond type from a predefined list
        String[] bondTypes = {"Government", "Corporate", "Municipal"};
        String bondType = bondTypes[random.nextInt(bondTypes.length)];

        // Generate a random quantity between 1 and 100
        int quantity = random.nextInt(100) + 1;

        // Generate a random price between 100.0 and 1000.0
        double price = 100.0 + random.nextDouble() * (1000.0 - 100.0);

        // Generate a random direction from a predefined list
        String[] directions = {"Buy", "Sell"};
        String direction = directions[random.nextInt(directions.length)];

        // Create a new Trade instance with the generated values
        return new Trade(bondType, quantity, price, direction);
    }
}
