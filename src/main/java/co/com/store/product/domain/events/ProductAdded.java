package co.com.store.product.domain.events;

import co.com.store.shared.domain.generic.DomainEvent;

public class ProductAdded extends DomainEvent {
    private final String name;
    private final double price;
    public ProductAdded(String name, double price) {
        super("sofkau.store.productadded");
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
