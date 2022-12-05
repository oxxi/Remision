package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.WarehouseManagerModel;
import hn.com.tigo.remision.services.WarehouseManagerService;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/encargadobodega")
public class WarehouseManagerController {

    private final WarehouseManagerService managerService;
    private final Util util;
    public WarehouseManagerController(WarehouseManagerService managerService) {
        this.managerService = managerService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<WarehouseManagerModel> models = this.managerService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        WarehouseManagerModel model = this.managerService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody WarehouseManagerModel model) {
        this.managerService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody WarehouseManagerModel model){
        this.managerService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.managerService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
