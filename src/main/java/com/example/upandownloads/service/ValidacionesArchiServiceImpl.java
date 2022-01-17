package com.example.upandownloads.service;


import com.example.upandownloads.model.Archivo;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ValidacionesArchiServiceImpl implements IvalidacionesService {


        @Override
        public void validarInfoArchivo(Archivo archivo) {

        }

        @Override
        public String retornarIdOrName(String idOrName) {

            Optional<Integer> idParam = Optional.empty();
            Optional<String> nombreParam;

            idOrName = idOrName.trim();
            String nombreBuscado = null;

            try {
                idParam = Optional.ofNullable(Integer.parseInt(idOrName));         // Busca por "Id";
            } catch (NumberFormatException nfe) {
                nombreParam = Optional.ofNullable(idOrName);
                nombreBuscado = nombreParam.get();                                //Busca por "Name"
            }

            if (idParam.isPresent()) {
                return idParam.get().toString();
            } else {
                return nombreBuscado;
            }

        }

}