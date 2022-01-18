package com.example.upandownloads.controller;


import com.example.upandownloads.exception.ExceptionToni;
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
public class AccionesController {

    @Autowired
    MtmtoArchivosService mtmtoArchivosService;
    private Object PropertiesReader;


    @Autowired
    IanadirArchivo anadirArchivo;

    @Autowired
    IlistaArchivos ilistaArchivos;

    @Autowired
    IBuscarArchivo iBuscarArchivo;




    //Subo un archivo que seleccionan desde PostMan a la carpeta local "uploads" y a la tabla H2 "ARCHIVO"
    @PostMapping("/enviar")
    public Archivo uploadFile(@RequestParam(name = "file") MultipartFile file) {

        Archivo miArchivo;

        try {
            mtmtoArchivosService.sube(file);

            //Inserto un archivo pasándole: nombre archivo, metadato mime("text", "image", "sound"...), tamaño y fecha.
            miArchivo = new Archivo(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Date());
            anadirArchivo.anyadirArchivo(miArchivo); //Hace un Repository.add

            //anadirArchivo.anyadirArchivo(null);  //Forzar Exception (pruebas)
            return miArchivo;

        } catch (Exception e) {
            throw new ExceptionToni("Error: " + e.getMessage());
        }


    }





    //Muestra los archivos que hay en la tabla H2 tras subir uno o más archivos
    @GetMapping("/archivos")
    public List<Archivo> contenidoTabla() {

        List archivosTabla = new ArrayList();
        archivosTabla = ilistaArchivos.buscarTodos();
        if(archivosTabla.size() == 0) throw new ExceptionToni("Tabla vacía. Archivos encontrados: " + archivosTabla.size());
        return archivosTabla;

    }




    //El usuario indica que quiere "bajarse" un determinado archivo (o indica un ID del que obtener el nombre archivo)
    @GetMapping("/archivos/{archivoentrada:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String archivoentrada) throws Exception
    {

        String nombreArchivo = archivoentrada; //Presupongo que han informado un nombre de archivo válido
        Resource contenidoArchivoBajado=null;
        int id=0;

        boolean esNumerico = isNumeric(archivoentrada); //pueden informar un String filename, o bien un int id
        if(esNumerico) id  = Integer.parseInt(archivoentrada);
        if(esNumerico) nombreArchivo = iBuscarArchivo.buscarArchivoId(id); //Retorna el nombre del archivo asociado al "id"

        contenidoArchivoBajado = mtmtoArchivosService.baja(nombreArchivo, id); //Retorna el contenido del archivo "nombreArchivo"

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
               "attachment; filename=\" " + contenidoArchivoBajado.getFilename() + "\"").body(contenidoArchivoBajado);
    }




    // Averigua si el parámetro de entrada (al intentar descargar un determinado archivo), es un numérico (id del fichero),
    // o bien un String (el nombre de un fichero).
    public boolean isNumeric(String strNum)
    {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if(  ! pattern.matcher(strNum).matches()) return false; //No es numérico
        return pattern.matcher(strNum).matches();               //Es un número
    }


}

