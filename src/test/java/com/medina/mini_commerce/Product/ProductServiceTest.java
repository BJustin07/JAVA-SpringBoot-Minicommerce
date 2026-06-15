package com.medina.mini_commerce.Product;

import com.medina.mini_commerce.Product.dto.ProductRequestDTO;
import com.medina.mini_commerce.Product.dto.ProductResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldCreateProduct(){
        Product sampleProduct = exampleProduct();
        ProductRequestDTO sampleProductReqDTO = new ProductRequestDTO(
                sampleProduct.getProductCode(),
                sampleProduct.getProductDescription(),
                sampleProduct.getQuantity(),
                sampleProduct.getPrice()
        );

        when(productRepository.findByProductCode("prod-sample-001")).thenReturn(Optional.empty());

        String createProductResponse = productService.createProduct(sampleProductReqDTO);

        assertNotNull(createProductResponse);
        assertEquals(
                "Successfully created product: prod-sample-001",
                createProductResponse
        );
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void shouldReturnProductByProductCode(){
        Product sampleProduct = exampleProduct();

        when(productRepository.findByProductCode("prod-sample-001")).thenReturn(Optional.of(sampleProduct));

        ProductResponseDTO product = productService.getProductByProductCode("prod-sample-001");

        assertNotNull(product);
        assertEquals(product.getProductCode(), sampleProduct.getProductCode());
    }

    @Test
    public void shouldUpdateProductByProductCode(){
        Product sampleProduct = exampleProduct();
        ProductRequestDTO sampleProductRequestDTO = new ProductRequestDTO(
                "prod-sample-001",
                "edited-productDescription",
                null,
                null
        );
        when(productRepository.findByProductCode("prod-sample-001")).thenReturn(Optional.of(sampleProduct));

        ProductResponseDTO productResponse = productService.updateProductByProductCode("prod-sample-001", sampleProductRequestDTO);

        assertNotNull(productResponse);
        assertEquals("edited-productDescription", productResponse.getProductDescription());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void shouldDeleteProductByProuctCoded(){
        Product sampleProduct = exampleProduct();
        when(productRepository.findByProductCode("prod-sample-001")).thenReturn(Optional.of(sampleProduct));

        String productDeleteResponse = productService.deleteProductByProductCode(sampleProduct.getProductCode());

        assertNotNull(productDeleteResponse);
        assertEquals("Product: " + sampleProduct.getProductCode() + " Deleted.", productDeleteResponse);
        verify(productRepository).delete(any(Product.class));
    }

    public Product exampleProduct(){
        Product sampleProduct = new Product();
        sampleProduct.setId(1);
        sampleProduct.setQuantity(123);
        sampleProduct.setProductCode("prod-sample-001");
        sampleProduct.setPrice(555.555);
        sampleProduct.setProductDescription("sample1-product-desc");

        return sampleProduct;
    }
}
