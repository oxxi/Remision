package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.services.ParameterServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parametros")
public class ParameterController {

    private final ParameterServiceImpl parameterService;
    private final Util util;

    public ParameterController(ParameterServiceImpl parameterService) {
        this.parameterService = parameterService;
        this.util = new Util();
    }


    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<GeneralParameter> models = this.parameterService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/by")
    public ResponseEntity<Object> getByName(@RequestParam(value = "name") String name ) {
        GeneralParameter param = this.parameterService.getById(name);
        return ResponseEntity.ok(this.util.setSuccessResponse(param));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody GeneralParameter model) {
        this.parameterService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, @Valid @RequestBody GeneralParameter model) {
        this.parameterService.update(name,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }
}
