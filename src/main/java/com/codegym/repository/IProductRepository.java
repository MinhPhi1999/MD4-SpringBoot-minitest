package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByOrderByPrice();

    //tim top theo thứ tự thời gian tạo
    @Query(value = "select * from products order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Product> getTop4();

    // lấy trong khoảng giá
    Iterable<Product> findAllByPriceBetween(double from, double to);
}
