package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = productService.findAll();
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> viewProduct(@RequestParam String name){
        Iterable<Product> products = productService.findAllByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sortByPrice")
    public ResponseEntity<Iterable<Product>> sortByPrice(){
        Iterable<Product> products = productService.findAllByOrderByPrice();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<Iterable<Product>> getTop4(){
        Iterable<Product> products = productService.getTop4();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<Iterable<Product>> getBetween(@RequestParam double from, @RequestParam double to){
        Iterable<Product> products = productService.findAllByPriceBetween((int) from, (int) to);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteP(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> editP(@PathVariable Long id,@RequestBody Product product){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createPro(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
