package co.com.store.product.infraestructure.handle;

import co.com.store.product.application.usecases.AddProductUseCase;
import co.com.store.product.domain.command.AddProduct;
import co.com.store.shared.infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddProductUseCaseHandle extends UseCaseHandle {

    private final AddProductUseCase addProductUseCase;

    public AddProductUseCaseHandle(AddProductUseCase addProductUseCase) {
        this.addProductUseCase = addProductUseCase;
    }

    @ConsumeEvent(value = "sofkau.store.addProduct", blocking = true)
    void consumeBlocking(AddProduct command) {
        var events = addProductUseCase.apply(command);
        saveProduct(command.getId(), events);
    }

}
