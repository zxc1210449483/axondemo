package com.example.axon.demo.query.repository;

import com.example.axon.demo.query.entries.OrderEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderEntryRepository extends PagingAndSortingRepository<OrderEntry, String> {
}
