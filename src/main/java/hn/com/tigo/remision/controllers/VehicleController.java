package hn.com.tigo.remision.controllers;


import hn.com.tigo.remision.models.VehicleModel;
import hn.com.tigo.remision.services.VehicleServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehicleController {

    private final VehicleServiceImpl vehicleService;
    private final Util util;
    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<VehicleModel> models = this.vehicleService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        VehicleModel model = this.vehicleService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody VehicleModel model){
        this.vehicleService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody VehicleModel model) {
        this.vehicleService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.vehicleService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
