package com.fruits.congtyhoaqua.services.imp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruits.congtyhoaqua.dtos.FruitDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Category;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.FruitCategory;
import com.fruits.congtyhoaqua.models.Manufacture;
import com.fruits.congtyhoaqua.models.id.FruitCategoryId;
import com.fruits.congtyhoaqua.repositories.CategoryRepository;
import com.fruits.congtyhoaqua.repositories.FruitCategoryRepository;
import com.fruits.congtyhoaqua.repositories.FruitRepository;
import com.fruits.congtyhoaqua.repositories.ManufactureRepository;
import com.fruits.congtyhoaqua.services.IFruitService;
import com.fruits.congtyhoaqua.utils.Convert;
import com.fruits.congtyhoaqua.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FruitServiceImp implements IFruitService {
    @Autowired
    private FruitRepository fruitRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Autowired
    private FruitCategoryRepository fruitCategoryRepository;

    @Autowired
    private UploadFile uploadFile;


    @Override
    public List<Fruit> getAllFruits(Integer start, Integer size) {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.selectAllFruit(start,size));
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    // tất cả sản phẩm đã xóa
    @Override
    public List<Fruit> selectAllFruitDeleted() {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.selectAllFruitDeleted());
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    @Override
    public List<Fruit> selectTopSale() {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.selectTopSale());
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    @Override
    public List<Fruit> selectTopNew() {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.selectTopNew());
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    @Override
    public List<Fruit> selectTopSaleVip() {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.selectTopSaleVip());
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    @Override
    public Fruit createFruit(Integer idManufacture, FruitDTO fruitDTO) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(idManufacture);
        if (manufacture.isEmpty()){
            throw new NotFoundException("No manufacture");
        }
        Fruit fruit = new Fruit();
        Convert.fromFruitDTOToFruit(fruitDTO,fruit);
        fruit.setManufacture(manufacture.get());
        Fruit fruit1 = fruitRepository.save(fruit);
        Set<FruitCategory> fruitCategories = new HashSet<>();

        for (Integer i: fruitDTO.getIdCategories()) {
            fruitCategories.add(fruitCategoryRepository.save(new FruitCategory(new FruitCategoryId(fruit1.getId(),i),fruit1,categoryRepository.findById(i).get())));
        }
        fruit1.setDateCreated(LocalDate.now());
        return fruitRepository.save(fruit1);
    }

    @Override
    public Fruit editAvatarFruit(Integer idFruit, MultipartFile avatar) {
        Optional<Fruit> fruit = fruitRepository.findById(idFruit);
        if (fruit.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        fruit.get().setAvatar(uploadFile.getUrlFromFile(avatar));
        return fruitRepository.save(fruit.get());
    }

    @Override
    public Fruit editFruit(Integer idFruit, FruitDTO fruitDTO) {
        Optional<Fruit> fruit = fruitRepository.findById(idFruit);
        if (fruit.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruitRepository.save(Convert.fromFruitDTOToFruit(fruitDTO,fruit.get()));
    }

    @Override
    public Fruit deleteFruit(Integer idFruit) {
        Optional<Fruit> fruit = fruitRepository.findById(idFruit);
        if (fruit.isEmpty()){
            throw new NotFoundException("No fruit");
        }

        fruit.get().setStatus(false);
        fruitRepository.save(fruit.get());
        return fruit.get();
    }

    @Override
    public Fruit restoreFruit(Integer idFruit) {
        Optional<Fruit> fruit = fruitRepository.findById(idFruit);
        if (fruit.isEmpty()){
            throw new NotFoundException("No fruit");
        }

        fruit.get().setStatus(true);
        fruitRepository.save(fruit.get());
        return fruit.get();
    }

    @Override
    public Fruit getById(Integer idFruit) {
        Optional<Fruit> fruit = fruitRepository.findById(idFruit);
        if (fruit.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruit.get();
    }

    @Override
    public List<Fruit> getAllByDay(String afterDate, String beforeDate) {
        LocalDate afterDateConvert = LocalDate.parse(afterDate);
        LocalDate beforeDateConvert = LocalDate.parse(beforeDate);
        return fruitRepository.findAllByDateCreatedBetween(afterDateConvert,beforeDateConvert);
    }

    @Override
    public List<Fruit> getAllByPrice(Integer greaterPrice, Integer lessPrice) {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.findAllByPriceOutGreaterThanAndPriceOutLessThan(greaterPrice, lessPrice));
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }

    @Override
    public List<Fruit> getAllByManufacture(Integer idManufacture) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(idManufacture);
        if (manufacture.isEmpty()){
            throw new NotFoundException("No manufacture");
        }
        List<Fruit> fruits = new ArrayList<>(fruitRepository.findAllByManufactureAndOrOrderById(manufacture.get()));
        if (fruits.isEmpty()){
            throw new NotFoundException("No Fruit");
        }
        return fruits;
    }

    @Override
    public List<Fruit> getAllByName(String name) {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.findAllByNameContaining(name));
        if (fruits.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        return fruits;
    }


    @Override
    public List<Fruit> getAllByExpiry(String expiry) {
        return null;
    }

    @Override
    public List<Fruit> getAllFruitByCategory(Integer idCategory) {
        Set<FruitCategory> fruitCategories = fruitCategoryRepository
                .findAll()
                .stream()
                .filter(fruitCategory -> fruitCategory.getFruitCategoryId().getCategoryId().equals(idCategory))
                .collect(Collectors.toSet());
        List<Fruit> fruits = new ArrayList<>();
        for (FruitCategory fruitCategory :
                fruitCategories) {
            fruits.add(fruitCategory.getFruit());
        }
        return fruits;
    }

    @Override
    public List<Fruit> top5() {
        List<Fruit> fruits = new ArrayList<>(fruitRepository.top5());
        return fruits;
    }

    @Override
    public List<Fruit> filter(String idCategory, String dayBefore, String dayAfter, Integer greaterPrice, Integer lessPrice, String manufacture, String name, String expiry) {
        return null;
    }
}
