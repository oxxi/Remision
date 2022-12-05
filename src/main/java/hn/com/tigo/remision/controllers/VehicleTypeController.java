package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.services.VehicleTypeServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipovehiculos")
public class VehicleTypeController {

    private final VehicleTypeServiceImpl vehicleTypeService;
    private final Util util;

    public VehicleTypeController(VehicleTypeServiceImpl typesOfTransportService) {
        this.vehicleTypeService = typesOfTransportService;
        this.util = new Util();
    }


    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<VehicleTypesModel> models = this.vehicleTypeService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        VehicleTypesModel model = this.vehicleTypeService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody VehicleTypesModel model) {
        this.vehicleTypeService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid VehicleTypesModel mode) {
        this.vehicleTypeService.update(id,mode,mode.getModifiedBy());
        return ResponseEntity.ok(util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.vehicleTypeService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
