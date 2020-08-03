package com.item.service.controller;

import com.item.service.dto.OrderDTO;
import com.item.service.service.OrderService;
import com.item.service.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    private final List<OrderDTO> orderDTOList = new TestDataGenerator().generateOrderDTOList();
    private final List<OrderDTO> emptyOrderDTOList = new ArrayList<>();

    @Test
    void findAll() throws Exception {
        given(orderService.findAll()).willReturn(orderDTOList);
        this.mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].itemName").value(orderDTOList.get(0).getItemName()))
                .andExpect(jsonPath("$.[1].itemName").value(orderDTOList.get(1).getItemName()))
                .andExpect(jsonPath("$.[2].itemName").value(orderDTOList.get(2).getItemName()));
        verify(orderService, times(1)).findAll();
        verify(orderService, only()).findAll();
    }

    @Test
    void findAllEmptyCase() throws Exception {
        given(orderService.findAll()).willReturn(emptyOrderDTOList);
        this.mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk());
        verify(orderService, times(1)).findAll();
        verify(orderService, only()).findAll();
    }

    @Test
    void displayActiveOrders() throws Exception {
        given(orderService.displayActiveOrders()).willReturn(orderDTOList);
        this.mockMvc.perform(get("/order/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].itemName").value(orderDTOList.get(0).getItemName()))
                .andExpect(jsonPath("$.[1].itemName").value(orderDTOList.get(1).getItemName()))
                .andExpect(jsonPath("$.[2].itemName").value(orderDTOList.get(2).getItemName()));
        verify(orderService, times(1)).displayActiveOrders();
        verify(orderService, only()).displayActiveOrders();
    }

    @Test
    void displayActiveOrdersEmptyCase() throws Exception {
        given(orderService.displayActiveOrders()).willReturn(emptyOrderDTOList);
        this.mockMvc.perform(get("/order/active"))
                .andExpect(status().isOk());
        verify(orderService, times(1)).displayActiveOrders();
        verify(orderService, only()).displayActiveOrders();
    }

    @Test
    void createOrder() throws Exception {
        given(orderService.create(any(OrderDTO.class))).willReturn(orderDTOList.get(0));
        this.mockMvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\":\"bread\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(orderDTOList.get(0).getId()));
        verify(orderService, times(1)).create(any(OrderDTO.class));
        verify(orderService, only()).create(any(OrderDTO.class));
    }
}