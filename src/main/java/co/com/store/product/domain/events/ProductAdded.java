package co.com.store.product.domain.events;

import co.com.store.shared.domain.generic.DomainEvent;

public class ProductAdded extends DomainEvent {
    private final String name;
    public ProductAdded(String name) {
        super("sofkau.store.productadded");
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
