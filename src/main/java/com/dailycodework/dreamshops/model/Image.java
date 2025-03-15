package com.dailycodework.dreamshops.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Blob;
import java.util.List;

public class Image {

    private Long id;
    private String fileName;
    private String fileType;
    private Blob image;
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;
}
