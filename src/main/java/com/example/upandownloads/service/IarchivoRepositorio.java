package com.example.upandownloads.service;


import com.example.upandownloads.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IarchivoRepositorio extends JpaRepository<Archivo, Integer>  {  }









