package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Long> {

}
