package com.cybersoft.cozastore_java21.controller;

import com.cybersoft.cozastore_java21.exception.CustomeFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/demo")
public class DemoDownloadFileController {

    @Value("${path.root}")
    private String spath;

    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadFile(
            @PathVariable String filename
            ) {

        try {
//      Duong dan folder root luu hinh
//          http://localhost:8080/demo/Screen Shot 2023-05-25 at 19.08.08.png
            Path rootPath = Paths.get(spath);
            Resource resource = new UrlResource(rootPath.resolve(filename).toUri());

            if (resource.exists()) {
//             Neu ton tai thi moi cho phep download


                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);

//                return new ResponseEntity<>(resource, HttpStatus.OK);
            } else {
//              Khi nem exception thi code se dung & vang ra loi
                throw new CustomeFileNotFoundException(200, "File is not found");
            }
        } catch (Exception ex) {
            throw new CustomeFileNotFoundException(200, "File is not found");
        }

    }
}
