package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.ProductModel;
import hn.com.tigo.remision.services.LocationServiceImpl;
import hn.com.tigo.remision.services.ProductServiceImpl;
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

    private final ProductServiceImpl productService;

    private Util _util;


    public ProductController(ProductServiceImpl productService, LocationServiceImpl locationService) {
        this.productService = productService;
        this._util = new Util();
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<ProductModel> models = this.productService.getAll();
        return ResponseEntity.ok(this._util.setSuccessResponse(models));
    }


}

