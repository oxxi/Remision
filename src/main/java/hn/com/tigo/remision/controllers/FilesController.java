package hn.com.tigo.remision.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FilesController {

    @GetMapping()
    public ResponseEntity<Resource> download() {

        HttpHeaders header = new HttpHeaders();

        String test = "Hola Mundo File";
        String fileName = "testFilename.txt";

        //set headers
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");


        ByteArrayResource resource = new ByteArrayResource(test.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(header).contentLength(test.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }
}
