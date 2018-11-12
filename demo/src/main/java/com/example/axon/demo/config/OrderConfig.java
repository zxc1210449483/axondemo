package com.example.axon.demo.config;

import com.example.axon.demo.command.aggregates.OrderAggregate;
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
 * @description: 订单配置
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:28
 **/
@Configuration
public class OrderConfig {

	@Autowired
	private EventStore eventStore;

	@Bean
	@Scope("prototype")
	public OrderAggregate orderAggregate() {
		return new OrderAggregate();
	}

	@Bean
	public AggregateFactory<OrderAggregate> orderAggregateAggregateFactory() {
		SpringPrototypeAggregateFactory<OrderAggregate> aggregateFactory = new SpringPrototypeAggregateFactory<>();
		aggregateFactory.setPrototypeBeanName("orderAggregate");
		return aggregateFactory;
	}

	@Bean
	public Repository<OrderAggregate> orderAggregateRepository() {
		EventSourcingRepository<OrderAggregate> repository = new EventSourcingRepository<>(
				orderAggregateAggregateFactory(),
				eventStore
		);
		return repository;
	}
}
