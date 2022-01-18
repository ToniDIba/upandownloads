package com.example.upandownloads.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface MtmtoArchivosService {

    void init(); //Antes de empezar, borra el contenido de la carpeta local "Upload"
    void sube(MultipartFile file);
    //Resource baja(String filename);         //Muestra el contenido del archivo bajado en "body"
    Resource baja(String filename, int id); //Muestra el contenido del archivo bajado en "body"
    void borraTodos(); //De la carpeta local "Upload"

}


