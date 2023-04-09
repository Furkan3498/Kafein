package com.example.Kafein.Entities;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class WareHouse extends BaseEntity{
    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
    private List<Category> categoryList = new ArrayList<Category>();
}
