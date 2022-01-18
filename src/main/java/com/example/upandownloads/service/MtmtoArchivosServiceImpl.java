package com.example.upandownloads.service;


import com.example.upandownloads.exception.ExceptionToni;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


@Service
public class MtmtoArchivosServiceImpl implements MtmtoArchivosService {

    //Carpeta local en donde dejo los archivos "subidos" dentro del directorio raiz del proyecto:
    // .../IdeaProjectsª/upandownloads/uploads
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new ExceptionToni("No se ha podido inicializar la carpeta de las subidas 'uploads'.");
        }
    }




    // UpLoad
    @Override
    public void sube(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new ExceptionToni("No se ha podido guardar el archivo. Error: " + e.getMessage());
        }
    }





    //DownLoad
    @Override
    public Resource baja(String filename, int id) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                if(id == 0) { //Si id = 0, es que han informado un nombre de archivo y no un "id"
                    throw new ExceptionToni("No he podido leer el archivo " + filename + " (comprueba que exista en carpeta local 'uploads')");
                }
                else {
                    throw new ExceptionToni("No he podido obtener nombre archivo asociado al id: " + id + " (comprueba que archivo exista en carpeta local 'uploads')");
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }




    //Borra el contenido de la carpeta local  "Upload" antes de empezar
    @Override
    public void borraTodos() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


}


