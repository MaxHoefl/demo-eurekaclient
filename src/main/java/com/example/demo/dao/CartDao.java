package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.resource.Cart;

@Repository
public interface CartDao extends CrudRepository<Cart, Long> {

	Cart getById(long id);
}
