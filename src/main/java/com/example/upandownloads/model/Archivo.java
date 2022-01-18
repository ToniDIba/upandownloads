package com.example.upandownloads.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Archivo")
@Data
public class Archivo {

    @GeneratedValue
    @Id
    public int id;

    public String fileName;
    public String mime; //Texto, imagen, sonido, video...
    public long fileSize;
    public Date uploadDate;


    public Archivo(String fn, String mm, long fs, Date ud) {

        //id es autoIncremental
        this.fileName = fn;
        this.mime = mm;
        this.fileSize = fs;
        this.uploadDate = ud;

    }


    public Archivo() { }


}

