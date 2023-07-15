package org.jmtrading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

@Component
public class MongoIndexCreator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Create indexes after the application has started
        createIndexes();
    }

    private void createIndexes() {
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("bondType", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("quantity", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("price", Sort.Direction.ASC));
        mongoTemplate.indexOps("trades").ensureIndex(new Index().on("direction", Sort.Direction.ASC));
    }
}
