package com.example.axon.demo.query.handlers;

import java.util.HashMap;
import java.util.Map;

import com.example.axon.demo.events.OrderCreatedEvent;
import com.example.axon.demo.query.entries.OrderEntry;
import com.example.axon.demo.query.entries.OrderProductEntry;
import com.example.axon.demo.query.repository.OrderEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: axondemo
 * @description: 订单事件handler
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:17
 **/
@Component
public class OrderEventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandler.class);

	@Autowired
	private OrderEntryRepository repository;

	@EventHandler
	public void on(OrderCreatedEvent event) {
		Map<String, OrderProductEntry> map = new HashMap<>();
		event.getProducts().forEach((id, product) -> {
			map.put(id, new OrderProductEntry(
							product.getId(),
							product.getName(),
							product.getPrice(),
							product.getAmount()));
		});
		OrderEntry order = new OrderEntry(event.getOrderId().toString(), event.getUsername(), map);
		repository.save(order);
	}
}
