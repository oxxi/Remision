package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.services.ParameterServiceImpl;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.utils.ModuleEnum;
import hn.com.tigo.remision.utils.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/parametros")
public class ParameterController {

    private final ParameterServiceImpl parameterService;
    private final Util util;
    private final ILogService logService;

    public ParameterController(ParameterServiceImpl parameterService, ILogService logService) {
        this.parameterService = parameterService;
        this.logService = logService;
        this.util = new Util();
    }


    @GetMapping()
    public ResponseEntity<Object> getAll() {
        List<GeneralParameter> models = this.parameterService.getAll();
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/by/{name}")
    public ResponseEntity<Object> getByName(@RequestParam(value = "name") String name ) {
        GeneralParameter param = this.parameterService.getById(name);
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.Action_Load.getDescription(),name));
        return ResponseEntity.ok(this.util.setSuccessResponse(param));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody GeneralParameter model) {
        this.parameterService.add(model);
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.Action_Create.getDescription(), null));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, @Valid @RequestBody GeneralParameter model) {
        this.parameterService.update(name,model);
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.Action_Update.getDescription(), name));
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }


    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.Module.getDescription(),"Parámetros Generales"),action, "Parámetro",key,principal.getIp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
