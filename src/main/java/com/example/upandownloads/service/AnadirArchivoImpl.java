package com.example.upandownloads.service;


import com.example.upandownloads.model.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Guarda fila en la tabla
@Service
public class AnadirArchivoImpl implements IanadirArchivo {

    @Autowired
    IarchivoRepositorio archivoRepositorio;


    @Override
    public void anyadirArchivo(Archivo archivo) { archivoRepositorio.save(archivo); }

}
