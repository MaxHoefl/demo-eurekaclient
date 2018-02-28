package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
public class Test_CartController {

	private static final Logger LOG = LoggerFactory.getLogger(Test_CartController.class);
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private CartDao dao;
    
    @MockBean
    private ItemDao itemDao;

    
	@Test
	public void testGetAllCarts() throws Exception {
		// setting up mock response
		Item item1 = new Item();
		item1.setId(1);
		item1.setItemName("toothbrush");
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setItemName("milk");
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setItemName("chocolate");
		
		Set<Item> itemSet1 = new HashSet<>();
		itemSet1.add(item1);
		itemSet1.add(item2);
		
		Set<Item> itemSet2 = new HashSet<>();
		itemSet2.add(item3);
		
		Cart cart1 = new Cart();
		cart1.setId(1);
		cart1.setItems(itemSet1);
		
		Cart cart2 = new Cart();
		cart2.setId(2);
		cart2.setItems(itemSet2);
		
		List<Cart> carts = new ArrayList<>();
		carts.add(cart1);
		carts.add(cart2);
		
		given(this.dao.findAll()).willReturn(carts);
		
		this.mvc.perform(get("/carts").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testAddItemToCart() throws Exception 
	{
		Item item1 = new Item();
		item1.setId(1);
		item1.setItemName("toothbrush");
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setItemName("milk");
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setItemName("chocolate");
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(item3);
		
		Set<Item> itemSet1 = new HashSet<>();
		itemSet1.add(item1);
		itemSet1.add(item2);
		
		Cart cart1 = new Cart();
		cart1.setId(1);
		cart1.setItems(itemSet1);
		
		Cart cart1New = new Cart();
		cart1New.setId(1);
		cart1New.setItems(itemSet1);
		cart1New.getItems().add(item3);
		
		//when(dao.findAll()).thenReturn(items);
		given(this.dao.getById(anyLong())).willReturn(cart1);
		given(this.dao.save(any())).willReturn(cart1New);
		
		LOG.info("Sending payload {}", payload);
		this.mvc.perform(post("/carts/{cartId}",1).characterEncoding("ASCII").contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(status().isOk());
	}
}