package com.example.Kafein.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity

public class Category extends BaseEntity{
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "warehouseId")
    private WareHouse warehouse;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products = new ArrayList<Product>();

}
