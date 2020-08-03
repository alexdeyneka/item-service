package com.item.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String itemName;
    private double price;
    private Integer quantity;

}
