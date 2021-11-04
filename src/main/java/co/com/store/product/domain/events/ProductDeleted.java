package co.com.store.product.domain.events;

import co.com.store.shared.domain.generic.DomainEvent;

public class ProductDeleted extends DomainEvent {

    public ProductDeleted() {
        super("sofkau.store.productdeleted");
    }
}
