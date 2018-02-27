package com.example.demo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.resource.Cart;
import com.example.demo.resource.Item;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Test_Item {

	private static final Logger LOG = LoggerFactory.getLogger(Test_Item.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testSerializationOfItemAndCart() throws IOException
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
	}

}
