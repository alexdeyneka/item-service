package com.item.service.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private long id;
    private String name;
    private double price;
    private int quantity;

}
