package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.services.UnitServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidadmedida")
public class UnitController {

    private final UnitServiceImpl unitService;
    private final Util util;

    public UnitController(UnitServiceImpl unitService) {
        this.unitService = unitService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        List<UnitOfMeasurementModel> model = this.unitService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        UnitOfMeasurementModel model = this.unitService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody UnitOfMeasurementModel model) {
        this.unitService.add(model,"A");
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody UnitOfMeasurementModel model) {
        this.unitService.update(id,model,"A");
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.unitService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

}
