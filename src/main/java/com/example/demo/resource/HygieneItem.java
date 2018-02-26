package com.example.demo.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="hygieneitems")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class HygieneItem extends Item
{
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "item_id")
//	private long id;
	
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
