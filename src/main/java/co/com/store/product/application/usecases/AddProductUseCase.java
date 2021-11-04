package co.com.store.product.application.usecases;

import co.com.store.product.domain.Product;
import co.com.store.product.domain.command.AddProduct;
import co.com.store.shared.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;
@Dependent
public class AddProductUseCase implements Function<AddProduct, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(AddProduct addProduct) {
        var product =new Product(addProduct.getId(),addProduct.getName(), addProduct.getPrice());
        return product.getUncommittedChanges();
    }
}
