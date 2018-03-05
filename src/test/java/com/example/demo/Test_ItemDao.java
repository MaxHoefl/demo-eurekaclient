package com.example.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.ItemDao;
import com.example.demo.resource.Cart;
import com.example.demo.resource.Item;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Test_ItemDao 
{
	private static final Logger LOG = LoggerFactory.getLogger(Test_ItemDao.class);
	
    @Autowired
    private ItemDao dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllItems() 
	{
		Cart cart = new Cart();
		cart.setId(1);
		cart.setItems(new HashSet<>());
		
		Item item = new Item();
		item.setId(1);
		item.setCart(cart);
		item.setItemName("toothbrush");
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setCart(cart);
		item2.setItemName("milk");
		
		
		List<Item> items = new ArrayList<>();
		dao.findAll().forEach(items::add);
		
		for(Item i : items)
		{
			LOG.info("Item {}, {}", i.getId(), i.getItemName());
			
		}
		
		assertTrue("item milk not found in cart", items.stream().filter(i -> i.getItemName().equalsIgnoreCase("tooth brush")).count() == 1);
		assertTrue("item toothbrush not found in cart", items.stream().filter(i -> i.getItemName().equalsIgnoreCase("shampoo")).count() == 1);
	}
	
	@Test
	@Transactional
	public void testSaveNewItems()
	{
		List<Item> items = new ArrayList<>();
		dao.findAll().forEach(items::add);
		
		for(Item i : items)
		{
			LOG.info("ITEM {}, {}", i.getId(), i.getItemName());
		}
		
		Cart cart = new Cart();
		cart.setId(1);
		cart.setItems(new HashSet<>());
		
		Item item = new Item();
		item.setCart(cart);
		item.setItemName("toothbrush");
		
		Item item2 = new Item();
		item2.setCart(cart);
		item2.setItemName("milk");
		
		Item saved =null;
		try
		{
			LOG.info("SAVING ITEM: {}, {}, {}", item.getId(), item.getItemName(), item.getCart().getId());
			saved = dao.save(item);
		}
		catch(Exception e)
		{
			LOG.error("Cannot save item {}", saved,e);
			fail();
		}

		items = new ArrayList<>();
		dao.findAll().forEach(items::add);
		
		for(Item i : items)
		{
			LOG.info("ITEM {}, {}", i.getId(), i.getItemName());
		}
		
	}
}
