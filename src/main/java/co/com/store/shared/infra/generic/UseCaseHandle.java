package co.com.store.shared.infra.generic;


import co.com.store.product.infraestructure.MessageService;
import co.com.store.shared.domain.generic.DomainEvent;
import co.com.store.shared.domain.generic.EventStoreRepository;
import co.com.store.shared.domain.generic.StoredEvent;
import org.jboss.logging.Logger;

import javax.decorator.Decorator;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {
    private static final Logger LOG = Logger.getLogger(UseCaseHandle.class);
    @Inject
    private EventStoreRepository repository;

    @Inject
    private MessageService messageService;

    public void
    saveProduct(String id, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("product", id, storedEvent));
        events.forEach(messageService::send);
    }
}
