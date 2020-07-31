package com.item.service.dto;

import com.item.service.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item itemDTOToItem(ItemDTO itemDTO);

    ItemDTO itemToItemDTO(Item item);
}
