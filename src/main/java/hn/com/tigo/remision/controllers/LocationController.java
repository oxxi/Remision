package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.services.LocationServiceImpl;
import hn.com.tigo.remision.services.interfaces.ILogService;
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
@RequestMapping("/ubicacion")
@Slf4j
public class LocationController {

    private final LocationServiceImpl locationService;
    private final Util util;
    private final ILogService logService;


    public LocationController(LocationServiceImpl locationService, ILogService logRepository) {
        this.locationService = locationService;
        this.logService = logRepository;
        this.util = new Util();

    }
    @GetMapping()
    public ResponseEntity<Object> getAll() {

        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), null));
        List<LocationModel> listModel =  this.locationService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(listModel));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), String.valueOf(id)));
        LocationModel model = this.locationService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody LocationModel model) {
        this.locationService.add(model);
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.Action_Create.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody LocationModel model) {

        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.Action_Update.getDescription(), String.valueOf(id)));
        this.locationService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        this.locationService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }


    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.Module.getDescription(),"Ubicaciones"),action, "Ubicación",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
