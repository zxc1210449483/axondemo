package com.example.axon.demo.query.repository;

import com.example.axon.demo.query.entries.ProductEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductEntryRepository extends PagingAndSortingRepository<ProductEntry, String> {
}
