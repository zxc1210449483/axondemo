package com.example.axon.demo.events;

import java.util.Map;

import com.example.axon.demo.domins.OrderId;
import com.example.axon.demo.domins.OrderProduct;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @program: axondemo
 * @description: 订单创建命令
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:41
 **/
public class OrderCreatedEvent {

	@TargetAggregateIdentifier

	private OrderId orderId;

	private String username;

	private Map<String, OrderProduct> products;

	public OrderCreatedEvent() {
	}

	public OrderCreatedEvent(OrderId orderId, String username, Map<String, OrderProduct> products) {
		this.orderId = orderId;
		this.username = username;
		this.products = products;
	}

	public OrderId getOrderId() {
		return orderId;
	}

	public String getUsername() {
		return username;
	}

	public Map<String, OrderProduct> getProducts() {
		return products;
	}
}
