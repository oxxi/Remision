package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.WarehouseManagerModel;
import hn.com.tigo.remision.services.WarehouseManagerService;
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
@RequestMapping("/encargadobodega")
public class WarehouseManagerController {

    private final WarehouseManagerService managerService;
    private final Util util;
    private final ILogService logService;
    public WarehouseManagerController(WarehouseManagerService managerService, ILogService logService) {
        this.managerService = managerService;
        this.logService = logService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), null));
        List<WarehouseManagerModel> models = this.managerService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), String.valueOf(id)));
        WarehouseManagerModel model = this.managerService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody WarehouseManagerModel model) {
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.Action_Create.getDescription(), null));
        this.managerService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody WarehouseManagerModel model){
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.Action_Update.getDescription(), String.valueOf(id)));
        this.managerService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.managerService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.Module.getDescription(),"Encargados de Bodegas"),action, "Encargado de bodega",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
