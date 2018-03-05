package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CartDao;
import com.example.demo.resource.Cart;
import com.example.demo.resource.Item;

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
	
	@GetMapping("carts/{cartId}")
	public Cart getById(@PathVariable("cartId") long cartId)
	{
		LOG.info("Cart service: Getting cart with id " + cartId);
		
		try {
			LOG.info("sleeping...");
			Thread.sleep(3_000);
			LOG.info("woke up...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao.getById(cartId);
	}
	
	@PostMapping("carts/{cartId}")
	public Cart addItemToCart(@PathVariable("cartId") long cartId, @RequestBody Item item)
	{
		LOG.info("Trying to add ITEM: id={}, name={}, cart={} to CART: {}", item.getId(), item.getItemName(), item.getCart(), cartId);
		Cart cart = dao.getById(cartId);
		LOG.info("CART: id={}, items={}", cart.getId(),cart.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		cart.getItems().add(item);
		LOG.info("CART: id={}, items={}", cart.getId(),cart.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		Cart res = dao.save(cart);
		LOG.info("CART: id={}, items={}", res.getId(),res.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		Cart resCheck = dao.getById(res.getId());
		LOG.info("CART: id={}, items={}", resCheck.getId(),resCheck.getItems().stream().map(i -> i.getItemName()).collect(Collectors.toList()));
		return res;
	}
	
	@PostMapping("carts/rdm/{id}")
    public Cart addRandomItem(@PathVariable("id") long id)
    {
        String[] rdmItemNames = {"toothbrush", "shampoo", "body wash"};
        int rnd = new Random().nextInt(rdmItemNames.length);
        String itemName = rdmItemNames[rnd];
        LOG.debug("Adding item {}", itemName);
        
        // Add new item to the cart with given id
        Cart cart = dao.getById(id);
        LOG.info("Cart {}: Item count: {}", cart.getId(), cart.getItems().size());
        
        Item item = new Item();
        item.setItemName(itemName);

        // This works but I have to create a new cart object with same id
//        Cart sameCart = new Cart();
//        sameCart.setId(cart.getId());
//        item.setCart(sameCart);

        // This doesn't work, no surprise but I would like to
         //just retrieve the Cart, add the new Item and be done.
         item.setCart(cart);
        
        cart.getItems().add(item);
        LOG.info("Cart item count: {}", cart.getItems().size());
        
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
