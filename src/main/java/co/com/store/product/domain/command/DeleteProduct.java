package co.com.store.product.domain.command;

import co.com.store.shared.domain.generic.Command;

public class DeleteProduct extends Command {
    private String id;

    public DeleteProduct() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
