package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hygieneitems")
public class HygieneItem extends Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hygieneitem_id")
	private long id;
	
	@Column(name="retailer")
	private String retailer;

	public HygieneItem() {}
	
	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
}
