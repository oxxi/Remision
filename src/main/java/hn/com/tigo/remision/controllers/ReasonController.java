package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.services.ReasonService;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/motivos")
public class ReasonController {

    private final ReasonService reasonService;
    private final Util util;

    public ReasonController(ReasonService reasonService) {
        this.reasonService = reasonService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<ReasonModel> models = this.reasonService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        ReasonModel model = this.reasonService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody ReasonModel model){
        this.reasonService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ReasonModel model){
        this.reasonService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.reasonService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
