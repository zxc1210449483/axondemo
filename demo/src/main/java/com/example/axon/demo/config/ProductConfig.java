package com.example.axon.demo.config;

import com.example.axon.demo.command.aggregates.ProductAggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @program: axondemo
 * @description: 商品配置
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:29
 **/
@Configuration
public class ProductConfig {

	@Autowired
	private EventStore eventStore;

	@Bean
	@Scope("prototype")
	public ProductAggregate productAggregate() {
		return new ProductAggregate();
	}

	@Bean
	public AggregateFactory<ProductAggregate> productAggregateAggregateFactory() {
		SpringPrototypeAggregateFactory<ProductAggregate> aggregateFactory = new SpringPrototypeAggregateFactory<>();
		aggregateFactory.setPrototypeBeanName("productAggregate");
		return aggregateFactory;
	}

	@Bean
	public Repository<ProductAggregate> productAggregateRepository() {
		EventSourcingRepository<ProductAggregate> repository = new EventSourcingRepository<>(
				productAggregateAggregateFactory(),
				eventStore
		);
		return repository;
	}
}
