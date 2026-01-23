package product.controller;
import product.entity.ProductEntity;
import product.mapper.ProductMapper;
import product.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/api/product")
public class ProductController {
    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    @GET
    public List<ProductEntity> findAll(){
        return productRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public ProductEntity findProductById(@PathParam("id") Long id){
        return productRepository.findById(id);
    }

    @POST
    @Transactional
    public ProductEntity createProduct(ProductEntity customerEntity){
        productRepository.persist(customerEntity);
        return customerEntity;
    }

    @PATCH
    @Transactional
    @Path("/{id}")
    public ProductEntity updateProduct(@PathParam("id") Long id, ProductEntity productEntity){
        ProductEntity newProductEntity = productRepository.findById(id);
        productMapper.updateEntityFromDto(productEntity, newProductEntity);
        productRepository.persist(newProductEntity);
        return newProductEntity;
    }
}
