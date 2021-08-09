package com.shopping.cart.controller;

import com.shopping.cart.dto.ProductGetDTO;
import com.shopping.cart.dto.ProductPostDTO;
import com.shopping.cart.request.UpdateProductRequest;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductGetDTO> add(@RequestBody @Valid ProductPostDTO productPostDTO) {
        return ResponseEntity.ok(productService.createProduct(productPostDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductGetDTO>> list() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductGetDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updatePrice(id, updateProductRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}