package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ItemController;
import com.example.demo.dao.CartDao;
import com.example.demo.dao.ItemDao;
import com.example.demo.resource.Cart;
import com.example.demo.resource.Item;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
public class Test_ItemController {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private ItemDao dao;
    
    @MockBean
    private CartDao cartDao;
    
	@Test
	public void test() throws Exception {
		// setting up mock response
		Cart cart = new Cart();
		cart.setId(1);
		
		Item item = new Item();
		item.setCart(cart);
		item.setItemName("toothbrush");
		item.setId(1);
		//---------------------
		
		List<Item> items = new ArrayList<>();
		items.add(item);
		
		given(this.dao.findAll()).willReturn(items);
		this.mvc.perform(get("items").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
