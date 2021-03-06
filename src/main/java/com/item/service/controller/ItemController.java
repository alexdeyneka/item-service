package com.item.service.controller;

import com.item.service.dto.ItemDTO;
import com.item.service.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    // handles get requests in order to get all items from the db
    @GetMapping("/find")
    public ResponseEntity<ItemDTO> getTheLowestPriceItem(@RequestParam String itemName, @RequestParam int quantity) {
        Optional<ItemDTO> optionalItemDTO = itemService.findByPrice(itemName, quantity);
        ResponseEntity<ItemDTO> body = null;
        if (optionalItemDTO.isPresent()) {
            body = ResponseEntity.ok().body(optionalItemDTO.get());
        }
        return body;
    }
}

