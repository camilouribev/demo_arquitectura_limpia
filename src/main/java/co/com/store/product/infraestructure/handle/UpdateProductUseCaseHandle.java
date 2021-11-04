package co.com.store.product.infraestructure.handle;

import co.com.store.product.application.usecases.UpdateProductUseCase;
import co.com.store.product.domain.command.UpdateProduct;
import co.com.store.shared.infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateProductUseCaseHandle extends UseCaseHandle {
    private final UpdateProductUseCase updateProductUseCase;

    public UpdateProductUseCaseHandle(UpdateProductUseCase updateProductUseCase) {
        this.updateProductUseCase = updateProductUseCase;
    }

    @ConsumeEvent(value = "sofkau.store.updateproduct", blocking = true)
    void consumeBlocking(UpdateProduct command) {
        var events = updateProductUseCase.apply(command);
        saveProduct(command.getId(), events);
    }
}
