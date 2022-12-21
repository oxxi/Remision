package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.services.MotoristServiceImpl;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IMotoristService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/conductores")
public class MotoristController {

    private final IMotoristService motoristService;
    private final Util util;
    private final ILogService logService;


    public MotoristController(IMotoristService motoristService, ILogService logService) {
        this.motoristService = motoristService;
        this.logService = logService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), null));
        List<MotoristModel> models =  this.motoristService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), String.valueOf(id)));
        MotoristModel model = this.motoristService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody MotoristModel model){
        this.motoristService.add(model);
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.CREATE.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody MotoristModel model){
        this.motoristService.update(id,model);
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.UPDATE.getDescription(), String.valueOf(id)));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.motoristService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }


    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.MODULE.getDescription(),"Motoristas"),action, "Motorista",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
