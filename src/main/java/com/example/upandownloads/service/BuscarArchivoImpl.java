package com.example.upandownloads.service;

import com.example.upandownloads.exception.ExceptionToni;
import com.example.upandownloads.model.Archivo;
import com.example.upandownloads.service.IBuscarArchivo;
import com.example.upandownloads.service.IarchivoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuscarArchivoImpl implements IBuscarArchivo {

    @Autowired
    IarchivoRepositorio iarchivoRepositorio;

    Archivo archivo;


    //FindById
    @Override
    public String buscarArchivoId(int id) throws Exception {
        archivo = iarchivoRepositorio.findById(id).orElseThrow(() ->
                  new ExceptionToni("No encuentro id: " + id + " para obtener el nombre archivo asociado."));
        return archivo.getFileName();
    }

}



