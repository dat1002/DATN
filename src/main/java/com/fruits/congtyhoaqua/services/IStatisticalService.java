package com.fruits.congtyhoaqua.services;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IStatisticalService {
    List<Double> RevenueForTheYear();
    Map<String, Integer> topFiveStaffAscending(String start, String end);
    Map<String, Integer> topFiveStaffDecrease(String start,String end);
    Map<String, Integer> topFiveFruitBestseller(String start, String end);
    Map<String, Integer> topFiveFruitSlowestSelling(String start, String end);
    Map<String, Integer> topFiveFruitBestProfit(String start, String end);
    Map<String, Integer> topFiveFruitWorstProfit(String start, String end);
}
