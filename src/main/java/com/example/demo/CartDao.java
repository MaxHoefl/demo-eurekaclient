package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CartDao extends CrudRepository<Cart, Long> {

	Cart getById(long id);
}
