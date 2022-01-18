package com.example.upandownloads;

import com.example.upandownloads.service.MtmtoArchivosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class UpandownloadsApplication implements CommandLineRunner {
    //public class UpandownloadsApplication  {

    @Resource
    MtmtoArchivosService storageService;

    public static void main(String[] args) {
        SpringApplication.run(UpandownloadsApplication.class, args);
    }


    //Deja la carpeta local "uploads" vacía antes de empezar.
    @Override
    public void run(String... arg) throws Exception {
        storageService.borraTodos();
        storageService.init();
    }



}