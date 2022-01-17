package com.example.upandownloads.model;

import lombok.Data;

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
    public String mime;
    public long fileSize;
    public Date uploadDate;


    Archivo() { }

    public Archivo(String fn, String mm, long fs, Date ud) {

        //this.id_file = id;
        this.fileName = fn;
        this.mime = mm;
        this.fileSize = fs;
        this.uploadDate = ud;

    }


}

