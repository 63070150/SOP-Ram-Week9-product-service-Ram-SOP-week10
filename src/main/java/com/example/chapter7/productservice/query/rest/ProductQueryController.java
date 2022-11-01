package com.example.chapter7.productservice.query.rest;


import com.example.chapter7.productservice.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        List<ProductRestModel> products = queryGateway
                .query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        return products;
    }
}
