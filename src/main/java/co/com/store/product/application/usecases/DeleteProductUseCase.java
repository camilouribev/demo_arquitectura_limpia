package co.com.store.product.application.usecases;

import co.com.store.product.domain.Product;
import co.com.store.product.domain.command.DeleteProduct;
import co.com.store.shared.domain.generic.DomainEvent;
import co.com.store.shared.domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;
@Dependent
public class DeleteProductUseCase implements Function<DeleteProduct, List<DomainEvent>> {
    private final EventStoreRepository eventStoreRepository;

    public DeleteProductUseCase(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(DeleteProduct deleteProduct) {
        var product = Product.from(deleteProduct.getId(), eventStoreRepository.getEventsBy("product", deleteProduct.getId()));
        product.productDelete();
        return product.getUncommittedChanges();
    }
}
