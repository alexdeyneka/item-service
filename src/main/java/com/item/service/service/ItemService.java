package com.item.service.service;

import com.item.service.dto.ItemDTO;
import com.item.service.dto.ItemMapper;
import com.item.service.entity.Item;
import com.item.service.repository.ItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepo itemRepo;

    // gets all items from the db
    public List<ItemDTO> findAll() {
        List<Item> entityList = itemRepo.findAll();
        List<ItemDTO> dtoList = new ArrayList<>();
        entityList.forEach(element -> dtoList.add(ItemMapper.INSTANCE.itemToItemDTO(element)));
        return dtoList;
    }

    // finds the item by the item's name at the lowest price
    public Optional<ItemDTO> findByPrice(String itemName, int quantity) {
        List<ItemDTO> itemDTOList;
        itemDTOList = findAll().stream()
                .filter(element -> element.getName().equals(itemName))
                .sorted(Comparator.comparingDouble(ItemDTO::getPrice))
                .limit(1)
                .collect(Collectors.toList());
        if (itemDTOList.isEmpty()) {
            log.warn("The item with name " + itemName + " is out of stock");
            return null;
        } else {
            if (itemDTOList.get(0).getQuantity() < quantity) {
                log.warn("Only " + itemDTOList.get(0).getQuantity() + " is available");
            }
        }
        return Optional.ofNullable(itemDTOList.get(0));
    }

}
