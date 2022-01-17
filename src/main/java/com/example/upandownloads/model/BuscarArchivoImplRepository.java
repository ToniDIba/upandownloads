package com.example.upandownloads.model;

import com.example.upandownloads.exception.NotFoundExceptionToni;
import com.example.upandownloads.service.IBuscarArchivo;
import com.example.upandownloads.service.IarchivoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@Service
public class BuscarArchivoImplRepository implements IBuscarArchivo {



    @Autowired
    IarchivoRepositorio iarchivoRepositorio;

    Archivo archivo = new Archivo();

    @Override
    public Archivo buscarArchivoId(int id) throws Exception {
        archivo = iarchivoRepositorio.findById(id).orElseThrow(() -> new NotFoundExceptionToni("No encuentro id: " + id));
        return archivo;
    }

}



