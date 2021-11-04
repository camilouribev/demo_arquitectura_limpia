package co.com.store.product.domain;

import co.com.store.product.domain.events.ProductAdded;
import co.com.store.product.domain.events.ProductDeleted;
import co.com.store.product.domain.events.ProductUpdated;
import co.com.store.shared.domain.generic.AggregateRoot;
import co.com.store.shared.domain.generic.DomainEvent;
import co.com.store.shared.domain.generic.EventChange;

import java.util.List;
import java.util.Objects;

public class Product extends AggregateRoot implements EventChange {
    private String name;
    private double price;
    public Product(String id, String name, double price) {
        super(id);
        appendChange(new ProductAdded(name, price)).apply();
    }
    public void productUpdate(String name, double price){
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);
        appendChange(new ProductUpdated(name, price)).apply();
    }
    public void productDelete(){
        appendChange(new ProductDeleted()).apply();
    }
    private Product(String id){
        super(id);
        subscribe(this);
        listener((ProductAdded event)->{
            this.name = event.getName();
            this.price= event.getPrice();
        });
        listener((ProductUpdated event)->{
            this.name = event.getName();
            this.price= event.getPrice();
        });
    }
    public static Product from(String id, List<DomainEvent> events){
        var product = new Product(id);
        events.forEach(product::applyEvent);
        return product;
    }
}

