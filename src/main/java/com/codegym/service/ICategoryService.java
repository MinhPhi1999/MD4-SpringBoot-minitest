package com.codegym.service;

import com.codegym.model.Category;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category>{
    Optional<Category> findAllByNameContaining(String name);
}
