package com.cybersoft.cozastore_java21.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/demo")
public class DemoUploadFileController {

//  Path : Chua toan bo ham ho tro san lien quan toi duong dan
    @Value("${path.root}")
    private String spath;

    @PostMapping("/uploadfiles")
    public ResponseEntity<?> uploadFiles(
            @RequestParam MultipartFile file // MultipartFile[] files
            ) {

//      Dinh nghia duong dan
        Path rootPath = Paths.get(spath);

        try {
            if (!Files.exists(rootPath)) {
//              Tao folder ung voi lai duong dan neu khong ton tai folder
                Files.createDirectories(rootPath);
            }

//          /Users/minhtri/Desktop/image21/abc.jpeg
//          resolve() <=> /
//          file.getOriginalFilename() : Lay ten file va dinh dang

            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(),rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception ex) {
            System.out.println("Loi " + ex.getLocalizedMessage());
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
