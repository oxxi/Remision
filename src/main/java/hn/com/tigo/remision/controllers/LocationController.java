package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.services.LocationServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ubicacion")
public class LocationController {


    private final LocationServiceImpl locationService;
    private final Util util;


    public LocationController(LocationServiceImpl locationService) {
        this.locationService = locationService;
        this.util = new Util();
    }
    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<LocationModel> listModel =  this.locationService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(listModel));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        LocationModel model = this.locationService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody LocationModel model) {
        this.locationService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody LocationModel model) {
        this.locationService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.locationService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
