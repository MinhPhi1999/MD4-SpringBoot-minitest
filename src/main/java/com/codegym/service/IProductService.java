package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Product;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByNameContaining(String name);
    Iterable<Product> findAllByCategory (Category category);
    Iterable<Product> findAllByOrderByPrice();
    Iterable<Product> getTop4();
    Iterable<Product> findAllByPriceBetween(double from, double to);
}
