package co.com.store.product.infraestructure;

import co.com.store.product.domain.command.AddProduct;
import co.com.store.product.domain.command.DeleteProduct;
import co.com.store.product.domain.command.UpdateProduct;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/store")
public class CommandController {

    private final MessageService messageService;

    public CommandController(MessageService messageService){
        this.messageService = messageService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addProduct")
    public Response executor(AddProduct command) {
        messageService.send(command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateProduct")
    public Response executor(UpdateProduct command) {
        messageService.send(command);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteProduct")
    public Response executor(DeleteProduct command) {
        messageService.send(command);
        return Response.ok().build();
    }

}
