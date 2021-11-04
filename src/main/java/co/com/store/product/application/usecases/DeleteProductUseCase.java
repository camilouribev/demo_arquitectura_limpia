package co.com.store.product.application.usecases;

import co.com.store.product.domain.Product;
import co.com.store.product.domain.command.DeleteProduct;
import co.com.store.shared.domain.generic.DomainEvent;
import co.com.store.shared.domain.generic.EventStoreRepository;

import java.util.List;
import java.util.function.Function;

public class DeleteProductUseCase implements Function<DeleteProduct, List<DomainEvent>> {
    private final EventStoreRepository eventStoreRepository;

    public DeleteProductUseCase(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public List<DomainEvent> apply(DeleteProduct deleteProduct) {
        var product = Product.from(deleteProduct.getProductId(), eventStoreRepository.getEventsBy("product", deleteProduct.getProductId()));
        product.productDelete();
        return product.getUncommittedChanges();
    }
}
