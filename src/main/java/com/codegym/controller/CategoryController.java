package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        Iterable<Category> categories = categoryService.findAll();
        if (categories == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Iterable<Product>> findByName(@PathVariable String name){
        Optional<Category> categoryOptional = categoryService.findAllByNameContaining(name);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Product> products = productService.findAllByCategory(categoryOptional.get());
        return new ResponseEntity<>(products ,HttpStatus.OK);
    }


}
