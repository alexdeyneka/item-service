package com.item.service.dto;

import com.item.service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(com.item.service.dto.OrderMapper.class);
    Order productDTOToProduct(OrderDTO orderDTO);
    OrderDTO productToProductDTO(Order order);
}
