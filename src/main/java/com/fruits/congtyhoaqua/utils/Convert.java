package com.fruits.congtyhoaqua.utils;

import com.fruits.congtyhoaqua.dtos.*;
import com.fruits.congtyhoaqua.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class Convert {
    public static User fromUserDTOToUser(UserDTO userDTO, User user){
        if (userDTO.getAccount() != null) {
            user.setAccount(userDTO.getAccount());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getPreName() != null) {
            user.setPreName(userDTO.getPreName());
        }
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getAge() != null) {
            user.setAge(userDTO.getAge());
        }
        if (userDTO.getAddress() != null) {
            user.setAddress(userDTO.getAddress());
        }
        if (userDTO.getBirthday() != null) {
            user.setBirthday(userDTO.getBirthday());
        }
        if (userDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        return user;
    }

    public static Fruit fromFruitDTOToFruit(FruitDTO fruitDTO, Fruit fruit) {
        if (fruitDTO.getName() != null) {
            fruit.setName(fruitDTO.getName());
        }
        if (fruitDTO.getDescription() != null) {
            fruit.setDescription(fruitDTO.getDescription());
        }
        if (fruitDTO.getExpiry() != null) {
            fruit.setExpiry(LocalDate.parse(fruitDTO.getExpiry()));
        }
        if (fruitDTO.getAmount() != null) {
            fruit.setAmount(fruitDTO.getAmount());
        }
        if (fruitDTO.getPriceIn() != null) {
            fruit.setPriceIn(fruitDTO.getPriceIn());
        }
        if (fruitDTO.getPriceOut() != null) {
            fruit.setPriceOut(fruitDTO.getPriceOut());
        }
        if (fruitDTO.getSale() != null) {
            fruit.setSale(fruitDTO.getSale());
        }
        return fruit;
    }

    public static BillDetail fromBillDetailDTOToBillDetail(BillDetailDTO billDetailDTO, BillDetail billDetail) {

        if (billDetailDTO.getAmount() != null){
            billDetail.setAmount(billDetailDTO.getAmount());
        }
        return billDetail;
    }


    public static CartDetail fromCartDetailDTOToCartDetail(CartDetailDTO cartDetailDTO, CartDetail cartDetail) {

        if (cartDetailDTO.getAmount()!= null){
            cartDetail.setAmount(cartDetailDTO.getAmount());
        }
        return cartDetail;
    }

    public static Knowledge fromKnowledgeDTOToKnowledge(KnowledgeDTO knowledgeDTO, Knowledge knowledge) {

        if (knowledgeDTO.getTitle()!=null){
            knowledge.setTitle(knowledgeDTO.getTitle());
        }
        if (knowledgeDTO.getCreated_by()!=null){
            knowledge.setCreated_by(knowledgeDTO.getCreated_by());
        }
        if (knowledgeDTO.getDescription()!=null){
            knowledge.setDescription(knowledgeDTO.getDescription());
        }
        return knowledge;
    }


    public static Category fromCategoryDTOToCategory(CategoryDTO categoryDTO,Category category) {
        if (categoryDTO.getName() != null) {
            category.setName(categoryDTO.getName());
        }
        if (categoryDTO.getDescription() != null) {
            category.setDescription(categoryDTO.getDescription());
        }

        return category;
    }

//    public static Customer fromCustomerDTOToCustomer(CustomerDTO customerDTO,Customer customer){
//        if (customerDTO.getName() != null) {
//            customer.setName(customerDTO.getName());
//        }
//        if (customerDTO.getAddress() != null) {
//            customer.setAddress(customerDTO.getAddress());
//        }
//        if (customerDTO.getPhoneNumber() != null) {
//            customer.setPhoneNumber(customerDTO.getPhoneNumber());
//        }
//        if (customerDTO.getEmail() != null) {
//            customer.setEmail(customerDTO.getEmail());
//        }
//        if (customerDTO.getNumberOfPurchases() != null) {
//            customer.setNumberOfPurchases(customerDTO.getNumberOfPurchases());
//        }
//
//        return customer;
//    }
    public static Manufacture fromManufactureDTOToManufacture(ManufactureDTO manufactureDTO,Manufacture manufacture){
        if (manufactureDTO.getName() != null) {
            manufacture.setName(manufactureDTO.getName());
        }
        if (manufactureDTO.getAddress() != null) {
            manufacture.setAddress(manufactureDTO.getAddress());
        }
        if (manufactureDTO.getPhoneNumber() != null) {
            manufacture.setPhoneNumber(manufactureDTO.getPhoneNumber());
        }
        return manufacture;
    }

    public static Role fromRoleDTOToRole(RoleDTO roleDTO,Role role){
        if (roleDTO.getName() != null){
            role.setName(roleDTO.getName());
        }
        return role;
    }
}
