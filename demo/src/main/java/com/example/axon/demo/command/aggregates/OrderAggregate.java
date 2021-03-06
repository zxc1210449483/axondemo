package com.example.axon.demo.command.aggregates;

import java.util.Map;

import com.example.axon.demo.domins.OrderId;
import com.example.axon.demo.domins.OrderProduct;
import com.example.axon.demo.events.OrderCreatedEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @program: axondemo
 * @description: 订单POJO
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:32
 **/
@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private OrderId id;

	private String username;

	private double payment;

	@AggregateMember
	private Map<String, OrderProduct> products;

	public OrderAggregate() {
	}

	public OrderAggregate(OrderId id, String username, Map<String, OrderProduct> products) {
		AggregateLifecycle.apply(new OrderCreatedEvent(id, username, products));
	}

	public OrderId getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Map<String, OrderProduct> getProducts() {
		return products;
	}

	@EventHandler
	public void on(OrderCreatedEvent event) {
		this.id = event.getOrderId();
		this.username = event.getUsername();
		this.products = event.getProducts();
		computePrice();
	}

	private void computePrice() {
		products.forEach((id, product) -> {
			payment += product.getPrice() * product.getAmount();
		});
	}

	/**
	 * Divided 100 here because of the transformation of accuracy
	 *
	 * @return
	 */
	public double getPayment() {
		return payment / 100;
	}

	public void addProduct(OrderProduct product) {
		this.products.put(product.getId(), product);
		payment += product.getPrice() * product.getAmount();
	}

	public void removeProduct(String productId) {
		OrderProduct product = this.products.remove(productId);
		payment = payment - product.getPrice() * product.getAmount();
	}
}
