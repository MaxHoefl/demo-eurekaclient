package com.example.demo.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="items")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Item
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long id;
	
	@Column(name="item_name")
	private String itemName;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable=false)
    @JsonBackReference
    private Cart cart;

    public Item() {
    }

    public Item(Cart c) {
        this.cart = c;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + id + ", itemName=" + itemName + ", cart_id=" + cart.getId() + "]";
	}
}
