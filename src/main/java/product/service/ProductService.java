package product.service;

import jakarta.ws.rs.core.Response;
import product.DTO.ProductDTO;

public interface ProductService {
    Response findAll();

    Response findProductById(Long id);

    Response createProduct(ProductDTO productEntity);

    Response updateProduct(Long id, ProductDTO productEntity);

}
