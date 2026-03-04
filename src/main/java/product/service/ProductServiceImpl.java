package product.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import product.DTO.ProductDTO;
import product.entity.ProductEntity;
import product.mapper.ProductMapper;
import product.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    @Override
    public Response findAll() {
        log.info("Procedemos a buscar todos los productos");
        List<ProductDTO> productDTOList= productMapper.toDTOList(productRepository.findAll().list());
        if(productDTOList.isEmpty()){
            throw new RuntimeException("Aun no existen productos en la base de datos");
        }

        return Response.status(Response.Status.OK)
                .entity(productDTOList)
                .build();
    }

    @Override
    public Response findProductById(Long id) {
        log.info("Procedemos a buscar el producto con ID = {} ",id);
        ProductEntity productEntity = productRepository.findById(id);

        validarPresencia(id, productEntity);

        log.info("Producto con ID = {} encontrado",id);
        return Response.status(Response.Status.OK)
                .entity(productMapper.toDTO(productEntity))
                .build();
    }

    @Override
    @Transactional
    public Response createProduct(ProductDTO product) {
        log.info("Procedemos a crear el producto [{}] ",product.getName());
        ProductEntity productEntity = productMapper.toEntity(product);
        productRepository.persist(productEntity);
        log.info("Producto [{}] creado exitosamente ",product.getName());
        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @Override
    @Transactional
    public Response updateProduct(Long id, ProductDTO product) {
        log.info("Procedemos a actualizar el producto con ID = {} ",id);
        ProductEntity newProductEntity = productRepository.findById(id);
        validarPresencia(id,newProductEntity);
        ProductEntity productEntity = productMapper.toEntity(product);
        productMapper.updateEntityFromDto(productEntity, newProductEntity);
        productRepository.persist(newProductEntity);
        log.info("Producto con ID = {} actualizado correctamente ",id);
        return Response.ok(productMapper.toDTO(newProductEntity)).build();
    }

    private static void validarPresencia(Long id, ProductEntity productEntity) {
        if (productEntity == null ){
            log.info("No se encontro un producto con el ID = {} ", id);
            throw new RuntimeException("Producto no encontrado en la base de datos");
        }
    }
}
