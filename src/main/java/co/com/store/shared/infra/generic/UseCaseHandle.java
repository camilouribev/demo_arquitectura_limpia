package co.com.store.shared.infra.generic;


import co.com.store.shared.domain.generic.EventStoreRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {
    private static final Logger LOG = Logger.getLogger(UseCaseHandle.class);
    @Inject
    private EventStoreRepository repository;

    /*@Inject
    private   MessageService messageService;

    public void saveProgram(String programId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = co.com.sofka.wsscore.infra.generic.EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("program", programId, storedEvent));
        events.forEach(messageService::send);
        LOG.info("se esta guardando los datos y enviando los mensajes...");
    }*/
}
