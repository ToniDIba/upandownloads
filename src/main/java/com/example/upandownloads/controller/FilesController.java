package com.example.upandownloads.controller;


// https://www.bezkoder.com/spring-boot-file-upload/


import com.example.upandownloads.model.Archivo;
import com.example.upandownloads.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


@RestController
public class FilesController {

    @Autowired
    FilesStorageService storageService;
    private Object PropertiesReader;


    @Autowired
    IanadirArchivo anadirArchivo;

    @Autowired
    IlistaArchivos ilistaArchivos;

    @Autowired
    IvalidacionesService ivalidacionesService;

    @Autowired
    IBuscarArchivo iBuscarArchivo;


    //Subo un archivo que seleccionan desde PostMan
    @PostMapping("/enviar")
    public Archivo uploadFile(@RequestParam(name = "file") MultipartFile file) {

        Archivo miArchivo;

        try {
            storageService.save(file);

            long longi = file.getSize();
            miArchivo = new Archivo(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Date());
            anadirArchivo.anyadirArchivo(miArchivo); //Hace un Repository.add
            return miArchivo;


        } catch (Exception e) {
            miArchivo = new Archivo();

            String nombreArchivo = file.getOriginalFilename();
            String error = "Error subiendo el archivo: " + nombreArchivo + " por motivo: " + e.getMessage();

            miArchivo.setFileName(error);
            return miArchivo;
        }


    }


    //Muestra los archivos que hay en la tabla
    @GetMapping("/archivos")
    public List<Archivo> contenidoTabla() {

        List archivosTabla = new ArrayList();
        archivosTabla = ilistaArchivos.buscarTodos();
        return archivosTabla;

    }


    @GetMapping("/archivos/{archivoentrada:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String archivoentrada) throws Exception {


        boolean esNumerico = false;
        String nombreArchivo = archivoentrada;


        System.out.println("Entrada: " + archivoentrada);

        esNumerico = isNumeric(archivoentrada); //puede llegar un String filename / int id

        if(esNumerico) {
            int id = Integer.parseInt(archivoentrada);
            nombreArchivo = iBuscarArchivo.buscarArchivoId(id);
        }


        //Descargo el archivo y lo presento en el body
        Resource file = storageService.load(nombreArchivo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



    public boolean isNumeric(String strNum)
    {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if(  ! pattern.matcher(strNum).matches()) return false; //No es numérico
        return pattern.matcher(strNum).matches();               //Es un número
    }


}






    /*
    @GetMapping("/archivos")
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
    }

     */








