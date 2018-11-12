package com.example.axon.demo.commands;

import java.util.Map;

import com.example.axon.demo.domins.OrderId;


/**
 * @program: axondemo
 * @description: 创建订单命令
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:37
 **/
public class CreateOrderCommand {

	private OrderId orderId;

	private String username;

	private Map<String, Integer> products;

	public CreateOrderCommand(String username, Map<String, Integer> products) {
		this.orderId = new OrderId();
		this.username = username;
		this.products = products;
	}

	public OrderId getOrderId() {
		return orderId;
	}

	public String getUsername() {
		return username;
	}

	public Map<String, Integer> getProducts() {
		return products;
	}
}
