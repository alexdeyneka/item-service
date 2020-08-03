package com.item.service.utils;

import com.item.service.dto.ItemDTO;
import com.item.service.dto.ItemMapper;
import com.item.service.dto.OrderDTO;
import com.item.service.dto.OrderMapper;
import com.item.service.entity.Item;
import com.item.service.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {

    public List<ItemDTO> generateItemDTOList() {
        ItemDTO itemDTO1 = new ItemDTO(1L, "bread", 12.0, 100);
        ItemDTO itemDTO2 = new ItemDTO(2L, "oil", 20.0, 80);
        ItemDTO itemDTO3 = new ItemDTO(3L, "milk", 25.0, 450);
        ItemDTO itemDTO4 = new ItemDTO(4L, "oil", 18.5, 35);
        return Arrays.asList(itemDTO1, itemDTO2, itemDTO3, itemDTO4);
    }

    public List<OrderDTO> generateOrderDTOList() {
        OrderDTO orderDTO1 = new OrderDTO(1L, "bread", 12.0, 80);
        OrderDTO orderDTO2 = new OrderDTO(2L, "oil", 10.0, 50);
        OrderDTO orderDTO3 = new OrderDTO(3L, "milk", 20.0, 120);
        OrderDTO orderDTO4 = new OrderDTO(4L, "bread", 9.0, 200);
        return Arrays.asList(orderDTO1, orderDTO2, orderDTO3, orderDTO4);
    }

    public List<Item> generateItemList() {
        List<ItemDTO> tempList = generateItemDTOList();
        List<Item> result = new ArrayList<>();
        tempList.forEach(n -> result.add(ItemMapper.INSTANCE.itemDTOToItem(n)));
        return result;
    }

    public List<Order> generateOrderList() {
        List<OrderDTO> tempList = generateOrderDTOList();
        List<Order> result = new ArrayList<>();
        tempList.forEach(n -> result.add(OrderMapper.INSTANCE.orderDTOToOrder(n)));
        return result;
    }
}
