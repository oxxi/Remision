package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.services.MotoristServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conductores")
public class MotoristController {

    private final MotoristServiceImpl motoristService;
    private final Util util;

    public MotoristController(MotoristServiceImpl motoristService) {
        this.motoristService = motoristService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<MotoristModel> models =  this.motoristService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        MotoristModel model = this.motoristService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody MotoristModel model){
        this.motoristService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody MotoristModel model){
        this.motoristService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.motoristService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
