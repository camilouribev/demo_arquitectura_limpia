package co.com.store.product.infraestructure.handle;

import co.com.store.product.application.usecases.DeleteProductUseCase;
import co.com.store.product.domain.command.DeleteProduct;
import co.com.store.shared.infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteProductUseCaseHandle extends UseCaseHandle {
    private final DeleteProductUseCase deleteProductUseCase;

    public DeleteProductUseCaseHandle(DeleteProductUseCase deleteProductUseCase) {
        this.deleteProductUseCase = deleteProductUseCase;
    }
    @ConsumeEvent("sofkau.store.productdelete")
    void consumeNoBlocking(DeleteProduct deleteProduct){
        var events = deleteProductUseCase.apply(deleteProduct);
        saveProduct(deleteProduct.getId(), events);
    }
}
