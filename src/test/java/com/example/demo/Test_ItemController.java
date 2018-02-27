package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.controller.ItemController;
import com.example.demo.dao.CartDao;
import com.example.demo.dao.ItemDao;
import com.example.demo.resource.Cart;
import com.example.demo.resource.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
public class Test_ItemController {

	private static final Logger LOG = LoggerFactory.getLogger(Test_ItemController.class);
	
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
		
		//when(dao.findAll()).thenReturn(items);
		
		given(this.dao.findAll()).willReturn(items);
		
		this.mvc.perform(get("/items").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testAdd() throws Exception {
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
		
		//when(dao.findAll()).thenReturn(items);
		
		given(this.dao.save(any())).willReturn(item);
		
		this.mvc.perform(post("/items").characterEncoding("ASCII").contentType(MediaType.APPLICATION_JSON).content("{\"item_id\": 1,\"itemName\": \"TOOTH BRUSH\",\"cart_id\": 1}")).andExpect(status().isOk());
	}
	

	@Test
    public void testGetResponse() throws Exception 
	{
		Cart cart = new Cart();
		cart.setId(1);
		
		Item item = new Item();
		item.setId(1);
		item.setItemName("tooth brush");
		item.setCart(cart);
		
		LOG.info("Original item : {}", item.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(item);
		LOG.info("Payload: {}", payload);
		
		Item itemCopy = mapper.readValue(payload, Item.class);
		LOG.info("Reconstructed query : {}", itemCopy.toString());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MvcResult res = this.mvc.perform(post("/items").characterEncoding("ASCII").headers(headers).content(payload)).andExpect(status().isOk()).andDo(print()).andReturn();
	}
}
