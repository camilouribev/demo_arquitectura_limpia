package co.com.store.product.domain.command;

import co.com.store.shared.domain.generic.Command;

public class DeleteProduct extends Command {
    private String productId;

    public DeleteProduct() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProgramId(String productId) {
        this.productId = productId;
    }

}
