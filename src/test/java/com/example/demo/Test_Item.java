package com.example.demo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

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
		Item item = new Item();
		item.setId(1);
		item.setItemName("tooth brush");
		item.setCart(null);
		
		Cart cart = new Cart();
		cart.setId(1);
		cart.setItems(new HashSet<>());
		cart.getItems().add(item);
		
		LOG.info("Original cart : {}, {}", cart.getId(), cart.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(cart);
		LOG.info("Cart Payload: {}", payload);
		
		Cart cartCopy = mapper.readValue(payload, Cart.class);
		LOG.info("Reconstructed cart : {}, {}",cartCopy.getId(), cartCopy.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		
		
	}

}
