package product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.core.Response;
import product.DTO.ProductDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import product.service.ProductService;

@Path("/api/product")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    public Response findAll(){
        return productService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findProductById(@PathParam("id") Long id){
        return productService.findProductById(id);
    }

    @POST
    public Response createProduct(@Valid ProductDTO product){
        return productService.createProduct(product);
    }

    @PATCH
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDTO product){
        return productService.updateProduct(id,product);
    }
}
