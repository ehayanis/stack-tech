package com.xeigna.ms.demo.repositories;

import java.util.List;

import com.xeigna.ms.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Repository
@RestResource(exported = false)
public interface ProductRepository extends MongoRepository<Product, String> {


}