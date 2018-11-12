package com.example.axon.demo.commands;

/**
 * @program: axondemo
 * @description: 创建商品命令
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:38
 **/
public class CreateProductCommand {

	private String id;

	private String name;

	private long price;

	private int stock;

	public CreateProductCommand(String id, String name, long price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
}
