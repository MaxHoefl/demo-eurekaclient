package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CartController 
{
	private static final Logger LOG = LoggerFactory.getLogger(CartController.class);
	
	@Autowired CartDao dao;
	
	@GetMapping("carts")
	public List<Cart> getAll()
	{
		List<Cart> res = new ArrayList<>();
		dao.findAll().forEach(res::add);
		return res;
	}
	
	@PostMapping("carts")
	public Cart addCart(@RequestBody Cart cart)
	{
		return dao.save(cart);
	}
	
	@PostMapping("carts/rdm/{id}")
	public Cart addRandomItem(@PathVariable("id") long id)
	{
		String[] rdmItemNames = {"toothbrush", "shampoo", "body wash"};
		
		Cart cart = dao.getById(id);
		Item item = new Item();
		int rnd = new Random().nextInt(rdmItemNames.length);
		String itemName = rdmItemNames[rnd];
		LOG.debug("Adding item: {}", itemName);
		
		item.setItemName(rdmItemNames[rnd]);
//		Cart sameCart = new Cart();
//		sameCart.setId(cart.getId());
//		item.setCart(sameCart);
		item.setCart(cart);
		
		cart.getItems().add(item);
		return dao.save(cart);
	}
	
	@PostMapping("carts/items")
	public Cart addItemToCart(@RequestBody Item item)
	{
		long cartId = item.getCart().getId();
		Cart cart = dao.getById(cartId);
		cart.getItems().add(item);
		
		return dao.save(cart);
	}
}
