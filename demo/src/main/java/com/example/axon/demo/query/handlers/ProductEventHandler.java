package com.example.axon.demo.query.handlers;

import com.example.axon.demo.events.ProductCreatedEvent;
import com.example.axon.demo.query.entries.ProductEntry;
import com.example.axon.demo.query.repository.ProductEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: axondemo
 * @description: 商品事件handler
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:18
 **/
@Component
public class ProductEventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventHandler.class);

	@Autowired
	ProductEntryRepository repository;

	@EventHandler
	public void on(ProductCreatedEvent event) {
		// update the data in the cache or db of the query side
		LOGGER.debug("repository data is updated");
		repository.save(new ProductEntry(event.getId(), event.getName(), event.getPrice(), event.getStock()));
	}
}
