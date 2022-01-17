package com.example.upandownloads.service;

import com.example.upandownloads.model.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaArchivosImpl implements IlistaArchivos {


    @Autowired
    IarchivoRepositorio archivoRepositorio;

    @Override
    public List<Archivo> buscarTodos() {
        return archivoRepositorio.findAll();
    }


}