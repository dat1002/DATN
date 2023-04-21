package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.FruitDTO;
import com.fruits.congtyhoaqua.models.Fruit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IFruitService {
    List<Fruit> getAllFruits();
    List<Fruit> selectTopSale();
    List<Fruit> selectTopNew();
    List<Fruit> selectTopSaleVip();
    Fruit createFruit(Integer idManufacture, FruitDTO fruitDTO);
    Fruit editAvatarFruit(Integer idFruit, MultipartFile avatar);
    Fruit editFruit(Integer idFruit,FruitDTO fruitDTO);
    Fruit deleteFruit(Integer idFruit);
    Fruit getById(Integer idFruit);
    List<Fruit> getAllByDay(String afterDate, String beforeDate);
    List<Fruit> getAllByPrice(Integer greaterPrice, Integer lessPrice);
    List<Fruit> getAllByManufacture(Integer idManufacture);
    List<Fruit> getAllByName(String name);
    List<Fruit> getAllByExpiry(String expiry);
    List<Fruit> getAllFruitByCategory(Integer idCategory);
    List<Fruit> filter(
            String idCategory,
            String dayBefore,
            String dayAfter,
            Integer greaterPrice,
            Integer lessPrice,
            String manufacture,
            String name,
            String expiry);
}
