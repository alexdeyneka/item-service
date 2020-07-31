package com.item.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;

}
