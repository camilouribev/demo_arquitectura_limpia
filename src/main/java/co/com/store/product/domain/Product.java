package co.com.store.product.domain;

import co.com.store.product.domain.events.ProductAdded;
import co.com.store.shared.domain.generic.AggregateRoot;
import co.com.store.shared.domain.generic.EventChange;

public class Product extends AggregateRoot implements EventChange {
    protected Product(String id, String name) {
        super(id);
        appendChange(new ProductAdded(name)).apply();
    }
}

