package hn.com.tigo.remision.controllers;


import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.VehicleModel;
import hn.com.tigo.remision.services.VehicleServiceImpl;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vehiculos")
public class VehicleController {

    private final VehicleServiceImpl vehicleService;
    private final Util util;
    private final ILogService logService;
    public VehicleController(VehicleServiceImpl vehicleService, ILogService logService) {
        this.vehicleService = vehicleService;
        this.logService = logService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), null));
        List<VehicleModel> models = this.vehicleService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), String.valueOf(id)));
        VehicleModel model = this.vehicleService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody VehicleModel model){
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.Action_Create.getDescription(), null));
        this.vehicleService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody VehicleModel model) {
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.Action_Update.getDescription(), String.valueOf(id)));
        this.vehicleService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.vehicleService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.Module.getDescription(),"Vehículos"),action, "vehículos",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
