package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ItemDao;
import com.example.demo.resource.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ItemController 
{
	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired ItemDao dao;
	
	@GetMapping("items")
	public List<Item> getAll()
	{
		List<Item> res = new ArrayList<>();
		dao.findAll().forEach(res::add);
		return res;
	}
	
	@PostMapping("items")
	public Item addItem(@RequestBody Item item)
	{
		LOG.info("Trying to add item {}", item.toString());
		return dao.save(item);
	}
	
	@GetMapping("items/{item_id}")
	public Item getItemById(@PathVariable("item_id") long item_id)
	{
		Item item = dao.findById(item_id).get();
		LOG.info(" ---------------- Retrieved item: ID: {}, NAME: {}, CART: {}", item.getId(), item.getItemName(), item.getCart().getId());
		return item;
	}
}
