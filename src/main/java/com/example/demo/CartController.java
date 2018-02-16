package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController 
{
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
	
	@PostMapping("carts/items")
	public Cart addItemToCart(@RequestBody Item item)
	{
		long cartId = item.getCart().getId();
		Cart cart = dao.getById(cartId);
		cart.getItems().add(item);
		
		return dao.save(cart);
	}
}
