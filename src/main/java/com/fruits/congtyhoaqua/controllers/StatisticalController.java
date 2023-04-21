package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.services.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistical")
public class StatisticalController extends BaseController<Double> {
    @Autowired
    private IStatisticalService statisticalService;

    @GetMapping("")
    public ResponseEntity<?> revenueForTheYear(){
        return this.resListSuccess(statisticalService.RevenueForTheYear());
    }

    @GetMapping("/staff-asc")
    public ResponseEntity<?> topFiveStaffAcs(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveStaffAscending(start,end));
    }

    @GetMapping("/staff-dec")
    public ResponseEntity<?> topFiveStaffDec(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveStaffDecrease(start,end));
    }
    @GetMapping("/fruit-asc")
    public ResponseEntity<?> topFiveFruitAsc(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveFruitBestseller(start,end));
    }
    @GetMapping("/fruit-dec")
    public ResponseEntity<?> topFiveFruitDec(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveFruitSlowestSelling(start,end));
    }
    @GetMapping("/fruit-profit-asc")
    public ResponseEntity<?> topFiveFruitProfitAsc(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveFruitBestProfit(start,end));
    }
    @GetMapping("/fruit-profit-dec")
    public ResponseEntity<?> topFiveFruitProfitDec(@RequestParam String start,
                                             @RequestParam String end){
        return ResponseEntity.status(200).body(statisticalService.topFiveFruitWorstProfit(start,end));
    }
}
