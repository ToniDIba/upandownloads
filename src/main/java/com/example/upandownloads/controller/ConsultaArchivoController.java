package com.example.upandownloads.controller;

import com.example.upandownloads.model.Archivo;
import com.example.upandownloads.service.IBuscarArchivo;
import com.example.upandownloads.service.IvalidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@RestController
public class ConsultaArchivoController {

    @Autowired
    IvalidacionesService validacionesService;

    @Autowired
    IBuscarArchivo buscarArchivo;

    @GetMapping("/id/{idOrName}")
    public Archivo consultaPorNombreOrId(@PathVariable String idOrName) throws Exception {
        System.out.println("Paso por consultar archivo");
        Archivo archBuscado = null;
        String claveBusqueda = validacionesService.retornarIdOrName(idOrName);


        if (isNumeric(claveBusqueda)) {
            archBuscado = buscarArchivo.buscarArchivoId(Integer.parseInt(claveBusqueda)); //Busca por id numérico
        }

        return archBuscado;

    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }


}
