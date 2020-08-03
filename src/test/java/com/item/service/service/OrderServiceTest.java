package com.item.service.service;

import com.item.service.dto.OrderDTO;
import com.item.service.dto.OrderMapper;
import com.item.service.entity.Order;
import com.item.service.repository.OrderRepo;
import com.item.service.utils.TestDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private OrderService mockedOrderService;

    @InjectMocks
    private OrderService orderService;

    private final List<Order> orderList = new TestDataGenerator().generateOrderList();
    private final List<OrderDTO> orderDTOList = new TestDataGenerator().generateOrderDTOList();

    @Test
    void findAll() {
        when(orderRepo.findAll()).thenReturn(orderList);
        List<OrderDTO> testList = orderService.findAll();
        assertEquals(testList.size(), orderList.size());
        verify(orderRepo, times(1)).findAll();
        verify(orderRepo, only()).findAll();
    }

    @Test
    void create() {
        when(orderRepo.save(any(Order.class))).thenReturn(orderList.get(0));
        Order expectedOrder = OrderMapper.INSTANCE.orderDTOToOrder(orderService.create(new OrderDTO()));
        assertEquals(expectedOrder, orderList.get(0));
        verify(orderRepo, times(1)).save(any(Order.class));
        verify(orderRepo, only()).save(any(Order.class));
    }

    @Test
    void displayActiveOrders() {
        when(mockedOrderService.displayActiveOrders()).thenReturn(orderDTOList);
        List<OrderDTO> expectedOrdersList = orderService.displayActiveOrders();
        assertEquals(expectedOrdersList.size(), 0);
    }
}