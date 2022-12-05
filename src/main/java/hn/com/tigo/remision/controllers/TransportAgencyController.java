package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.TransportAgencyModel;
import hn.com.tigo.remision.services.TransportAgencyServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agenciastransporte")
public class TransportAgencyController {

    private final TransportAgencyServiceImpl agencyService;
    private final Util util;

    public TransportAgencyController(TransportAgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<TransportAgencyModel> models = this.agencyService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        TransportAgencyModel model = this.agencyService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody TransportAgencyModel model) {
        this.agencyService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TransportAgencyModel model) {
        this.agencyService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.agencyService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
