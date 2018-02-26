package com.example.demo.service;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ItemDao;
import com.example.demo.resource.Item;

@Component
@DependsOn("itemDaoInit")
public class ItemService 
{
	private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);
	
	public ItemService(@Autowired ItemDao dao)
	{
		dao.findAll().forEach(new Consumer<Item>() {
			@Override
			public void accept(Item t) {
				LOG.info(" ----------------------------- {}",t.toString());
			}
		});
	}
}
