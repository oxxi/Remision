package hn.com.tigo.remision.controllers;


import hn.com.tigo.remision.models.LogModel;
import hn.com.tigo.remision.services.LogServiceImpl;
import hn.com.tigo.remision.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/log")
public class LogController {

    private final LogServiceImpl logService;
    private final Util util;
    public LogController(LogServiceImpl logService) {
        this.logService = logService;
        this.util = new Util();
    }

    @GetMapping()
    public ResponseEntity<Object> getData(@RequestParam( value = "init", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> init,
                                          @RequestParam( value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> end) {

        List<LogModel> models = this.logService.getLog(init,end);
        return ResponseEntity.ok(this.util.setSuccessResponse(models));
    }


}
