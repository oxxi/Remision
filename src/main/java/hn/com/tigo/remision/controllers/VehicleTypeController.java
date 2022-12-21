package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IVehicleTypeService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tipovehiculos")
@Slf4j
public class VehicleTypeController {

    private final IVehicleTypeService vehicleTypeService;
    private final Util util;
    private final ILogService logService;

    public VehicleTypeController(IVehicleTypeService typesOfTransportService, ILogService logService) {
        this.vehicleTypeService = typesOfTransportService;
        this.logService = logService;
        this.util = new Util();
    }


    @GetMapping()
    public ResponseEntity<Object> getAll() {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), null));
        List<VehicleTypesModel> models = this.vehicleTypeService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), String.valueOf(id)));
        VehicleTypesModel model = this.vehicleTypeService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody VehicleTypesModel model) {
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.CREATE.getDescription(), null));
        this.vehicleTypeService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody VehicleTypesModel model) {
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.UPDATE.getDescription(), String.valueOf(id)));
        this.vehicleTypeService.update(id,model);
        return ResponseEntity.ok(util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.vehicleTypeService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.MODULE.getDescription(),"Tipo de Vehículos"),action, "Tipo de Vehículo",key,principal.getIp());
        }catch (Exception e) {
           log.info(e.getMessage());
        }
    }
}
