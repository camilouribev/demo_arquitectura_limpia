package co.com.store.product.application.usecases;

import co.com.store.product.domain.Product;
import co.com.store.product.domain.command.UpdateProduct;
import co.com.store.shared.domain.generic.DomainEvent;
import co.com.store.shared.domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;
@Dependent
public class UpdateProductUseCase implements Function<UpdateProduct, List<DomainEvent>> {
    private final EventStoreRepository eventStoreRepository;

    public UpdateProductUseCase(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(UpdateProduct updateProduct) {
        var events = eventStoreRepository.getEventsBy("product", updateProduct.getId());
        var product = Product.from(updateProduct.getId(), events);
        product.productUpdate(updateProduct.getId(),updateProduct.getName(), updateProduct.getPrice());
        return product.getUncommittedChanges();
    }
}
