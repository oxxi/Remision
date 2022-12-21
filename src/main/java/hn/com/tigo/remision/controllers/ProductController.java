package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.ProductModel;
import hn.com.tigo.remision.services.ProductServiceImpl;
import hn.com.tigo.remision.services.interfaces.IProductService;
import hn.com.tigo.remision.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Slf4j
public class ProductController {

    private final IProductService productService;

    private Util util;


    public ProductController(IProductService productService) {
        this.productService = productService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<ProductModel> models = this.productService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }


}

