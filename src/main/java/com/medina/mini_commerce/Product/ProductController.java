package com.medina.mini_commerce.Product;

import com.medina.mini_commerce.Product.dto.ProductRequestDTO;
import com.medina.mini_commerce.Product.dto.ProductResponseDTO;
import com.medina.mini_commerce.validation.OnCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<ProductResponseDTO>getProductByProductCode(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.getProductByProductCode(productRequestDTO.getProductCode()));
    }

    @PutMapping("/")
    public ResponseEntity<ProductResponseDTO>updateProductByProductCode(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.updateProductByProductCode(productRequestDTO.getProductCode(), productRequestDTO));
    }

    @DeleteMapping("/")
    public ResponseEntity<Map<String, String>>deleteProductByProductCode(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(
                Map.of("message", productService.deleteProductByProductCode(productRequestDTO.getProductCode()))
        );
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> createProduct
            (@Validated(OnCreate.class)
             @RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(
                Map.of("message", productService.createProduct(productRequestDTO))
        );
    }
}
