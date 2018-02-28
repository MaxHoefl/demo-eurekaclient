package com.example.demo.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.ExcludeFromTests;
import com.example.demo.resource.Item;

@Component
@ExcludeFromTests
public class ItemDaoInit 
{
	private static final Logger LOG = LoggerFactory.getLogger(ItemDaoInit.class);
	
	public ItemDaoInit(@Autowired ItemDao dao)
	{
		init(dao);
	}
	
	private void init(ItemDao dao)
	{
		LOG.info(" ------------ item dao preprocessor called");
		
		List<Item> preprocessedItems = new ArrayList<>();
		dao.findAll().forEach(new Consumer<Item>() {
			@Override
			public void accept(Item i) {
				Item ppI = new Item();
				ppI.setId(i.getId());
				ppI.setCart(i.getCart());
				ppI.setItemName(i.getItemName().toUpperCase());
				
				preprocessedItems.add(ppI);
			}
		});
		
		preprocessedItems.forEach(dao::save);
	}
}
