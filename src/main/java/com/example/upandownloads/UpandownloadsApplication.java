package com.example.upandownloads;

import com.example.upandownloads.service.FilesStorageService;
import com.example.upandownloads.service.IarchivoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class UpandownloadsApplication implements CommandLineRunner {

    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(UpandownloadsApplication.class, args);
    }

    //Deja la carpeta local "uploads" vacía antes de empezar.
    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }



}