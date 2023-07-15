package org.jmtrading;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;

@Component
public class TradeProcessor extends RouteBuilder {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void configure() throws Exception {
        createIndexes();
        from("direct:tradeQueue")
                .choice()
                .when(this::isValidTrade)
                .process(this::processTrade)
                .to("direct:saveTrade")
                .otherwise()
                .throwException(InvalidTradeException.class, "Invalid trade received");
    }

    private boolean isValidTrade(Exchange exchange) {
        Trade trade = exchange.getIn().getBody(Trade.class);

        // Validate trade logic
        // Replace with your own trade validation rules

        // Example trade validation rules
        if (trade.getPrice() <= 0 || trade.getQuantity() <= 0) {
            return false;
        }

        if (trade.getBondType() == null || trade.getBondType().isEmpty()) {
            return false;
        }

        // Add more validation rules as needed

        return true;
    }

    private void processTrade(Exchange exchange) {
        Trade trade = exchange.getIn().getBody(Trade.class);

        // Process trade logic
        // Replace with your own trade processing rules

        // Example trade processing rules
        if (trade.getQuantity() > 1000) {
            trade.setDirection("Sell");
        } else {
            trade.setDirection("Buy");
        }

        // Apply any other business rules, calculations, transformations, etc.

        trade.setProcessed(true);

        // Save the trade to MongoDB
        saveTradeToMongoDB(trade);
    }

    private void saveTradeToMongoDB(Trade trade) {
        mongoTemplate.save(trade, "trades");
    }

    private void createIndexes() {
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("bondType", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("quantity", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("price", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("direction", Sort.Direction.ASC));
    }
}
