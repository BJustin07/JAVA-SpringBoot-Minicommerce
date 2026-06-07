package com.medina.mini_commerce.Product;

import com.medina.mini_commerce.Product.dto.ProductRequestDTO;
import com.medina.mini_commerce.Product.dto.ProductResponseDTO;
import com.medina.mini_commerce.Product.exceptions.ProductAlreadyExists;
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public String createProduct(ProductRequestDTO productRequestDTO){
        productRepository.findByProductCode(productRequestDTO.getProductCode())
                .ifPresent(
                        p -> {
                            throw new ProductAlreadyExists("Product Code "
                                    + productRequestDTO.getProductCode()
                                    + "  already Exists");
                        }
                );
        Product product = new Product();
        product.setProductCode(productRequestDTO.getProductCode());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setPrice(productRequestDTO.getPrice());
        productRepository.save(product);
        return "Successfully created product: " + product.getProductCode();
    }

    public ProductResponseDTO getProductByProductCode(String productCode){
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFound("Product Code does not exist"));
        return new ProductResponseDTO(
                product.getProductCode(),
                product.getProductDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    @Transactional
    public ProductResponseDTO updateProductByProductCode(ProductRequestDTO productRequestDTO){
        Product product = productRepository.findByProductCode(productRequestDTO.getProductCode())
                .orElseThrow(() -> new ProductNotFound("Product Code does not exist"));
        if(productRequestDTO.getProductDescription() != null){
            product.setProductDescription(productRequestDTO.getProductDescription());
        }
        if(productRequestDTO.getQuantity() != null){
            product.setQuantity(productRequestDTO.getQuantity());
        }
        if(productRequestDTO.getPrice() != null){
            product.setPrice(productRequestDTO.getPrice());
        }
        productRepository.save(product);
        return new ProductResponseDTO(
                product.getProductCode(),
                product.getProductDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public String deleteProductByProductCode(String productCode){
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFound("Product Code does not exist"));
        productRepository.delete(product);
        return "Product: " + product.getProductCode() + " Deleted.";
    }

}
