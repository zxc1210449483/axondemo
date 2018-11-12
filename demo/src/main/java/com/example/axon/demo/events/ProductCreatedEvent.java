package com.example.axon.demo.events;

/**
 * @program: axondemo
 * @description: 商品创建命令
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:41
 **/
public class ProductCreatedEvent {

	private String id;

	private String name;

	private long price;

	private int stock;

	/**
	 * If no empty args constructor, jackson will throw serialization exceptions
	 */
	public ProductCreatedEvent() {
		System.out.println("Product event created");
	}

	public ProductCreatedEvent(String id, String name, long price, int stock) {
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