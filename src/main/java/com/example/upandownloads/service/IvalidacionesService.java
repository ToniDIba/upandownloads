package com.example.upandownloads.service;

import com.example.upandownloads.model.Archivo;

/**
 *  Comprueba que ciertos campos no vengan con "null", que tengan una longitud mínima y máxima...
 */

public interface IvalidacionesService
{
    public void validarInfoArchivo(Archivo archivo);

    //public Archivo mapearInputOutput(InputDto inputDto);

    String retornarIdOrName(String idOrName);

    //void comprobarName(String name, Parametros params);
}







