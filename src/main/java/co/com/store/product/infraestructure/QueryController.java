package co.com.store.product.infraestructure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

@Path(value="/store")
public class QueryController {
    private final MongoClient mongoClient;
    public QueryController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/{id}")
    public Response get(@PathParam("id") String programId) {
        List<Document> documentList = new ArrayList<>();
        mongoClient.getDatabase("queries")
                .getCollection("product")
                .find(Filters.eq("_id", programId))
                .forEach(documentList::add);
        return Response.ok(documentList).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/products")
    public Response get() {
        List<Document> documentList = new ArrayList<>();
        mongoClient.getDatabase("queries")
                .getCollection("product")
                .find()
                .forEach(documentList::add);
        return Response.ok(documentList).build();
    }


}
