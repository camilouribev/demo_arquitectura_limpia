package co.com.store.product.domain.events;

import co.com.store.shared.domain.generic.DomainEvent;

public class ProductUpdated extends DomainEvent {
    private final String id;
    private final String name;
    private final double price;

    public ProductUpdated(String id, String name, double price) {
        super("sofkau.store.productupdated");
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
