package co.com.store.product.infraestructure.materialize;

import co.com.store.product.domain.events.ProductAdded;
import co.com.store.product.domain.events.ProductDeleted;
import co.com.store.product.domain.events.ProductUpdated;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProgramHandle {
    private final MongoClient mongoClient;


    public ProgramHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofkau.store.productadded", blocking = true)
    void consumeProductAdded(ProductAdded event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());
        document.put("price", event.getPrice());
        mongoClient.getDatabase("queries").getCollection("product")
                .insertOne(new Document(document));

    }

    @ConsumeEvent(value = "sofkau.store.productupdated", blocking = true)
    void consumeProductUpdated(ProductUpdated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("name", event.getName());
        document.put("price", event.getPrice());
        BasicDBObject objectUpdated = new BasicDBObject();
        objectUpdated.put("$set", objectUpdated);
        mongoClient.getDatabase("queries").getCollection("product").updateOne(Filters.eq("_id", event.getId()), objectUpdated);
    }

    @ConsumeEvent("sofkau.store.productdeleted")
    void consumeProductDeleted(ProductDeleted productDeleted) {
        mongoClient.getDatabase("queries").getCollection("product").deleteOne(Filters.eq("_id", productDeleted.getAggregateId()));
    }
}
