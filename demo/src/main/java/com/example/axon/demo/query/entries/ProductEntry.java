package com.example.axon.demo.query.entries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: axondemo
 * @description: 商品查询入口
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:15
 **/
@Entity
public class ProductEntry {

	@Id
	private String id;

	@Column
	private String name;

	@Column
	private long price;

	@Column
	private int stock;

	public ProductEntry() {
	}

	public ProductEntry(String id, String name, long price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
