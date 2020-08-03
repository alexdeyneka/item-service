package com.item.service.controller;

import com.item.service.dto.ItemDTO;
import com.item.service.service.ItemService;
import com.item.service.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mockMvc;

    private final List<ItemDTO> itemDTOList = new TestDataGenerator().generateItemDTOList();

    @Test
    void getTheLowestPriceItem() throws Exception {
        given(itemService.findByPrice(anyString(), anyInt())).willReturn(java.util.Optional.ofNullable(itemDTOList.get(0)));
        this.mockMvc.perform(get("/item/find")
                .param("itemName", "bread")
                .param("quantity", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(itemDTOList.get(0).getName()));
        verify(itemService, times(1)).findByPrice(anyString(), anyInt());
        verify(itemService, only()).findByPrice(anyString(), anyInt());
    }
}