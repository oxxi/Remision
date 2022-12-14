package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IParameterService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/parametros")
@Slf4j
public class ParameterController {

    private final IParameterService parameterService;
    private final Util util;
    private final ILogService logService;

    public ParameterController(IParameterService parameterService, ILogService logService) {
        this.parameterService = parameterService;
        this.logService = logService;
        this.util = new Util();
    }

    @PostConstruct
    void setGlobalSecurityContext() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<GeneralParameter> models = this.parameterService.getAll();
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getByName(@PathVariable(value = "name") String name ) {
        GeneralParameter param = this.parameterService.getById(name);
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(),name));
        return ResponseEntity.ok(this.util.setSuccessResponse(param));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody GeneralParameter model) {
        this.parameterService.add(model);
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.CREATE.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, @Valid @RequestBody GeneralParameter model) {
        this.parameterService.update(name,model);
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.UPDATE.getDescription(), name));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }


    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.MODULE.getDescription(),"Par??metros Generales"),action, "Par??metro",key,principal.getIp());
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
