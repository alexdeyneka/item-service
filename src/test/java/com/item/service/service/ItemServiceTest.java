package com.item.service.service;

import com.item.service.dto.ItemDTO;
import com.item.service.dto.ItemMapper;
import com.item.service.entity.Item;
import com.item.service.repository.ItemRepo;
import com.item.service.utils.TestDataGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;

    @InjectMocks
    private ItemService itemService;

    private final List<Item> itemList = new TestDataGenerator().generateItemList();

    @Test
    void findAll() {
        when(itemRepo.findAll()).thenReturn(itemList);
        List<ItemDTO> testList = itemService.findAll();
        assertEquals(testList.size(), itemList.size());
        verify(itemRepo, times(1)).findAll();
        verify(itemRepo, only()).findAll();
    }

    @Test
    void findByPriceItemExistsCaseTest() {
        when(itemRepo.findAll()).thenReturn(itemList);
        Optional<ItemDTO> optionalItem = itemService.findByPrice("oil", 50);
        assertEquals(itemList.get(3), ItemMapper.INSTANCE.itemDTOToItem(optionalItem.get()));
        assertNotEquals(itemList.get(1), ItemMapper.INSTANCE.itemDTOToItem(optionalItem.get()));
        verify(itemRepo, times(1)).findAll();
        verify(itemRepo, only()).findAll();
    }

    @Test
    void findByPriceNoItemCaseTest() {
        List<Item> emptyList = new ArrayList<>();
        when(itemRepo.findAll()).thenReturn(emptyList);
        Optional<ItemDTO> optionalItem = itemService.findByPrice("candy", 5);
        assertNull(optionalItem);
        verify(itemRepo, times(1)).findAll();
        verify(itemRepo, only()).findAll();
    }
}