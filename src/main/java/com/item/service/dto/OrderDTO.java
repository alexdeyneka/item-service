package com.item.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private Integer id;
    private String item;
    private BigDecimal price;
    private Integer quantity;

    public OrderDTO() {
    }
}
