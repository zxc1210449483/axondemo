package com.example.axon.demo.handlers;

import java.util.HashMap;
import java.util.Map;

import com.example.axon.demo.command.aggregates.OrderAggregate;
import com.example.axon.demo.command.aggregates.ProductAggregate;
import com.example.axon.demo.commands.CreateOrderCommand;
import com.example.axon.demo.domins.OrderProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: axondemo
 * @description: 订单创建命令handler
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:46
 **/
@Component
public class OrderHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderHandler.class);

	@Autowired
	private Repository<OrderAggregate> repository;

	@Autowired
	private Repository<ProductAggregate> productRepository;

	@Autowired
	private EventBus eventBus;

	@CommandHandler
	public void handle(CreateOrderCommand command) throws Exception {
		Map<String, OrderProduct> products = new HashMap<>();
		command.getProducts().forEach((productId, number) -> {
			LOGGER.debug("Loading product information with productId: {}", productId);
			Aggregate<ProductAggregate> aggregate = productRepository.load(productId);
			products.put(productId,
					new OrderProduct(productId,
							aggregate.invoke(productAggregate -> productAggregate.getName()),
							aggregate.invoke(productAggregate -> productAggregate.getPrice()),
							number));
		});
		repository.newInstance(() -> new OrderAggregate(command.getOrderId(), command.getUsername(), products));
	}
}
