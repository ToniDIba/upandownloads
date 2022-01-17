package com.example.upandownloads.service;


import com.example.upandownloads.model.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnadirArchivoImpl implements IanadirArchivo {
    @Autowired
    IvalidacionesService validacionesService;

    @Autowired
    IarchivoRepositorio archivoRepositorio;


    @Override
    public void anyadirArchivo(Archivo archivo) {

        validacionesService.validarInfoArchivo(archivo);
        archivoRepositorio.save(archivo);


    }


}
