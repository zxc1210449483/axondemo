package com.example.axon.demo.command.aggregates;

import com.example.axon.demo.commands.CreateProductCommand;
import com.example.axon.demo.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: axondemo
 * @description: 商品POJO
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:33
 **/
@Aggregate
public class ProductAggregate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductAggregate.class);

	@AggregateIdentifier
	private String id;

	private String name;

	private int stock;

	private long price;

	public ProductAggregate() {
	}

	@CommandHandler
	public ProductAggregate(CreateProductCommand command) {
		AggregateLifecycle.apply(new ProductCreatedEvent(command.getId(), command.getName(),
				command.getPrice(), command.getStock()));
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {
		this.id = event.getId();
		this.name = event.getName();
		this.price = event.getPrice();
		this.stock = event.getStock();
		LOGGER.debug("Product [{}] {} {}x{} is created.", id, name, price, stock);
	}

	public String getName() {
		return name;
	}

	public int getStock() {
		return stock;
	}

	public long getPrice() {
		return price;
	}
}
