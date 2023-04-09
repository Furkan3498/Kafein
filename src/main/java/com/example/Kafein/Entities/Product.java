package com.example.Kafein.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product extends BaseEntity{
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column
    private String barcode;
}
