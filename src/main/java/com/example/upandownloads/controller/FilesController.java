package com.example.upandownloads.controller;


import com.example.upandownloads.model.FileInfo;
import com.example.upandownloads.message.ResponseMessage;
import com.example.upandownloads.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@CrossOrigin("http://localhost:8085")
public class FilesController {

    @Autowired
    FilesStorageService storageService;

/*
    @PostMapping("/uno/dos")
    @ResponseBody
    public String addFoo(@RequestParam(name = "id") String fooId,    @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
    }
*/


    // https://www.bezkoder.com/spring-boot-file-upload/

    //@PostMapping("/enviar")
    @PostMapping("/uno/dos")
    @ResponseBody
    //public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    //public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(name = "file") MultipartFile file) {
   // public void addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) { <<<< este funciona
    public void uploadFile(@RequestParam(name = "id") String fooId, @RequestParam String name) {

        //public String                              addFoo(@RequestParam(name = "id") ) {

        System.out.println("Paso por aquí");
/*
        String message = "";
        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }*/




    }

}
