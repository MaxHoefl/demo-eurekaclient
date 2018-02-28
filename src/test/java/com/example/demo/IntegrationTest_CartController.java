package com.example.demo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.IntegrationTestApplication;
import com.example.demo.controller.CartController;
import com.example.demo.resource.Cart;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=IntegrationTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest_CartController 
{
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationTest_CartController.class);
	
	@Autowired private CartController cartController;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void test() {
		LOG.info("Cart controller null? {}", cartController == null);
		List<Cart> carts = cartController.getAll();
		for(Cart c : carts)
		{
			LOG.info("CART {}, {}", c.getId(), c.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		}
	}

}
