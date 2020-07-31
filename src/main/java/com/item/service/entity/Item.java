package com.item.service.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item_table")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private int quantity;

}
