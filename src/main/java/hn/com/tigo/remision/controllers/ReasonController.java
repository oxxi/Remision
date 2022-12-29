package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IReasonService;
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
@RequestMapping("/motivos")
@Slf4j
public class ReasonController {

    private final IReasonService reasonService;
    private final Util util;
    private final ILogService logService;

    public ReasonController(IReasonService reasonService, ILogService logService) {
        this.reasonService = reasonService;
        this.logService = logService;

        this.util = new Util();
    }

    @PostConstruct
    void setGlobalSecurityContext() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), null));
        List<ReasonModel> models = this.reasonService.getAll();
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        CompletableFuture.runAsync(() -> log(null,ModuleEnum.LOAD.getDescription(), String.valueOf(id)));
        ReasonModel model = this.reasonService.getById(id);
        return ResponseEntity.ok(this.util.setSuccessResponse(model));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody ReasonModel model){
        CompletableFuture.runAsync(() -> log(model.getCreatedBy(), ModuleEnum.CREATE.getDescription(), null));
        this.reasonService.add(model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ReasonModel model){
        CompletableFuture.runAsync(() -> log(model.getModifiedBy(), ModuleEnum.UPDATE.getDescription(), String.valueOf(id)));
        this.reasonService.update(id,model);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        this.reasonService.delete(id);
        return ResponseEntity.ok(this.util.setSuccessWithoutData());
    }


    private void log(String userName, String action, String key) {
        try{
            AuthModel principal = (AuthModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userName !=null) principal.setUserName(userName);
            this.logService.insertLog(principal.getUserName(),String.format(ModuleEnum.MODULE.getDescription(),"Motivos"),action, "Motivo",key,principal.getIp());
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
