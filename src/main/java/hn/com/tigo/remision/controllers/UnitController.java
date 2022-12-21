package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.services.UnitServiceImpl;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IUnitService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/unidadmedida")
public class UnitController {

    private final IUnitService unitService;
    private final Util util;
    private final ILogService logService;

    public UnitController(IUnitService unitService, ILogService logService) {
        this.unitService = unitService;
        this.logService = logService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), null));
        List<UnitOfMeasurementModel> model = this.unitService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), String.valueOf(id)));
        UnitOfMeasurementModel model = this.unitService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody UnitOfMeasurementModel model) {
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.CREATE.getDescription(), null));
        this.unitService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody UnitOfMeasurementModel model) {
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.UPDATE.getDescription(), String.valueOf(id)));
        this.unitService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.unitService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.MODULE.getDescription(),"Unidades de Medida"),action, "unidad de medida",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
