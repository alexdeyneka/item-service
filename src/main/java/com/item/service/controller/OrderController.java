package com.item.service.controller;

import com.item.service.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public ResponseEntity findAll() {

        return null;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody OrderDTO orderDTO) {

        return null;
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity delete(@PathVariable Integer orderId) {

        return ResponseEntity.accepted().build();
    }
}

